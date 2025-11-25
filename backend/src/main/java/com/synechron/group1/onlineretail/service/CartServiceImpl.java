package com.synechron.group1.onlineretail.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.synechron.group1.onlineretail.domain.Cart;
import com.synechron.group1.onlineretail.domain.CartItem;
import com.synechron.group1.onlineretail.domain.Order;
import com.synechron.group1.onlineretail.domain.Product;
import com.synechron.group1.onlineretail.domain.User;
import com.synechron.group1.onlineretail.dto.CartResponse;
import com.synechron.group1.onlineretail.dto.OrderDTO;
import com.synechron.group1.onlineretail.dto.OrderItemDTO;
import com.synechron.group1.onlineretail.enums.CartStatus;
import com.synechron.group1.onlineretail.enums.OrderStatus;
import com.synechron.group1.onlineretail.enums.PaymentMethod;
import com.synechron.group1.onlineretail.enums.PaymentStatus;
import com.synechron.group1.onlineretail.repository.CartRepository;
import com.synechron.group1.onlineretail.repository.OrderRepository;
import com.synechron.group1.onlineretail.repository.ProductRepository;
import com.synechron.group1.onlineretail.repository.UserRepository;
import com.synechron.group1.onlineretail.util.MapperUtil;
import com.synechron.group1.onlineretail.util.OrderItemUtil;

import jakarta.transaction.Transactional;

@Service
public class CartServiceImpl implements CartService {
	
	private final CartRepository cartRepository;
	private final ProductRepository productRepository;
	private final UserRepository userRepository;
	private final OrderRepository orderRepository;
	private final OrderItemUtil orderItemUtil;

	public CartServiceImpl(CartRepository cartRepository, ProductRepository productRepository,
	                       UserRepository userRepository, OrderRepository orderRepository,OrderItemUtil orderItemUtil) {
	    this.cartRepository = cartRepository;
	    this.productRepository = productRepository;
	    this.userRepository = userRepository;
	    this.orderRepository=orderRepository;
	    this.orderItemUtil = orderItemUtil;
	    
	}
	
	@Override
	public Cart getOrCreateCart(Long userId) {
		return cartRepository.findByUserIdAndStatus(userId, CartStatus.ACTIVE).orElseGet(() -> {
			Cart newCart = new Cart();
			newCart.setUserId(userId);
			newCart.setStatus(CartStatus.ACTIVE);
			return cartRepository.save(newCart);
		});
	}


	@Override
	public CartResponse addProductToCart(Long userId, Long productId, int quantity) {
		Cart cart = getOrCreateCart(userId);
		
		Product product =productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
		Optional<CartItem> existingItem = cart.getItems().stream()
				.filter(item -> item.getProduct().getId().equals(productId)).findFirst();
		if (!product.isActive()) {
		    throw new RuntimeException("Product is inactive and cannot be added to cart");
		}

		if(existingItem.isPresent()) {
			existingItem.get().setQuantity(existingItem.get().getQuantity()+quantity);
		}
		
		if(product.getStockQuantity()<quantity) {
			throw new IllegalArgumentException("Cannot add product to cart due to exceeding limit");
		}
		
		else
		{	
			CartItem newItem = new CartItem();
			newItem.setProduct(product);
			newItem.setQuantity(quantity);
			newItem.setCart(cart);
			cart.getItems().add(newItem);
		}
		cartRepository.save(cart);
		return MapperUtil.toDto(cart);
	}

	@Override
	public CartResponse updateProductQuantity(Long userId, Long productId, int quantity) {
		Cart cart = getOrCreateCart(userId);
		
		cart.getItems().stream().filter(item -> item.getProduct().getId().equals(productId))
		.findFirst()
		.ifPresent(item -> item.setQuantity(quantity));
		cartRepository.save(cart);
		return MapperUtil.toDto(cart);
	}

	@Override
	public CartResponse removeProductFromCart(Long userId, Long productId) {
		Cart cart = getOrCreateCart(userId);
		
		cart.getItems().removeIf(item -> item.getProduct().getId().equals(productId));
		cartRepository.save(cart);
		return MapperUtil.toDto(cart);
	}

	@Override
	public CartResponse clearCart(Long userId) {
		Cart cart = getOrCreateCart(userId);
		cart.getItems().clear();
		cartRepository.save(cart);
		return MapperUtil.toDto(cart);
	}
	
	@Override
	@Transactional
	public OrderDTO checkoutCart(Long userId, PaymentMethod paymentMethod) throws Exception {
	    // Fetch active cart
	    Cart cart = cartRepository.findByUserIdAndStatus(userId, CartStatus.ACTIVE)
	        .orElseThrow(() -> new RuntimeException("No active cart found"));

	    if (cart.getItems().isEmpty()) {
	        throw new RuntimeException("Cart is empty");
	    }

	    // Fetch user
	    User user = userRepository.findById(userId)
	        .orElseThrow(() -> new RuntimeException("User not found"));

	    // Prepare order entity
	    Order order = new Order();
	    order.setCustomer(user);
	    order.setPaymentMethod(paymentMethod);
	    order.setOrderDate(LocalDateTime.now());
	    order.setShippingAddress(user.getAddress());

	    // Set order and payment status based on payment method
	    if (paymentMethod == PaymentMethod.NET_BANKING) {
	        order.setOrderStatus(OrderStatus.PENDING);
	        order.setPaymentStatus(PaymentStatus.PENDING);
	    } else {
	        order.setOrderStatus(OrderStatus.CONFIRMED);
	        order.setPaymentStatus(PaymentStatus.PENDING);
	    }

	    order.setTotalAmount(BigDecimal.ZERO);
	    orderRepository.save(order);

	    List<OrderItemDTO> itemDTOs = new ArrayList<>();
	    BigDecimal totalAmount = BigDecimal.ZERO;

	    for (CartItem cartItem : cart.getItems()) {
	        OrderItemDTO savedDto = orderItemUtil.prepareOrderItem(cartItem.getProduct(), cartItem.getQuantity(), order);
	        itemDTOs.add(savedDto);
	        totalAmount = totalAmount.add(savedDto.getPrice());
	    }

	    order.setTotalAmount(totalAmount);
	    orderRepository.save(order);

	    cart.setStatus(CartStatus.CHECKED_OUT);
	    cartRepository.save(cart);

	    OrderDTO result = MapperUtil.toDto(order);
	    result.setItems(itemDTOs);

	    return result;
	}

	@Override
	public CartResponse getCart(Long userId) {
		Cart cart = getOrCreateCart(userId);
		return MapperUtil.toDto(cart);
	}
}
