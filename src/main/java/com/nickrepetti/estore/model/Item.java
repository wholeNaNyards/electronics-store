package com.nickrepetti.estore.model;

import com.nickrepetti.estore.model.Category;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@JsonInclude(Include.NON_NULL)
public class Item {
	
	private Long id;
	private String name;
	private String description;
	private BigDecimal price;
	private int rating;
	private Image image;
	
	// Item's list of categories that it belongs to
	private Map<Long, Boolean> categories;
	
	public Item() {}
	
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
			.append(image)
			.append(categories)
			.toHashCode();
	}
	
	@Override
	public boolean equals(final Object object) {
		if (object instanceof Item) {
			final Item otherItem = (Item) object;
			
			return new EqualsBuilder()
				.append(id, otherItem.getId())
				.append(name, otherItem.getName())
				.append(description, otherItem.getDescription())
				.append(price, otherItem.getPrice())
				.append(rating, otherItem.getRating())
				.append(image, otherItem.getImage())
				.append(categories, otherItem.getCategories())
				.isEquals();
		}
		else {
			return false;
		}
	}
}
