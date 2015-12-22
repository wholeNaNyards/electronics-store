package com.nickrepetti.estore.dao.jdbc.mapper;

import com.nickrepetti.estore.dao.jdbc.mapper.CategoryResultSetExtractor;
import com.nickrepetti.estore.model.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class CategoryRowMapper implements RowMapper<Category> {
	
	@Override
	public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new CategoryResultSetExtractor().extractData(rs);
	}
}
