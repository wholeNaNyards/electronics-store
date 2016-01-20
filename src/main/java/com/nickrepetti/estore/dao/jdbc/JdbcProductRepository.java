package com.nickrepetti.estore.dao.jdbc;

import com.nickrepetti.estore.dao.ProductRepository;
import com.nickrepetti.estore.dao.jdbc.mapper.ProductRowMapper;

import com.nickrepetti.estore.model.Product;

import com.nickrepetti.estore.util.ProductNotFoundException;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcProductRepository implements ProductRepository {

	// No category specified - Grab all products
	private final String GET_ALL_PRODUCTS_NO_CATEGORY =
		"SELECT p.id, p.name, p.description, p.price, "
		+ "i.id as imageId, i.name as imageName "
		+ "FROM Products p "
		+ "INNER JOIN Images i "
		+ "ON p.imageId = i.id "
		+ "WHERE LOWER(p.name) LIKE '%' || ? || '%' "
		+ "AND p.price >= ? AND p.price <= ? ";
		
	private final String GET_PRODUCT_COUNT_NO_CATEGORY =
		"SELECT COUNT(*) "
		+ "FROM Products p "
		+ "WHERE LOWER(p.name) LIKE '%' || ? || '%' "
		+ "AND p.price >= ? AND p.price <= ?;";
		
	// Category specified
	private final String GET_ALL_PRODUCTS_WITH_CATEGORY = 
		"SELECT p.id, p.name, p.description, p.price, "
		+ "i.id AS imageId, i.name AS imageName "
		+ "FROM Products p "
		+ "INNER JOIN Images i "
		+ "ON p.imageId = i.id "
		+ "INNER JOIN ProductCategories pc "
		+ "ON p.id = pc.productId "
		+ "WHERE LOWER(p.name) LIKE '%' || ? || '%' "
		+ "AND p.price >= ? AND p.price <= ? AND pc.categoryId = ? ";
		
	private final String GET_PRODUCT_COUNT_WITH_CATEGORY = 
		"SELECT COUNT(*) "
		+ "FROM Products p "
		+ "INNER JOIN ProductCategories pc "
		+ "ON p.id = pc.productId "
		+ "WHERE LOWER(p.name) LIKE '%' || ? || '%' "
		+ "AND p.price >= ? AND p.price <= ? AND pc.categoryId = ?;";
		
	private String SORT_AZ_DESC = "ORDER BY p.name DESC, p.price ";
	private String SORT_AZ_ASC = "ORDER BY p.name ASC, p.price ";
	private String SORT_PRICE_DESC = "ORDER BY p.price DESC, p.name ";
	private String SORT_PRICE_ASC = "ORDER BY p.price ASC, p.name ";
	private String LIMIT_OFFSET = "LIMIT ? OFFSET ?;";
		
	private JdbcTemplate jdbcTemplate;

	public JdbcProductRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;		
	}

	@Override
	public List<Product> getProducts(String searchValue, int minPrice, int maxPrice,
							   int categoryId, boolean sortAZ, 
							   boolean sortPrice, int limit, int offset) {
		List<Product> products;
		
		if (categoryId != 0) {
			String query = getSortedQuery(GET_ALL_PRODUCTS_WITH_CATEGORY, 
										  sortAZ, sortPrice);
			
			// Get requested products
			products = jdbcTemplate.query(
				query, 
				new ProductRowMapper(),
				searchValue,
				minPrice,
				maxPrice,
				categoryId,
				limit,
				offset);
		
			if (products.size() == 0) {
				throw new ProductNotFoundException();
			}
		
			// Get total product count
			Integer totalProductCount = 
				jdbcTemplate.queryForObject(
					GET_PRODUCT_COUNT_WITH_CATEGORY, 
					Integer.class, 
					searchValue,
					minPrice,
					maxPrice,
					categoryId);
					
			// Place total count in first product brought back
			// TODO: Bring back in Custom Message Converter instead
			products.get(0).setTotalProductCount(totalProductCount);
		}
		// No category selected (categoryId == 0)
		else {
			String query = getSortedQuery(GET_ALL_PRODUCTS_NO_CATEGORY, 
										  sortAZ, sortPrice);
			
			// Get requested products
			products = jdbcTemplate.query(
				query, 
				new ProductRowMapper(),
				searchValue,
				minPrice,
				maxPrice,
				limit,
				offset);
			
			if (products.size() == 0) {
				throw new ProductNotFoundException();
			}
			
			// Get total product count
			Integer totalProductCount = 
			jdbcTemplate.queryForObject(
				GET_PRODUCT_COUNT_NO_CATEGORY, 
				Integer.class, 
				searchValue,
				minPrice,
				maxPrice);
				
			// Store total count in first product brought back
			// TODO: Bring back in Custom Message Converter instead
			products.get(0).setTotalProductCount(totalProductCount);
		}
		
		return products;
	}
	
	private String getSortedQuery(String query, boolean sortAZ, boolean sortPrice) {
		StringBuilder stringBuilder = new StringBuilder(query);
		
		if (!sortAZ && !sortPrice) {
			stringBuilder.append(SORT_AZ_DESC);
		}
		else if (!sortAZ && sortPrice) {
			stringBuilder.append(SORT_PRICE_ASC);
		}
		else if (sortAZ && !sortPrice) {
			stringBuilder.append(SORT_AZ_ASC);
		}
		else {
			stringBuilder.append(SORT_PRICE_DESC);
		}		
		
		stringBuilder.append(LIMIT_OFFSET);
		
		return stringBuilder.toString();
	}
}
