package com.nickrepetti.estore.dao.jdbc;

import com.nickrepetti.estore.dao.CategoryRepository;
import com.nickrepetti.estore.dao.jdbc.mapper.CategoryRowMapper;

import com.nickrepetti.estore.model.Category;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcCategoryRepository implements CategoryRepository {

	private final String GET_ALL_CATEGORIES = 
		"SELECT c.id, c.name "
		+ "FROM Categories c";

	private JdbcTemplate jdbcTemplate;

	public JdbcCategoryRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;		
	}

	@Override
	public List<Category> getCategories() {
		return jdbcTemplate.query(
			GET_ALL_CATEGORIES, 
			new CategoryRowMapper());
	}
}
