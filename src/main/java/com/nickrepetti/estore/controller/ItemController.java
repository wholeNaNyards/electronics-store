package com.nickrepetti.estore.controller;

import com.nickrepetti.estore.dao.ItemRepository;
import com.nickrepetti.estore.model.Item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/items")
public class ItemController {
		
	private ItemRepository itemRepository;
	
	@Autowired
	public ItemController(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Item> getItems(
		@RequestParam(value="searchValue", defaultValue="") String searchValue, 
		@RequestParam(value="minPrice", defaultValue="0") int minPrice, 
		@RequestParam(value="maxPrice", defaultValue="1000") int maxPrice,
		@RequestParam(value="categoryId", defaultValue="0") int categoryId,
		@RequestParam(value="sortAZ", defaultValue="false") boolean sortAZ,
		@RequestParam(value="sortPrice", defaultValue="false") boolean sortPrice,
		@RequestParam(value="limit", defaultValue="20") int limit,
		@RequestParam(value="offset", defaultValue="0") int offset) {
		
		return itemRepository.getItems(
			searchValue, minPrice, maxPrice, categoryId,
			sortAZ, sortPrice, limit, offset);
	}
}
