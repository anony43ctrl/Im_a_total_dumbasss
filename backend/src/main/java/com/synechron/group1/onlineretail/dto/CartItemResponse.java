package com.synechron.group1.onlineretail.dto;

import java.math.BigDecimal;

public class CartItemResponse {
	
	private Long id;
	private int quantity;
	private Long productId;
	private String productName;
	private BigDecimal price;
	
	public CartItemResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public CartItemResponse(Long id,int quantity, Long productId, String productName, BigDecimal price) {
		this.id = id;
		this.quantity = quantity;
		this.productId = productId;
		this.productName = productName;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
}
