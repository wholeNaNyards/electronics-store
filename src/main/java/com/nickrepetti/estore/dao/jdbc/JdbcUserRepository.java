package com.nickrepetti.estore.dao.jdbc;

import com.nickrepetti.estore.dao.UserRepository;
import com.nickrepetti.estore.dao.jdbc.mapper.UserRowMapper;

import com.nickrepetti.estore.model.User;

import com.nickrepetti.estore.util.UserNotFoundException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.DuplicateKeyException;

import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcUserRepository implements UserRepository {

	private final String GET_BY_ID = 
		"SELECT u.id, u.firstName, u.lastName, u.userName, "
		+ "ui.itemId, ui.quantity "
		+ "FROM Users u "
		+ "LEFT OUTER JOIN UserItems ui "
		+ "ON u.id = ui.userId "
		+ "WHERE u.id = ? ";

	private final String ADD_TO_CART = 
		"INSERT INTO UserItems(userId, itemId, quantity) "
		+ "VALUES(?, ?, ?)";
		
	private final String UPDATE_CART = 
		"UPDATE UserItems "
		+ "SET quantity = quantity + 1 "
		+ "WHERE userId = ? "
		+ "AND itemId = ?";
		
	private JdbcTemplate jdbcTemplate;

	public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;		
	}

	@Override
	public User getUser(Long userId) throws UserNotFoundException {
		try {
			return jdbcTemplate.queryForObject(
				GET_BY_ID, 
				new UserRowMapper(), 
				userId);
		}
		catch (EmptyResultDataAccessException e) {
			throw new UserNotFoundException();
		}
	}
	
	@Override
	public User addToCart(Long userId, Long itemId) {
		try {
			// Attempt to create record
			jdbcTemplate.update(
				ADD_TO_CART, 
				userId,
				itemId,
				0);
		}
		catch (DuplicateKeyException e) {
			// Record already exists
		}
		
		// Increment value
		jdbcTemplate.update(
			UPDATE_CART, 
			userId,
			itemId);
				
		return getUser(userId);
	}
}
