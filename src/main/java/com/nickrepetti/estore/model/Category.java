package com.nickrepetti.estore.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@JsonInclude(Include.NON_NULL)
public class Category {
	
	private Long id;
	private String name;
	
	public Category() {}
	
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
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(id)
			.append(name)
			.toHashCode();
	}
	
	@Override
	public boolean equals(final Object object) {
		if (object instanceof Category) {
			final Category otherCategory = (Category) object;
			
			return new EqualsBuilder()
				.append(id, otherCategory.getId())
				.append(name, otherCategory.getName())
				.isEquals();
		}
		else {
			return false;
		}
	}
}
