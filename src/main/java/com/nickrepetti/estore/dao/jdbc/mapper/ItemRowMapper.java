package com.nickrepetti.estore.dao.jdbc.mapper;

import com.nickrepetti.estore.model.Item;
import com.nickrepetti.estore.model.Image;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ItemRowMapper implements RowMapper<Item> {
	
	@Override
	public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
		Item item = new Item();
		item.setId(rs.getLong("id"));
		item.setName(rs.getString("name"));
		item.setDescription(rs.getString("description"));
		item.setPrice(rs.getBigDecimal("price"));
		
		Image image = new Image();
		image.setId(rs.getLong("imageId"));
		image.setName(rs.getString("imageName"));
		
		item.setImage(image);
		
		return item;
	}
}
