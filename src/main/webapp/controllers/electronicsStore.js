var electronicsStore = angular.module('ElectronicsStore', ['ngRoute']);
	
electronicsStore.controller('ElectronicsStoreController', 
	function($scope, $location,
		CartService, FilterService, PaginationService, ProductService) {
	
	$scope.cart = CartService.data;
	CartService.loadCart();
	
	$scope.addToCart = function(product) {
		CartService.addToCart(product);
	}
	
	// Returns true if the User's cart is empty
	$scope.emptyCart = function() {
		return CartService.emptyCart();
	}
		
	// Returns true if page is the current page
	$scope.isCurrentPage = function(page) {
		return page === $location.path();
	}
		
	// Returns true if the product id is in the User's cart
	$scope.itemInCart = function(productId) {
		return CartService.itemInCart(productId);
	}
	
	$scope.removeFromCart = function(product) {
		CartService.removeFromCart(product);
	}
	
	$scope.searchProducts = function(searchValue) {
		FilterService.setSearchValue(searchValue.toLowerCase());
		
		var filterObject = FilterService.getFilterObject();
		
		ProductService.loadProducts(filterObject);
	}
});

// Configure Routes
electronicsStore.config(function($routeProvider, $locationProvider) {
	$routeProvider
		.when('/home', {
			templateUrl : 'pages/home.html',
			controller  : 'ElectronicsStoreController'
		})
		
		.when('/', {
			templateUrl : 'pages/products.html',
			controller  : 'ElectronicsStoreController'
		})
		
		.when('/products', {
			templateUrl : 'pages/products.html',
			controller  : 'ElectronicsStoreController'
		})

		.when('/cart', {
			templateUrl : 'pages/cart.html',
			controller  : 'ElectronicsStoreController'
		})
		
		.when('/underConstruction', {
			templateUrl : 'pages/underConstruction.html',
			controller  : 'ElectronicsStoreController'
		})
		
		.otherwise({
			redirectTo: '/products'
		});

    $locationProvider.html5Mode(true);
});

electronicsStore.constant("productsURL", "http://localhost:8080/items");
electronicsStore.constant("categoriesURL", "http://localhost:8080/categories");
electronicsStore.constant("usersURL", "http://localhost:8080/users");
