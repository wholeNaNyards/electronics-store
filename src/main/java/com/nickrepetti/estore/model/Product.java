package com.nickrepetti.estore.model;

import com.nickrepetti.estore.model.Category;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@JsonInclude(Include.NON_EMPTY)
public class Product {
	
	private Long id;
	private String name;
	private String description;
	private BigDecimal price;
	private int rating;
	private int totalProductCount;
	private int subtotal;
	private Image image;
	
	// Product's list of categories that it belongs to
	private Map<Long, Boolean> categories;
	
	public Product() {}
	
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
	
	public int getRating() {
		return rating;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}
		
	public int getTotalProductCount() {
		return totalProductCount;
	}
	
	public void setTotalProductCount(int totalProductCount) {
		this.totalProductCount = totalProductCount;
	}
	
	public int getSubtotal() {
		return subtotal;
	}
	
	public void setSubtotal(int subtotal) {
		this.subtotal = subtotal;
	}
	
	public Image getImage() {
		return image;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}
	
	public Map<Long, Boolean> getCategories() {
		return categories;
	}
	
	public void setCategories(Map<Long, Boolean> categories) {
		this.categories = categories;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(id)
			.append(name)
			.append(description)
			.append(price)
			.append(rating)
			.append(totalProductCount)
			.append(subtotal)
			.append(image)
			.append(categories)
			.toHashCode();
	}
	
	@Override
	public boolean equals(final Object object) {
		if (object instanceof Product) {
			final Product otherProduct = (Product) object;
			
			return new EqualsBuilder()
				.append(id, otherProduct.getId())
				.append(name, otherProduct.getName())
				.append(description, otherProduct.getDescription())
				.append(price, otherProduct.getPrice())
				.append(rating, otherProduct.getRating())
				.append(totalProductCount, otherProduct.getTotalProductCount())
				.append(subtotal, otherProduct.getSubtotal())
				.append(image, otherProduct.getImage())
				.append(categories, otherProduct.getCategories())
				.isEquals();
		}
		else {
			return false;
		}
	}
}
