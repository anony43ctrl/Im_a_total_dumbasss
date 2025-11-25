package com.synechron.group1.onlineretail.util;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.synechron.group1.onlineretail.domain.Order;
import com.synechron.group1.onlineretail.domain.OrderItem;
import com.synechron.group1.onlineretail.domain.Product;
import com.synechron.group1.onlineretail.dto.OrderItemDTO;
import com.synechron.group1.onlineretail.repository.OrderItemRepository;
import com.synechron.group1.onlineretail.repository.ProductRepository;

@Component
public class OrderItemUtil {
	private final ProductRepository productRepository;
	private final OrderItemRepository orderItemRepository;
	
	public OrderItemUtil(ProductRepository productRepository, OrderItemRepository orderItemRepository) {
		this.productRepository = productRepository;
		this.orderItemRepository = orderItemRepository;
	}
	
	public OrderItemDTO prepareOrderItem(Product product, int quantity, Order order) throws Exception {
    	if(product.getStockQuantity()<quantity) {
    		throw new IllegalArgumentException("Insufficient stock for product: " +product.getName());
    	}
    	
    	product.setStockQuantity(product.getStockQuantity()-quantity);
    	productRepository.save(product);
    	
    	BigDecimal itemTotal = product.getPrice().multiply(BigDecimal.valueOf(quantity));
    	
    	OrderItem item = new OrderItem();
    	item.setProduct(product);
    	item.setQuantity(quantity);
    	item.setPrice(itemTotal);
    	item.setOrder(order);
    	
    	orderItemRepository.save(item);
    	
    	return MapperUtil.toDto(item);
    }
	
	

}
