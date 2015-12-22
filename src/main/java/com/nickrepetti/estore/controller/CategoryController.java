package com.nickrepetti.estore.controller;

import com.nickrepetti.estore.dao.CategoryRepository;
import com.nickrepetti.estore.model.Category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {
		
	private CategoryRepository categoryRepository;
	
	@Autowired
	public CategoryController(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Category> getCategories() {
		return categoryRepository.getCategories();
	}
}
