package com.nickrepetti.estore.dao.jdbc.mapper;

import com.nickrepetti.estore.dao.jdbc.mapper.ItemResultSetExtractor;
import com.nickrepetti.estore.model.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ItemRowMapper implements RowMapper<Item> {
	
	@Override
	public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new ItemResultSetExtractor().extractData(rs);
	}
}
