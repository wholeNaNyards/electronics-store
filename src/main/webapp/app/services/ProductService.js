var electronicsStore = angular.module('ElectronicsStore');

electronicsStore.factory('ProductService', 
	function($http, CartService, PaginationService, productsURL, usersURL) {
	
	// Holds all products currently brought back from database (limited by LIMIT)
	this.allProducts = [];
	
	// Count of all products found by database query
	this.totalNumProducts = 0,
	
	// Flag for whether loading Products or User's Cart Items
	this.loadCart = false;
	
	var PRODUCTS_PER_PAGE = 4;
	var PAGE_RANGE = 5;
	
	var LIMIT = PRODUCTS_PER_PAGE * PAGE_RANGE;
	
	var service = {};	
	
	service.data = {
		products: [], // Currently displayed products on page
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
		
		// Load products
		if (!me.loadCart) {
			$http.get(productsURL, {params: filter})
			.success(function (data) {
				me.allProducts = data;
				me.totalNumProducts = me.allProducts[0].totalProductCount;
				
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
		// Load user's cart products
		else {
			// Hard code to default user with id = 1
			$http.get(usersURL + '/1/products', {params: filter})
			.success(function (data) {
				me.allProducts = data;
				
				var firstProduct = me.allProducts[0];
				me.totalNumProducts = firstProduct.totalProductCount;
				
				CartService.setSubtotal(firstProduct.subtotal);
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
		
		for (var i = 0; i < PRODUCTS_PER_PAGE && i < this.totalNumProducts; i++) {
			products.push(this.allProducts[i]);
		}
		
		service.data.products = products;
	};
	
	return service;
});
