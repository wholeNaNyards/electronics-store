package com.nickrepetti.estore.dao.jdbc.mapper;

import com.nickrepetti.estore.model.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

public class CategoryResultSetExtractor implements ResultSetExtractor<Category> {
	
	@Override
	public Category extractData(ResultSet rs) throws SQLException {
		Category category = new Category();
		
		category.setId(rs.getLong("id"));
		category.setName(rs.getString("name"));
		
		return category;
	}
}
