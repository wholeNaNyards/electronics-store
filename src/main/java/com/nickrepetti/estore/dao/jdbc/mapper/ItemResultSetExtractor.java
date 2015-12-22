package com.nickrepetti.estore.dao.jdbc.mapper;

import com.nickrepetti.estore.model.Image;
import com.nickrepetti.estore.model.Item;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.ResultSetExtractor;

public class ItemResultSetExtractor implements ResultSetExtractor<Item> {
	
	@Override
	public Item extractData(ResultSet rs) throws SQLException {
		Item item = new Item();
		item.setId(rs.getLong("id"));
		item.setName(rs.getString("name"));
		item.setDescription(rs.getString("description"));
		item.setPrice(rs.getBigDecimal("price"));
		item.setRating(rs.getInt("rating"));
		
		Image image = new Image();
		image.setId(rs.getLong("imageId"));
		image.setName(rs.getString("imageName"));
		
		item.setImage(image);
			
		Map<Long, Boolean> categories = new HashMap<Long, Boolean>();
		
		do {
			categories.put(rs.getLong("categoryId"), true);
		}
		while (rs.next());
		
		item.setCategories(categories);
		
		return item;
	}
}
