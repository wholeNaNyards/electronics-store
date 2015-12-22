package com.nickrepetti.estore.controller;

import com.nickrepetti.estore.dao.ItemRepository;
import com.nickrepetti.estore.model.Item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	public List<Item> getItems() {
		return itemRepository.getItems();
	}
}
