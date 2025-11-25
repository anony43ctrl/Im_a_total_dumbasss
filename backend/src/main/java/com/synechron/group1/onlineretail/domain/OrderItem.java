package com.synechron.group1.onlineretail.domain;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_items")
public class OrderItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;
	
//	@Column
//	private Long productId;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@Column(nullable = false)
	private int quantity;
	
	@Column(nullable = false, precision = 12, scale = 2)
	private BigDecimal price;
	
	public OrderItem() {
	
	}
	
	public OrderItem(Product product, int quantity, BigDecimal price) {
		this.product = product;
		this.quantity = quantity;
		this.price = price;
	}
	
	public OrderItem(Order order,Product product, int quantity, BigDecimal price) {
		this.order = order;
		this.product = product;
		this.quantity = quantity;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
