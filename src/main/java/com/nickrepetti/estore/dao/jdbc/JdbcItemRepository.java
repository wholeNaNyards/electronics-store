package com.nickrepetti.estore.dao.jdbc;

import com.nickrepetti.estore.dao.ItemRepository;
import com.nickrepetti.estore.dao.jdbc.mapper.ItemRowMapper;

import com.nickrepetti.estore.model.Item;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcItemRepository implements ItemRepository {

	private final String GET_ALL_ITEMS = 
		"SELECT i.id, i.name, i.description, i.price, i.rating, "
		+ "im.id AS imageId, im.name AS imageName, "
		+ "ic.categoryId "
		+ "FROM Items i "
		+ "INNER JOIN Images im "
		+ "ON i.imageId = im.id "
		+ "INNER JOIN ItemCategories ic "
		+ "ON i.id = ic.itemId";

	private JdbcTemplate jdbcTemplate;

	public JdbcItemRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;		
	}

	@Override
	public List<Item> getItems() {
		return jdbcTemplate.query(
			GET_ALL_ITEMS, 
			new ItemRowMapper());
	}
}
