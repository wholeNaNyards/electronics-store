package com.nickrepetti.estore.dao.jdbc;

import com.nickrepetti.estore.dao.UserRepository;

import com.nickrepetti.estore.dao.jdbc.mapper.ProductRowMapper;
import com.nickrepetti.estore.dao.jdbc.mapper.UserRowMapper;

import com.nickrepetti.estore.model.Product;
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
		+ "up.productId "
		+ "FROM Users u "
		+ "LEFT OUTER JOIN UserProducts up "
		+ "ON u.id = up.userId "
		+ "WHERE u.id = ? ";

	private final String GET_USER_PRODUCTS =
		"SELECT p.id, p.name, p.description, p.price, "
		+ "i.id as imageId, i.name as imageName "
		+ "FROM Products p "
		+ "INNER JOIN Images i "
		+ "ON p.imageId = i.id "
		+ "INNER JOIN UserProducts up "
		+ "ON up.productId = p.id "
		+ "WHERE up.userId = ? ";

	private final String GET_USER_PRODUCTS_COUNT =
		"SELECT COUNT(*) "
		+ "FROM Products p "
		+ "INNER JOIN UserProducts up "
		+ "ON up.productId = p.id "
		+ "WHERE up.userId = ?;";
		
	private final String GET_USER_PRODUCTS_SUBTOTAL =
		"SELECT SUM(p.price) AS subtotal "
		+ "FROM Products p "
		+ "INNER JOIN UserProducts up "
		+ "ON up.productId = p.id "
		+ "WHERE up.userId = ?;";
		
	private String SORT_AZ_DESC = "ORDER BY p.name DESC, p.price ";
	
	private String SORT_AZ_ASC = "ORDER BY p.name ASC, p.price ";
	
	private String SORT_PRICE_DESC = "ORDER BY p.price DESC, p.name ";
	
	private String SORT_PRICE_ASC = "ORDER BY p.price ASC, p.name ";
	
	private String LIMIT_OFFSET = "LIMIT ? OFFSET ?;";
	
	private final String ADD_TO_CART = 
		"INSERT INTO UserProducts(userId, productId) "
		+ "VALUES(?, ?)";
		
	private final String REMOVE_FROM_CART = 
		"DELETE FROM UserProducts up "
		+ "WHERE up.userId = ? "
		+ "AND up.productId = ?;";
	
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
	public List<Product> getCartProducts(Long userId, boolean sortAZ, 
								   boolean sortPrice, int limit, int offset) {
		
		String query = getSortedQuery(GET_USER_PRODUCTS, sortAZ, sortPrice);
		
		// Get requested products
		List<Product> products = jdbcTemplate.query(
			query, 
			new ProductRowMapper(),
			userId,
			limit,
			offset);
		
		if (products.size() == 0) {
			throw new EmptyCartException();
		}
		
		// Get total product count
		Integer totalProductCount = 
			jdbcTemplate.queryForObject(
				GET_USER_PRODUCTS_COUNT, 
				Integer.class, 
				userId);

		Integer subtotal = 
				jdbcTemplate.queryForObject(
					GET_USER_PRODUCTS_SUBTOTAL, 
					Integer.class, 
					userId);
				
		// Place total count and subtotal in first product brought back
		// TODO: Bring back in Custom Message Converter instead
		Product firstProduct = products.get(0);
		
		firstProduct.setTotalProductCount(totalProductCount);
		firstProduct.setSubtotal(subtotal);
		
		return products;
	}
	
	@Override
	public void addToCart(Long userId, Long productId) {
		jdbcTemplate.update(
			ADD_TO_CART, 
			userId,
			productId);
	}
	
	@Override
	public void removeFromCart(Long userId, Long productId) {
		jdbcTemplate.update(
			REMOVE_FROM_CART, 
			userId,
			productId);
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
