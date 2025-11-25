package com.synechron.group1.onlineretail.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synechron.group1.onlineretail.domain.Order;
import com.synechron.group1.onlineretail.domain.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
	public List<Order> findByCustomer(User customer);
	public List<Order> findByCustomerId(Long customerId);
	public Optional<Order> findByRazorpayOrderId(String razorpayOrderId);

}
