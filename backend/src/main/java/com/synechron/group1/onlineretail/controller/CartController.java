package com.synechron.group1.onlineretail.controller;

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

import com.synechron.group1.onlineretail.dto.CartResponse;
import com.synechron.group1.onlineretail.dto.OrderDTO;
import com.synechron.group1.onlineretail.enums.PaymentMethod;
import com.synechron.group1.onlineretail.service.CartService;

@RestController
@RequestMapping("/api/v1/cart")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class CartController {
	
private final CartService cartService;
	
	public CartController(CartService cartService) {
		this.cartService = cartService;
		
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<CartResponse> getCart(@PathVariable Long userId){
		return ResponseEntity.ok(cartService.getCart(userId));
	}
	
	@PostMapping("/{userId}/add/{productId}")
	public ResponseEntity<CartResponse> addToCart(@PathVariable Long userId, @PathVariable Long productId, @RequestParam int quantity){
		return ResponseEntity.ok(cartService.addProductToCart(userId, productId, quantity));
	}
	
	@PutMapping("/{userId}/update/{productId}")
	public ResponseEntity<CartResponse> updateQuantity(@PathVariable Long userId, @PathVariable Long productId, @RequestParam int quantity){
		return ResponseEntity.ok(cartService.updateProductQuantity(userId, productId, quantity));
		
	}
	
	@DeleteMapping("/{userId}/remove/{productId}")
	public ResponseEntity<CartResponse> removeFromCart(@PathVariable Long userId, @PathVariable Long productId){
		return ResponseEntity.ok(cartService.removeProductFromCart(userId, productId));
	}
	
	@DeleteMapping("/{userId}/clear")
	public ResponseEntity<CartResponse> clearCart(@PathVariable Long userId){
		return ResponseEntity.ok(cartService.clearCart(userId));
	}
	
	@PostMapping("/{userId}/checkout")
	public ResponseEntity<OrderDTO> checkout(
	    @PathVariable Long userId,
	    @RequestParam PaymentMethod paymentMethod
	) throws Exception {
	    return ResponseEntity.ok(cartService.checkoutCart(userId, paymentMethod));
	}

}
