package com.nickrepetti.estore.dao.jdbc;

import com.nickrepetti.estore.dao.UserRepository;

import com.nickrepetti.estore.dao.jdbc.mapper.ItemRowMapper;
import com.nickrepetti.estore.dao.jdbc.mapper.UserRowMapper;

import com.nickrepetti.estore.model.Item;
import com.nickrepetti.estore.model.User;

import com.nickrepetti.estore.util.UserNotFoundException;
import com.nickrepetti.estore.util.EmptyCartException;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.DuplicateKeyException;

import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcUserRepository implements UserRepository {

	private final String GET_BY_ID = 
		"SELECT u.id, u.firstName, u.lastName, u.userName, "
		+ "ui.itemId "
		+ "FROM Users u "
		+ "INNER JOIN UserItems ui "
		+ "ON u.id = ui.userId "
		+ "WHERE u.id = ? ";

	private final String GET_USER_ITEMS =
		"SELECT i.id, i.name, i.description, i.price, "
		+ "im.id as imageId, im.name as imageName "
		+ "FROM Items i "
		+ "INNER JOIN Images im "
		+ "ON i.imageId = im.id "
		+ "INNER JOIN UserItems ui "
		+ "ON ui.itemId = i.id "
		+ "WHERE ui.userId = ? ";

	private final String GET_USER_ITEMS_COUNT =
		"SELECT COUNT(*) "
		+ "FROM Items i "
		+ "INNER JOIN UserItems ui "
		+ "ON ui.itemId = i.id "
		+ "WHERE ui.userId = ?;";
		
	private final String GET_USER_ITEMS_SUBTOTAL =
		"SELECT SUM(i.price) AS subtotal "
		+ "FROM Items i "
		+ "INNER JOIN UserItems ui "
		+ "ON ui.itemId = i.id "
		+ "WHERE ui.userId = ?;";
		
	private String SORT_AZ_DESC = "ORDER BY i.name DESC, i.price ";
	
	private String SORT_AZ_ASC = "ORDER BY i.name ASC, i.price ";
	
	private String SORT_PRICE_DESC = "ORDER BY i.price DESC, i.name ";
	
	private String SORT_PRICE_ASC = "ORDER BY i.price ASC, i.name ";
	
	private String LIMIT_OFFSET = "LIMIT ? OFFSET ?;";
	
	private final String ADD_TO_CART = 
		"INSERT INTO UserItems(userId, itemId) "
		+ "VALUES(?, ?)";
		
	private final String REMOVE_FROM_CART = 
		"DELETE FROM UserItems ui "
		+ "WHERE ui.userId = ? "
		+ "AND ui.itemId = ?;";
	
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
	public List<Item> getCartItems(Long userId, boolean sortAZ, 
								   boolean sortPrice, int limit, int offset) {
		
		String query = getSortedQuery(GET_USER_ITEMS, sortAZ, sortPrice);
		
		// Get requested items
		List<Item> items = jdbcTemplate.query(
			query, 
			new ItemRowMapper(),
			userId,
			limit,
			offset);
		
		if (items.size() == 0) {
			throw new EmptyCartException();
		}
		
		// Get total item count
		Integer totalItemCount = 
			jdbcTemplate.queryForObject(
				GET_USER_ITEMS_COUNT, 
				Integer.class, 
				userId);

		Integer subtotal = 
				jdbcTemplate.queryForObject(
					GET_USER_ITEMS_SUBTOTAL, 
					Integer.class, 
					userId);
				
		// Place total count and subtotal in first item brought back
		// TODO: Bring back in Custom Message Converter instead
		Item firstItem = items.get(0);
		
		firstItem.setTotalItemCount(totalItemCount);
		firstItem.setSubtotal(subtotal);
		
		return items;
	}
	
	@Override
	public void addToCart(Long userId, Long itemId) {
		jdbcTemplate.update(
			ADD_TO_CART, 
			userId,
			itemId);
	}
	
	@Override
	public void removeFromCart(Long userId, Long itemId) {
		jdbcTemplate.update(
			REMOVE_FROM_CART, 
			userId,
			itemId);
	}
	
	private String getSortedQuery(String query, boolean sortAZ, boolean sortPrice) {
		StringBuilder stringBuilder = new StringBuilder(query);
		
		if (!sortAZ && !sortPrice) {
			stringBuilder.append(SORT_AZ_DESC);
		}
		else if (!sortAZ && sortPrice) {
			stringBuilder.append(SORT_PRICE_ASC);
		}
		else if (sortAZ && !sortPrice) {
			stringBuilder.append(SORT_AZ_ASC);
		}
		else {
			stringBuilder.append(SORT_PRICE_DESC);
		}		
		
		stringBuilder.append(LIMIT_OFFSET);
		
		return stringBuilder.toString();
	}
}
