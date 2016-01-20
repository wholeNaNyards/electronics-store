package com.nickrepetti.estore.dao.jdbc.mapper;

import com.nickrepetti.estore.model.Product;
import com.nickrepetti.estore.model.Image;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ProductRowMapper implements RowMapper<Product> {
	
	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
		Product product = new Product();
		product.setId(rs.getLong("id"));
		product.setName(rs.getString("name"));
		product.setDescription(rs.getString("description"));
		product.setPrice(rs.getBigDecimal("price"));
		
		Image image = new Image();
		image.setId(rs.getLong("imageId"));
		image.setName(rs.getString("imageName"));
		
		product.setImage(image);
		
		return product;
	}
}
