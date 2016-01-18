package com.nickrepetti.estore.dao.jdbc.mapper;

import com.nickrepetti.estore.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.ResultSetExtractor;

public class UserResultSetExtractor implements ResultSetExtractor<User> {
	
	@Override
	public User extractData(ResultSet rs) throws SQLException {
		// Grab User's info from first row
		User user = new User();
		user.setId(rs.getLong("id"));
		user.setFirstName(rs.getString("firstName"));
		user.setLastName(rs.getString("lastName"));
		user.setUserName(rs.getString("userName"));
		
		Map<Long, Integer> cart = new HashMap<Long, Integer>();
		
		Long firstItemId = rs.getLong("itemId");

		// Grab first cart item. Value of 0 means column was NULL
		if (firstItemId != 0) {
			cart.put(firstItemId, 1);
		}
		
		// Get the rest of the items from User's cart.
		while (rs.next()) {
			cart.put(rs.getLong("itemId"), 1);
		}
		
		user.setCart(cart);
		
		return user;
	}
}
