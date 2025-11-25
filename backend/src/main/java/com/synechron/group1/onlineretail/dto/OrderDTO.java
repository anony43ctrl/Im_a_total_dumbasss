package com.synechron.group1.onlineretail.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.synechron.group1.onlineretail.enums.OrderStatus;
import com.synechron.group1.onlineretail.enums.PaymentMethod;
import com.synechron.group1.onlineretail.enums.PaymentStatus;

public class OrderDTO {
	private Long id;
	
	@JsonProperty("userId")
	private Long userId;
	private List<OrderItemDTO> items;
	private BigDecimal totalAmount;
	private OrderStatus orderStatus;
	private PaymentMethod paymentMethod;
	private PaymentStatus paymentStatus;
	private LocalDateTime orderDate;
	private String shippingAddress;

	public OrderDTO() {
		// TODO Auto-generated constructor stub
	}

	public OrderDTO(Long id, Long userId, List<OrderItemDTO> items, BigDecimal totalAmount, OrderStatus orderStatus,
			PaymentMethod paymentMethod, PaymentStatus paymentStatus, LocalDateTime orderDate, String shippingAddress) {
		super();
		this.id = id;
		this.userId = userId;
		this.items = items;
		this.totalAmount = totalAmount;
		this.orderStatus = orderStatus;
		this.paymentMethod = paymentMethod;
		this.paymentStatus = paymentStatus;
		this.orderDate = orderDate;
		this.shippingAddress=shippingAddress;
	}

	public String getShippingAddress() {
	    return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
	    this.shippingAddress = shippingAddress;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<OrderItemDTO> getItems() {
		return items;
	}

	public void setItems(List<OrderItemDTO> items) {
		this.items = items;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	

}
