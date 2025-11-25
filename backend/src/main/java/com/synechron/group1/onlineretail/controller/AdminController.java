package com.synechron.group1.onlineretail.controller;
import com.synechron.group1.onlineretail.domain.Product;
import com.synechron.group1.onlineretail.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/products")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class AdminController {
    private final ProductService productService;
    public AdminController(ProductService productService) {
        this.productService = productService;

    }


    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.createProduct(product));

    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    // ✅ Soft delete product

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deactivateProduct(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Reactivate product

    @PutMapping("/{id}/reactivate")
    public ResponseEntity<String> reactivateProduct(@PathVariable Long id) {
        productService.reactivateProduct(id);
        return ResponseEntity.ok("Product reactivated successfully.");

    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // ✅ Get one product (admin sees even if inactive)

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

}
 