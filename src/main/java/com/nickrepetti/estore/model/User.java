package com.nickrepetti.estore.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@JsonInclude(Include.NON_NULL)
public class User {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String userName;
	
	// User's cart maps productId to quantity
	private Map<Long, Integer> cart;
	
	public User() {}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public Map<Long, Integer> getCart() {
		return cart;
	}
	
	public void setCart(Map<Long, Integer> cart) {
		this.cart = cart;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(id)
			.append(firstName)
			.append(lastName)
			.append(userName)
			.append(cart)
			.toHashCode();
	}
	
	@Override
	public boolean equals(final Object object) {
		if (object instanceof User) {
			final User otherUser = (User) object;
			
			return new EqualsBuilder()
				.append(id, otherUser.getId())
				.append(firstName, otherUser.getFirstName())
				.append(lastName, otherUser.getLastName())
				.append(userName, otherUser.getUserName())
				.append(cart, otherUser.getCart())
				.isEquals();
		}
		else {
			return false;
		}
	}
}
