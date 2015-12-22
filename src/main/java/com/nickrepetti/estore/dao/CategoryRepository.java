package com.nickrepetti.estore.dao;

import com.nickrepetti.estore.model.Category;

import java.util.List;

public interface CategoryRepository {
	
	public List<Category> getCategories();
}
