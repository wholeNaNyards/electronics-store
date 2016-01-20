var electronicsStore = angular.module('ElectronicsStore');

electronicsStore.factory('FilterService', function() {
	
	var searchValue = "";
	var minPrice = 0;
	var maxPrice = 1000;
	var categoryId = 0;
	var sortAZ = true;
	var sortPrice = false;

	var service = {};	

	service.init = function() {
		searchValue = "";
		minPrice = 0;
		maxPrice = 1000;
		categoryId = 0;
		sortAZ = true;
		sortPrice = false;
	}
	
	service.setSearchValue = function(searchVal) {
		searchValue = searchVal;
	}
	
	service.setMinPrice = function(minimumPrice) {
		minPrice = minimumPrice;
	}
	
	service.setMaxPrice = function(maximumPrice) {
		maxPrice = maximumPrice;
	}	
	
	service.setCategoryId = function(catId) {
		categoryId = catId;
	}
	
	service.getSortAZ = function() {
		return sortAZ;
	}
	
	service.setSortAZ = function(sort_AZ) {
		sortAZ = sort_AZ;
	}
	
	service.getSortPrice = function() {
		return sortPrice;
	}
	
	service.setSortPrice = function(sort_Price) {
		sortPrice = sort_Price;
	}

	service.getFilterObject = function() {
		return {
			searchValue: searchValue,
			minPrice: minPrice,
			maxPrice: maxPrice,
			categoryId: categoryId,
			sortAZ: sortAZ,
			sortPrice: sortPrice
		}
	}
	
	return service;
});
