package com.synechron.group1.onlineretail.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synechron.group1.onlineretail.domain.Product;
import com.synechron.group1.onlineretail.enums.Category;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Admin access
    List<Product> findByCategory(Category category);
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByPriceLessThan(Double price);
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

    // Public access
    List<Product> findByActiveTrue();
    List<Product> findByNameContainingIgnoreCaseAndActiveTrue(String name);
    List<Product> findByCategoryAndActiveTrue(Category category);
    Optional<Product> findByIdAndActiveTrue(Long id); // ✅ Corrected
}
