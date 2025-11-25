package com.synechron.group1.onlineretail.dto;

import java.math.BigDecimal;

public class OrderItemDTO {
	private Long id;
	private String productName;
	private Long productId;
	private int quantity;
	private BigDecimal price;
	
	public OrderItemDTO() {
		// TODO Auto-generated constructor stub
	}

	public OrderItemDTO(String productName,Long productId, int quantity, BigDecimal price) {
		this.productName = productName;
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
	}
	

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	

}
