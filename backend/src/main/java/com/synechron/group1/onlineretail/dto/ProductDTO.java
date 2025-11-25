package com.synechron.group1.onlineretail.dto;

import java.math.BigDecimal;

import com.synechron.group1.onlineretail.enums.Category;

public class ProductDTO {
	
	private Long id;
	private String name;
	private String description;	
	private BigDecimal price;
	private int stockQuantity;
	private Category category;
	private String imageURL;
	private boolean active;

	public ProductDTO() {
		// TODO Auto-generated constructor stub
	}
	

	public ProductDTO(Long id, String name, String description, BigDecimal price, int stockQuantity, Category category,
			String imageURL, boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.stockQuantity = stockQuantity;
		this.category = category;
		this.imageURL = imageURL;
		this.active=active;
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
	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
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

