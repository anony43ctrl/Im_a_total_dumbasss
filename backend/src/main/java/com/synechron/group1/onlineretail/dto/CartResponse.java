package com.synechron.group1.onlineretail.dto;

import java.math.BigDecimal;
import java.util.List;

public class CartResponse {
	private Long id;
	private Long userId;
	private String status;
	private List<CartItemResponse> items;
	private BigDecimal totalAmount;
	
	public CartResponse() {
		// TODO Auto-generated constructor stub
	}
	public CartResponse(Long id, Long userId, String status, List<CartItemResponse> items,BigDecimal totalAmount){
		this.id = id;
		this.userId = userId;
		this.status = status;
		this.items = items;
		this.totalAmount=totalAmount;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<CartItemResponse> getItems() {
		return items;
	}
	public void setItems(List<CartItemResponse> items) {
		this.items = items;
	}
}
