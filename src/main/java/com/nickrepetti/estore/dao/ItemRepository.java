package com.nickrepetti.estore.dao;

import com.nickrepetti.estore.model.Item;

import java.util.List;

public interface ItemRepository {
	
	public List<Item> getItems();

	// public List<Item> getItems(ItemFilter itemFilter);
	// public List<Item> getItems(int perPg, int pgNum, int categoryId ...);
}
