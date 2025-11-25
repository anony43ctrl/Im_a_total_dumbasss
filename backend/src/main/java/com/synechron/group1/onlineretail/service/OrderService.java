package com.synechron.group1.onlineretail.service;

import java.util.List;

import com.synechron.group1.onlineretail.dto.OrderDTO;
import com.synechron.group1.onlineretail.enums.OrderStatus;

public interface OrderService {
//	public OrderResponse createOrder(OrderRequest orderRequest);
//	public List<OrderResponse> getOrdersByCustomer(Long customerId);
//	public List<OrderResponse> getAllOrders();
//	public OrderResponse updateOrderStatus(Long orderId, OrderStatus status);
//	void cancelOrder(Long orderId);
//	
//	public Order createOrderFromCart(Long userId);
//	
//	public Order placeOrder(OrderRequest orderRequest);
	
	public OrderDTO placeOrder(OrderDTO orderDTO) throws Exception;
		
	public OrderDTO getOrderById(Long id);
	
	public List<OrderDTO> getOrderbbyCustomer(Long customerId);
	
	public List<OrderDTO> getAllOrders();
	
	public OrderDTO updateOrderStatus(Long is, OrderStatus status);
	
	public void cancelOrder(Long orderId);


}
