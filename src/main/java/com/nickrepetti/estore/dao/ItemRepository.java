package com.nickrepetti.estore.dao;

import com.nickrepetti.estore.model.Item;

import java.util.List;

public interface ItemRepository {
	
	public List<Item> getItems(int minPrice, int maxPrice, int categoryId, 
							   boolean sortAZ, boolean sortPrice, int limit,
							   int offset);
}
