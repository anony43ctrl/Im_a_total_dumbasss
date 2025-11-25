package com.synechron.group1.onlineretail.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.synechron.group1.onlineretail.domain.Order;
import com.synechron.group1.onlineretail.domain.OrderItem;
import com.synechron.group1.onlineretail.domain.Product;
import com.synechron.group1.onlineretail.domain.User;
import com.synechron.group1.onlineretail.dto.OrderDTO;
import com.synechron.group1.onlineretail.dto.OrderItemDTO;
import com.synechron.group1.onlineretail.enums.OrderStatus;
import com.synechron.group1.onlineretail.enums.PaymentMethod;
import com.synechron.group1.onlineretail.enums.PaymentStatus;
import com.synechron.group1.onlineretail.repository.OrderRepository;
import com.synechron.group1.onlineretail.repository.ProductRepository;
import com.synechron.group1.onlineretail.repository.UserRepository;
import com.synechron.group1.onlineretail.util.MapperUtil;
import com.synechron.group1.onlineretail.util.OrderItemUtil;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{

	private final OrderRepository orderRepository;
	private final UserRepository userRepository;
	private final ProductRepository productRepository;
	private final OrderItemUtil orderItemUtil;
	
	public OrderServiceImpl(OrderRepository orderRepository, 
			UserRepository userRepository, ProductRepository productRepository,OrderItemUtil orderItemUtil) {
		this.orderRepository= orderRepository;
		this.userRepository = userRepository;
		this.productRepository = productRepository;
		this.orderItemUtil = orderItemUtil;
	}
	
	@Override
	@Transactional
	public OrderDTO placeOrder(OrderDTO orderDTO) throws Exception {
	    if (orderDTO.getUserId() == null) {
	        throw new IllegalArgumentException("CustomerId must not be null");
	    }

	    User customer = userRepository.findById(orderDTO.getUserId())
	        .orElseThrow(() -> new RuntimeException("Customer not found"));

	    if (orderDTO.getItems() == null || orderDTO.getItems().isEmpty()) {
	        throw new IllegalArgumentException("Must have at least one product");
	    }
	    
	    
	    Order order = new Order();
	    order.setCustomer(customer);
	    order.setOrderStatus(OrderStatus.PENDING);
	    order.setPaymentStatus(PaymentStatus.PENDING);
	    order.setOrderDate(LocalDateTime.now());
	    order.setShippingAddress(orderDTO.getShippingAddress());
	    order.setPaymentMethod(orderDTO.getPaymentMethod() != null ? orderDTO.getPaymentMethod() : PaymentMethod.COD);
	    order.setTotalAmount(BigDecimal.ZERO);
	    
	    orderRepository.save(order);
	    
	    List<OrderItemDTO> itemDTO = new ArrayList<OrderItemDTO>();
	    BigDecimal total = BigDecimal.ZERO;

	    for (OrderItemDTO dto : orderDTO.getItems()) {
	        Product product = productRepository.findById(dto.getProductId())
	            .orElseThrow(() -> new IllegalArgumentException("Product not found: " +dto.getProductId()));
	        
	        OrderItemDTO savedDto = orderItemUtil.prepareOrderItem(product, dto.getQuantity(), order);
	        itemDTO.add(savedDto);
	        
	        total = total.add(savedDto.getPrice());
	    }
	    
	    order.setTotalAmount(total); 
	    orderRepository.save(order);
	    
	    OrderDTO result = MapperUtil.toDto(order);
	    result.setItems(itemDTO);
	    return result;
	}
	@Override
    public OrderDTO getOrderById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid order ID");
        }

        Order order = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order with ID " + id + " not found"));

        return MapperUtil.toDto(order);
    }
	@Override
    public List<OrderDTO> getOrderbbyCustomer(Long customerId) {
        if (customerId == null || customerId <= 0) {
            throw new IllegalArgumentException("Invalid customer ID");
        }

        List<Order> orders = orderRepository.findByCustomerId(customerId);
        return orders.stream()
            .map(MapperUtil::toDto)
            .collect(Collectors.toList());
    }
	@Override
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
            .map(MapperUtil::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public OrderDTO updateOrderStatus(Long id, OrderStatus status) {
        if (id == null || status == null) {
            throw new IllegalArgumentException("Order ID and status must not be null");
        }
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Order with ID " + id + " not found"));

        order.setOrderStatus(status);
        orderRepository.save(order);

        return MapperUtil.toDto(order);
    }
    
    @Override
    public void cancelOrder(Long orderId) {
        if (orderId == null || orderId <= 0) {
            throw new IllegalArgumentException("Invalid order ID");
        }

        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new EntityNotFoundException("Order with ID " + orderId + " not found"));
        
        if(order.getItems() != null) {
        	for(OrderItem item: order.getItems()) {
        		Product product = item.getProduct();
        		product.setStockQuantity(product.getStockQuantity() + item.getQuantity());
        		productRepository.save(product);
        	}
        }

        order.setOrderStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
    }
}
