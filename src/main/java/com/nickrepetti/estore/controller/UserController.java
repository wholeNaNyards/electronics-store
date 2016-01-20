package com.nickrepetti.estore.controller;

import com.nickrepetti.estore.dao.UserRepository;

import com.nickrepetti.estore.model.Product;
import com.nickrepetti.estore.model.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {
		
	private UserRepository userRepository;
	
	@Autowired
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public User getUser(
		@PathVariable Long userId) {
			
		return userRepository.getUser(userId);
	}
	
	@RequestMapping(value = "/{userId}/products", method = RequestMethod.GET)
	public List<Product> getCartProducts(
		@PathVariable Long userId,
		@RequestParam(value="sortAZ", defaultValue="false") boolean sortAZ,
		@RequestParam(value="sortPrice", defaultValue="false") boolean sortPrice,
		@RequestParam(value="limit", defaultValue="20") int limit,
		@RequestParam(value="offset", defaultValue="0") int offset) {
		
		return userRepository.getCartProducts(
			userId, sortAZ, sortPrice, limit, offset);
	}
	
	@RequestMapping(value="/{userId}/products/{productId}", method=RequestMethod.POST)
	public void addToCart(
		@PathVariable Long userId,
		@PathVariable Long productId) {
		
		userRepository.addToCart(userId, productId);
	}
	
	@RequestMapping(value="/{userId}/products/{productId}", method=RequestMethod.DELETE)
	public void removeFromCart(
		@PathVariable Long userId,
		@PathVariable Long productId) {
		
		userRepository.removeFromCart(userId, productId);
	}
}
