package com.synechron.group1.onlineretail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synechron.group1.onlineretail.domain.CartItem;
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    void deleteByProductId(Long productId);
}
