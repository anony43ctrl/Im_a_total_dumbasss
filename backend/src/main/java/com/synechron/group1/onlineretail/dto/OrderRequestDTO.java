package com.synechron.group1.onlineretail.dto;

import java.math.BigDecimal;
import java.util.List;

public class OrderRequestDTO {
	private Long customerId;
	private BigDecimal total;
	private List<OrderItemDTO> items;
	
	public OrderRequestDTO() {
		// TODO Auto-generated constructor stub
	}

	public OrderRequestDTO(Long customerId, BigDecimal total, List<OrderItemDTO> items) {
		this.customerId = customerId;
		this.total = total;
		this.items = items;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public List<OrderItemDTO> getItems() {
		return items;
	}

	public void setItems(List<OrderItemDTO> items) {
		this.items = items;
	}
	

}
