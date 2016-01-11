var electronicsStore = angular.module("electronicsStore", ['ngRoute']);
	
electronicsStore.controller("electronicsStoreController", function($scope, $location, cartFactory) {	
	
	$scope.cart = cartFactory.data;
	
	cartFactory.loadCart();
	
	$scope.addToCart = function(product) {
		cartFactory.addToCart(product);
	}

	$scope.removeFromCart = function(product) {
		cartFactory.removeFromCart(product);
	}
	
	// Returns true if the product id is in the User's cart
	$scope.itemInCart = function(productId) {
		return cartFactory.itemInCart(productId);
	}
	
	// Returns true if the User's cart is empty
	$scope.emptyCart = function() {
		return cartFactory.emptyCart();
	}
	
	// Returns true if page is the current page
	$scope.isCurrentPage = function(page) {
		return page === $location.path();
	}
});

// Configure Routes
electronicsStore.config(function($routeProvider, $locationProvider) {
	$routeProvider
		.when('/home', {
			templateUrl : 'pages/home.html',
			controller  : 'electronicsStoreController'
		})
		
		.when('/', {
			templateUrl : 'pages/products.html',
			controller  : 'electronicsStoreController'
		})
		
		.when('/products', {
			templateUrl : 'pages/products.html',
			controller  : 'electronicsStoreController'
		})

		.when('/cart', {
			templateUrl : 'pages/cart.html',
			controller  : 'electronicsStoreController'
		})
		
		.when('/underConstruction', {
			templateUrl : 'pages/underConstruction.html',
			controller  : 'electronicsStoreController'
		})
		
		.otherwise({
			redirectTo: '/products'
		});

    $locationProvider.html5Mode(true);
});
