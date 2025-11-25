package com.synechron.group1.onlineretail.service;

import java.util.List;
import java.util.Optional;

import com.synechron.group1.onlineretail.domain.Product;
import com.synechron.group1.onlineretail.enums.Category;
public interface ProductService {
	   // --- Public ---
	   Optional<Product> getActiveProductById(Long id);
	   List<Product> getAllActiveProducts();
	   List<Product> searchActiveProductsByName(String name);
	   List<Product> getActiveProductsByCategory(Category category);
	   // --- Admin ---
	   Product createProduct(Product product);
	   Product updateProduct(Long id, Product product);
	   Product deactivateProduct(Long id);
	   Product reactivateProduct(Long id);
	   Optional<Product> getProductById(Long id);
	   List<Product> getAllProducts();
	   List<Product> searchProductsByName(String name);
	   List<Product> getProductsByCategory(Category category);
	   
	}