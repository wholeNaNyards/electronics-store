package com.nickrepetti.estore.controller;

import com.nickrepetti.estore.dao.ProductRepository;
import com.nickrepetti.estore.model.Product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
		
	private ProductRepository productRepository;
	
	@Autowired
	public ProductController(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Product> getProducts(
		@RequestParam(value="searchValue", defaultValue="") String searchValue, 
		@RequestParam(value="minPrice", defaultValue="0") int minPrice, 
		@RequestParam(value="maxPrice", defaultValue="1000") int maxPrice,
		@RequestParam(value="categoryId", defaultValue="0") int categoryId,
		@RequestParam(value="sortAZ", defaultValue="false") boolean sortAZ,
		@RequestParam(value="sortPrice", defaultValue="false") boolean sortPrice,
		@RequestParam(value="limit", defaultValue="20") int limit,
		@RequestParam(value="offset", defaultValue="0") int offset) {
		
		return productRepository.getProducts(
			searchValue, minPrice, maxPrice, categoryId,
			sortAZ, sortPrice, limit, offset);
	}
}
