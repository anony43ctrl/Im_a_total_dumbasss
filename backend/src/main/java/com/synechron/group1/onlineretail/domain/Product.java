package com.synechron.group1.onlineretail.domain;

import java.math.BigDecimal;

import com.synechron.group1.onlineretail.enums.Category;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String description;
	
	private BigDecimal price;
	
	@NotNull
	private int stockQuantity;
	@NotNull
	private String imageURL;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Category category;
	private boolean active=true;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}
	
	public Product(Long id, String name, String description, BigDecimal price, @NotNull int stockQuantity,
			String imageURL, Category category, boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.stockQuantity = stockQuantity;
		this.imageURL = imageURL;
		this.category = category;
		this.active = active;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		return "Product{" +

                "id=" + id +

                ", name='" + name + '\'' +

                ", description='" + description + '\'' +

                ", price=" + price +

                ", stockQuantity=" + stockQuantity +

                ", category=" + category +

                '}';
 
	}
}
