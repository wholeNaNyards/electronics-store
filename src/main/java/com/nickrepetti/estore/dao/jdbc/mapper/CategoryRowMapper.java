package com.nickrepetti.estore.dao.jdbc.mapper;

import com.nickrepetti.estore.model.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class CategoryRowMapper implements RowMapper<Category> {
	
	@Override
	public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
		Category category = new Category();
		
		category.setId(rs.getLong("id"));
		category.setName(rs.getString("name"));
		
		return category;
	}
}
