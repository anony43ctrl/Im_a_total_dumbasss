package com.synechron.group1.onlineretail.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.synechron.group1.onlineretail.domain.Product;
import com.synechron.group1.onlineretail.enums.Category;
import com.synechron.group1.onlineretail.repository.CartItemRepository;
import com.synechron.group1.onlineretail.repository.ProductRepository;

import jakarta.transaction.Transactional;
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CartItemRepository cartRepository;

    public ProductServiceImpl(ProductRepository productRepository, CartItemRepository cartRepository) {
		super();
		this.productRepository = productRepository;
		this.cartRepository = cartRepository;
	}


	// --- Admin Methods ---

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product updatedProduct) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(updatedProduct.getName());
                    existingProduct.setDescription(updatedProduct.getDescription());
                    existingProduct.setPrice(updatedProduct.getPrice());
                    existingProduct.setStockQuantity(updatedProduct.getStockQuantity());
                    existingProduct.setCategory(updatedProduct.getCategory());
                    existingProduct.setImageURL(updatedProduct.getImageURL());
                    return productRepository.save(existingProduct);
                })
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
    }

    @Override
    @Transactional
    public Product deactivateProduct(Long id) {
        return productRepository.findById(id)
            .map(existingProduct -> {
                if (!existingProduct.isActive()) {
                    throw new IllegalStateException("Product is already inactive");
                }

                cartRepository.deleteByProductId(existingProduct.getId()); // ✅ now inside a transaction

                existingProduct.setActive(false);
                return productRepository.save(existingProduct);
            })
            .orElseThrow(() -> new IllegalArgumentException("Product no longer available"));
    }


    @Override
    public Product reactivateProduct(Long id) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    if (existingProduct.isActive()) {
                        throw new IllegalStateException("Product is already active");
                    }
                    existingProduct.setActive(true);
                    return productRepository.save(existingProduct);
                })
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll(); // Admin sees all
    }
    @Override
	public Optional<Product> getProductById(Long id) {
		return productRepository.findById(id);
	}
    @Override
    public List<Product> searchProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name); // Admin sees all
    }

    @Override
    public List<Product> getProductsByCategory(Category category) {
        return productRepository.findByCategory(category); // Admin sees all
    }

    // --- Public Methods ---
    @Override
    public Optional<Product> getActiveProductById(Long id) {
        return productRepository.findByIdAndActiveTrue(id); // ✅ Corrected
    }

    @Override
    public List<Product> getAllActiveProducts() {
        return productRepository.findByActiveTrue(); // Only active products
    }

    @Override
    public List<Product> searchActiveProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCaseAndActiveTrue(name); // Only active
    }

    @Override
    public List<Product> getActiveProductsByCategory(Category category) {
        return productRepository.findByCategoryAndActiveTrue(category); // Only active
    }

	
}
