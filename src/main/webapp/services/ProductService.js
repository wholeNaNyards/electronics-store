var electronicsStore = angular.module('ElectronicsStore');

electronicsStore.factory('ProductService', 
	function($http, CartService, PaginationService, productsURL, usersURL) {
	
	this.allProducts = [];
	this.totalNumProducts = 0,
	this.loadCart = false;
	
	var ITEMS_PER_PAGE = 4;
	var PAGE_RANGE = 5;
	
	var LIMIT = ITEMS_PER_PAGE * PAGE_RANGE;
	
	var service = {};	
	
	service.data = {
		products: [],
		error: false,
		errorMsg: ''
	};
	
	service.allProductsLoaded = function() {
		return this.totalNumProducts <= LIMIT;
	}
	
	service.getAllProducts = function() {
		return this.allProducts;
	};
	
	service.getTotalNumProducts = function() {
		return this.totalNumProducts;
	}

	service.loadProducts = function(filter, offset, page, paginationForward) {
		
		offset = typeof offset !== 'undefined' ? offset : 0;
		
		filter.offset = offset;
		filter.limit = LIMIT;
		
		var me = this;
		
		if (!me.loadCart) {
			$http.get(productsURL, {params: filter})
			.success(function (data) {
				me.allProducts = data;
				me.totalNumProducts = me.allProducts[0].totalItemCount;
				
				service.updateProducts();
				
				service.data.error = false;
				service.data.errorMsg = '';
				
				PaginationService.updatePagination(
					me.totalNumProducts, page, paginationForward);
			})
			.error(function (error) {
				service.data.error = true;
				service.data.errorMsg = error.message;
			});
		}
		else {
			// Hard code to default user with id = 1
			$http.get(usersURL + '/1/items', {params: filter})
			.success(function (data) {
				me.allProducts = data;
				
				var firstItem = me.allProducts[0];
				me.totalNumProducts = firstItem.totalItemCount;
				
				CartService.setSubtotal(firstItem.subtotal);
				service.updateProducts();
				
				service.data.error = false;
				service.data.errorMsg = '';
				
				PaginationService.updatePagination(
					me.totalNumProducts, page, paginationForward);
			})
			.error(function (error) {
				service.data.error = true;
				service.data.errorMsg = error.message;
			});
		}
	};
	
	service.setAllProducts = function(allProducts) {
		this.allProducts = allProducts;
	};
		
	service.setLoadCart = function(loadCart) {
		this.loadCart = loadCart;
	};
	
	service.setProducts = function(products) {
		service.data.products = products;
	};
	
	service.updateProducts = function() {
		var products = [];
		
		for (var i = 0; i < ITEMS_PER_PAGE && i < this.totalNumProducts; i++) {
			products.push(this.allProducts[i]);
		}
		
		service.data.products = products;
	}
	
	return service;
});
