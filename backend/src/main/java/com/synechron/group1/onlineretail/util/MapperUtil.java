package com.synechron.group1.onlineretail.util;


import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.synechron.group1.onlineretail.domain.Cart;
import com.synechron.group1.onlineretail.domain.CartItem;
import com.synechron.group1.onlineretail.domain.Order;
import com.synechron.group1.onlineretail.domain.OrderItem;
import com.synechron.group1.onlineretail.domain.Product;
import com.synechron.group1.onlineretail.domain.User;
import com.synechron.group1.onlineretail.dto.CartItemResponse;
import com.synechron.group1.onlineretail.dto.CartResponse;
import com.synechron.group1.onlineretail.dto.OrderDTO;
import com.synechron.group1.onlineretail.dto.OrderItemDTO;
import com.synechron.group1.onlineretail.dto.ProductDTO;

public final class MapperUtil {

	private MapperUtil() {}
	
	public static ProductDTO toDto(Product product) {
		if(product == null) return null;
		
		return new ProductDTO(
				product.getId(),
				product.getName(),
				product.getDescription(),
				product.getPrice(),
				product.getStockQuantity(),
				product.getCategory(),
				product.getImageURL(),
				product.isActive()
				);
		
	}
	public static Product toEntity(ProductDTO dto) {
		if(dto == null) return null;
		
		return new Product(
				dto.getId(),
				dto.getName(),
				dto.getDescription(),
				dto.getPrice(),
				dto.getStockQuantity(),
				dto.getImageURL(),
				dto.getCategory(),
				dto.isActive()
				);


	}
	
	//////Cart

    public static CartResponse toDto(Cart cart) {
        List<CartItemResponse> itemResponses = cart.getItems()
                .stream()
                .map(MapperUtil::toItemDto)
                .collect(Collectors.toList());

        BigDecimal totalAmount = itemResponses.stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new CartResponse(
                cart.getId(),
                cart.getUserId(),
                cart.getStatus().name(),
                itemResponses,
                totalAmount
        );
    }

    public static CartItemResponse toItemDto(CartItem item) {
        return new CartItemResponse(
                item.getId(),
                item.getQuantity(),
                item.getProduct() != null ? item.getProduct().getId() : null,
                item.getProduct() != null ? item.getProduct().getName() : null,
                item.getProduct() != null ? item.getProduct().getPrice() : null
        );
    }
	
	/////order
    public static OrderDTO toDto(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setUserId(order.getCustomer().getId());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setOrderStatus(order.getOrderStatus());
        dto.setPaymentMethod(order.getPaymentMethod());
        dto.setPaymentStatus(order.getPaymentStatus());
        dto.setOrderDate(order.getOrderDate());
        dto.setShippingAddress(order.getShippingAddress());

        List<OrderItemDTO> itemDTOs = order.getItems().stream()
            .map(MapperUtil::toDto) // ✅ Use the dedicated method
            .collect(Collectors.toList());

        dto.setItems(itemDTOs);
        return dto;
    }


	
	public static OrderItemDTO toDto(OrderItem item) {
		if(item == null) return null;
		
//		return new OrderItemDTO(
//				item.getProduct().getId(),
//				item.getQuantity(),
//				item.getPrice());
		
		OrderItemDTO dto = new OrderItemDTO();
		dto.setId(item.getId());
		dto.setProductId(item.getProduct().getId());
		dto.setProductName(item.getProduct().getName());
		dto.setQuantity(item.getQuantity());
		dto.setPrice(item.getPrice());
		
		return dto;
	}
	
	//// order: dto->entity
	public static Order toEntity(OrderDTO dto, User customer, List<OrderItem> items) {
		if(dto ==null) return null;
		
		Order order = new Order();
		order.setId(dto.getId());
		order.setCustomer(customer);
		order.setItems(items);
		order.setTotalAmount(dto.getTotalAmount());
		order.setOrderStatus(dto.getOrderStatus());
		order.setPaymentMethod(dto.getPaymentMethod());
		order.setPaymentStatus(dto.getPaymentStatus());
		order.setOrderDate(dto.getOrderDate());
	    order.setShippingAddress(dto.getShippingAddress()); 
		return order;
	}
	
	public static OrderItem toEntity(OrderItemDTO dto, Product product, Order order) {
		if(dto==null) return null;
		
		OrderItem item = new OrderItem();
		
		item.setId(dto.getId());
		item.setOrder(order);
		item.setProduct(product);
		item.setQuantity(dto.getQuantity());
		item.setPrice(dto.getPrice());
		
		return item;
	}
}
