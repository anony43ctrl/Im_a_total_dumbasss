package com.synechron.group1.onlineretail.service;

import com.synechron.group1.onlineretail.domain.Cart;
import com.synechron.group1.onlineretail.dto.CartResponse;
import com.synechron.group1.onlineretail.dto.OrderDTO;
import com.synechron.group1.onlineretail.enums.PaymentMethod;

public interface CartService {

	public Cart getOrCreateCart(Long userId);
	
	public CartResponse addProductToCart(Long userId, Long productId, int quantity);
	
	public CartResponse updateProductQuantity(Long userId, Long productId, int quantity);
	
	public CartResponse removeProductFromCart(Long userId, Long productId);
	
	public CartResponse clearCart(Long userId);
	
	public OrderDTO checkoutCart(Long userId, PaymentMethod paymentMethod) throws Exception;
	
	public CartResponse getCart(Long userId);
}
