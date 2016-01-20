package com.nickrepetti.estore.dao;

import com.nickrepetti.estore.model.Product;

import java.util.List;

public interface ProductRepository {
	
	public List<Product> getProducts(String searchValue, int minPrice, int maxPrice,
							   int categoryId, boolean sortAZ, 
							   boolean sortPrice, int limit, int offset);
}
