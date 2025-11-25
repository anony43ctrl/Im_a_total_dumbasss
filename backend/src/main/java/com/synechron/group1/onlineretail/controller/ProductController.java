package com.synechron.group1.onlineretail.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synechron.group1.onlineretail.domain.Product;
import com.synechron.group1.onlineretail.enums.Category;
import com.synechron.group1.onlineretail.service.ProductService;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Public access or CUSTOMER access
    @GetMapping
    public ResponseEntity<List<Product>> getAllActiveProducts() {
        return ResponseEntity.ok(productService.getAllActiveProducts());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getActiveProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Public access or CUSTOMER access
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchActiveProductsByName(@RequestParam String name) {
        return ResponseEntity.ok(productService.searchActiveProductsByName(name));
    }

    // Public access or CUSTOMER access
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getActiveProductsByCategory(@PathVariable Category category) {
        return ResponseEntity.ok(productService.getActiveProductsByCategory(category));
    }
}
