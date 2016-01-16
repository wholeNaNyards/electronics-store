package com.nickrepetti.estore.dao.jdbc;

import com.nickrepetti.estore.dao.ItemRepository;
import com.nickrepetti.estore.dao.jdbc.mapper.ItemRowMapper;

import com.nickrepetti.estore.model.Item;

import com.nickrepetti.estore.util.ItemNotFoundException;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcItemRepository implements ItemRepository {

	// No category specified - Grab all items
	private final String GET_ALL_ITEMS_NO_CATEGORY =
		"SELECT i.id, i.name, i.description, i.price, "
		+ "im.id as imageId, im.name as imageName "
		+ "FROM Items i "
		+ "INNER JOIN Images im "
		+ "ON i.imageId = im.id "
		+ "WHERE LOWER(i.name) LIKE '%' || ? || '%' "
		+ "AND i.price >= ? AND i.price <= ? ";
		
	private final String GET_ITEM_COUNT_NO_CATEGORY =
		"SELECT COUNT(*) "
		+ "FROM Items i "
		+ "WHERE LOWER(i.name) LIKE '%' || ? || '%' "
		+ "AND i.price >= ? AND i.price <= ?;";
		
	// Category specified
	private final String GET_ALL_ITEMS_WITH_CATEGORY = 
		"SELECT i.id, i.name, i.description, i.price, "
		+ "im.id AS imageId, im.name AS imageName "
		+ "FROM Items i "
		+ "INNER JOIN Images im "
		+ "ON i.imageId = im.id "
		+ "INNER JOIN ItemCategories ic "
		+ "ON i.id = ic.itemId "
		+ "WHERE LOWER(i.name) LIKE '%' || ? || '%' "
		+ "AND i.price >= ? AND i.price <= ? AND ic.categoryId = ? ";
		
	private final String GET_ITEM_COUNT_WITH_CATEGORY = 
		"SELECT COUNT(*) "
		+ "FROM Items i "
		+ "INNER JOIN ItemCategories ic "
		+ "ON i.id = ic.itemId "
		+ "WHERE LOWER(i.name) LIKE '%' || ? || '%' "
		+ "AND i.price >= ? AND i.price <= ? AND ic.categoryId = ?;";
		
	private String SORT_AZ_DESC = "ORDER BY i.name DESC, i.price ";
	private String SORT_AZ_ASC = "ORDER BY i.name ASC, i.price ";
	private String SORT_PRICE_DESC = "ORDER BY i.price DESC, i.name ";
	private String SORT_PRICE_ASC = "ORDER BY i.price ASC, i.name ";
	private String LIMIT_OFFSET = "LIMIT ? OFFSET ?;";
		
	private JdbcTemplate jdbcTemplate;

	public JdbcItemRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;		
	}

	@Override
	public List<Item> getItems(String searchValue, int minPrice, int maxPrice,
							   int categoryId, boolean sortAZ, 
							   boolean sortPrice, int limit, int offset) {
		List<Item> items;
		
		if (categoryId != 0) {
			String query = getSortedQuery(GET_ALL_ITEMS_WITH_CATEGORY, 
										  sortAZ, sortPrice);
			
			// Get requested items
			items = jdbcTemplate.query(
				query, 
				new ItemRowMapper(),
				searchValue,
				minPrice,
				maxPrice,
				categoryId,
				limit,
				offset);
		
			if (items.size() == 0) {
				throw new ItemNotFoundException();
			}
		
			// Get total item count
			Integer totalItemCount = 
				jdbcTemplate.queryForObject(
					GET_ITEM_COUNT_WITH_CATEGORY, 
					Integer.class, 
					searchValue,
					minPrice,
					maxPrice,
					categoryId);
					
			// Store total count in first item brought back
			// TODO: Bring back in Custom Message Converter instead
			items.get(0).setTotalItemCount(totalItemCount);
		}
		// No category selected (categoryId == 0)
		else {
			String query = getSortedQuery(GET_ALL_ITEMS_NO_CATEGORY, 
										  sortAZ, sortPrice);
			
			// Get requested items
			items = jdbcTemplate.query(
				query, 
				new ItemRowMapper(),
				searchValue,
				minPrice,
				maxPrice,
				limit,
				offset);
			
			if (items.size() == 0) {
				throw new ItemNotFoundException();
			}
			
			// Get total item count
			Integer totalItemCount = 
			jdbcTemplate.queryForObject(
				GET_ITEM_COUNT_NO_CATEGORY, 
				Integer.class, 
				searchValue,
				minPrice,
				maxPrice);
				
			// Store total count in first item brought back
			// TODO: Bring back in Custom Message Converter instead
			items.get(0).setTotalItemCount(totalItemCount);
		}
		
		return items;
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
