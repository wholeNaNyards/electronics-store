var electronicsStore = angular.module('electronicsStore');

electronicsStore.factory('ProductList', function($http, $q, $filter) {
		
	// Pagination Constants
	// Max amount of products displayed per page
	var PRODUCTS_PER_PAGE = 4;
	// Max number of pages brought in at a time
	var NUM_PAGES = 5;

	var allProducts = [
		{ 
			id: '3',
			name: 'Zivio 55" LED TV', 
			description: 'A product called one with a very long description about something about the product. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', 
			price: 100 
		}, {
			id: '1',
			name: 'Massung 42" LED Smart TV', 
			description: 'A product called two with a very long description about something about the product. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', 
			price: 150 
		}, { 
			id: '2',
			name: 'GL 30" LED TV', 
			description: 'A product called three with a very long description about something about the product. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', 
			price: 10 
		}
	];
	
	var productList = {};	
	
	productList.data = {
		products: [],
		currentPage: 1,
		pageRange: [],
		sortAZ: true,
		sortPrice: true
	};

	productList.loadProducts = function() {
		loadProducts(5, 125, 0);
	};
	
	productList.loadProductsFiltered = function(minPrice, maxPrice, categoryId) {
		loadProducts(minPrice, maxPrice, categoryId);
	};
	
	productList.changePage = function(page) {
		productList.data.currentPage = page;
	};

	productList.toggleSortAZ = function() {
		productList.data.sortAZ = !productList.data.sortAZ;
		
		sortProductsAZ();
		updateProducts();
	};
	
	productList.toggleSortPrice = function() {
		productList.data.sortPrice = !productList.data.sortPrice;
		
		sortProductsPrice();
		updateProducts();
	};

	function updateProducts() {
		productList.data.products = getCurrentPageOfProducts();
	};
	
	function getCurrentPageOfProducts() {
		var displayedProducts = [];
		
		for (var i = 0; i < allProducts.length; i++) {
			displayedProducts.push(allProducts[i]);
		}
		
		return displayedProducts;
	}
	
	function sortProductsDefault() {
		sortProductsAZ();
	}
	
	function sortProductsAZ() {
		allProducts = $filter('orderBy')(allProducts, ['name', 'price'], !productList.data.sortAZ);
	}
	
	function sortProductsPrice() {
		allProducts = $filter('orderBy')(allProducts, ['price', 'name'], !productList.data.sortPrice);
	}
	
	function loadProducts(minPrice, maxPrice, categoryId) {
		// allProducts = ajax(...);
		sortProductsDefault();
		updateProducts();
	}
	
	return productList;
});
