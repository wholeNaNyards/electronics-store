package com.nickrepetti.estore.dao;

import com.nickrepetti.estore.model.Product;
import com.nickrepetti.estore.model.User;

import com.nickrepetti.estore.util.UserNotFoundException;

import java.util.List;

public interface UserRepository {
	
	public User getUser(Long id) throws UserNotFoundException;
	
	public List<Product> getCartProducts(Long userId, boolean sortAZ, 
								   boolean sortPrice, int limit, int offset);
	
	public void addToCart(Long userId, Long productId);
	
	public void removeFromCart(Long userId, Long productId);
}
