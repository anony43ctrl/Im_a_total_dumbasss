package com.synechron.group1.onlineretail.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synechron.group1.onlineretail.domain.Cart;
import com.synechron.group1.onlineretail.enums.CartStatus;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
	public Optional<Cart> findByUserIdAndStatus(Long userId, CartStatus status);

}
