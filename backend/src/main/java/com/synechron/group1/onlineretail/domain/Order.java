package com.synechron.group1.onlineretail.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.synechron.group1.onlineretail.enums.OrderStatus;
import com.synechron.group1.onlineretail.enums.PaymentMethod;
import com.synechron.group1.onlineretail.enums.PaymentStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="userId")
	private User customer;
	

	@Column(nullable = false)
	private String shippingAddress;
	
	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;
	
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	
	@Column(nullable = false, precision = 12, scale = 2)
	private BigDecimal totalAmount;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<OrderItem> items = new ArrayList<OrderItem>();

	private LocalDateTime orderDate;
	
	@Column(name = "razorpay_order_id")
	private String razorpayOrderId;

	public String getRazorpayOrderId() {
		return razorpayOrderId;
	}

	public void setRazorpayOrderId(String razorpayOrderId) {
		this.razorpayOrderId = razorpayOrderId;
	}

	public String getRazorpayPaymentId() {
		return razorpayPaymentId;
	}

	public void setRazorpayPaymentId(String razorpayPaymentId) {
		this.razorpayPaymentId = razorpayPaymentId;
	}

	@Column(name = "razorpay_payment_id")
	private String razorpayPaymentId;


	
	public Order() {
		// TODO Auto-generated constructor stub
	}
	
	public Order(User customer, List<OrderItem> items, BigDecimal totalAmount) {
		this.customer = customer;
		this.items = items;
		this.totalAmount = totalAmount;
	}
	
	public Order(Long id) {
		this.id = id;
		this.orderStatus = OrderStatus.PENDING;
		this.orderDate = LocalDateTime.now();
	}

	
	public void addItem(OrderItem item) {
		if(items == null) {
			items = new ArrayList<OrderItem>();
		}
		items.add(item);
		item.setOrder(this);
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}
//	public Long getCustomerId() {
//		return customer_id;
//	}
//
//	public void setCustomerId(Long customerId) {
//		this.customer_id = customerId;
//	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
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

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
	
	
}
