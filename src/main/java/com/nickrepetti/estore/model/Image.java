package com.nickrepetti.estore.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@JsonInclude(Include.NON_NULL)
public class Image {
	
	private Long id;
	private String name;
	
	public Image() {}
	
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
		if (object instanceof Image) {
			final Image otherImage = (Image) object;
			
			return new EqualsBuilder()
				.append(id, otherImage.getId())
				.append(name, otherImage.getName())
				.isEquals();
		}
		else {
			return false;
		}
	}
}
