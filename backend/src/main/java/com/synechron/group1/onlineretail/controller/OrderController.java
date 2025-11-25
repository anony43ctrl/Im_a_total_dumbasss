package com.synechron.group1.onlineretail.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synechron.group1.onlineretail.dto.OrderDTO;
import com.synechron.group1.onlineretail.enums.OrderStatus;
import com.synechron.group1.onlineretail.service.OrderService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/v1/order")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class OrderController {
	
	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@PostMapping("/create")
	public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderRequest) throws Exception {
		OrderDTO order = orderService.placeOrder(orderRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(order);
	}
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<List<OrderDTO>> getOrdersByCustomer(@PathVariable Long customerId) {
	    return ResponseEntity.ok(orderService.getOrderbbyCustomer(customerId));
	}

//	
	@GetMapping
	public ResponseEntity<List<OrderDTO>> getAllOrders(){
		return ResponseEntity.ok(orderService.getAllOrders());
	}
//	
	@PutMapping("/{orderId}/status")
	public ResponseEntity<OrderDTO> updatestatus(@PathVariable Long orderId, @RequestParam OrderStatus orderStatus) {
		return ResponseEntity.ok(orderService.updateOrderStatus(orderId, orderStatus));
	}
//	
	@DeleteMapping("/{orderId}")
	public ResponseEntity<Void> cancelOrder(@PathVariable Long orderId) {
		orderService.cancelOrder(orderId);
		return ResponseEntity.noContent().build();
	}
}
