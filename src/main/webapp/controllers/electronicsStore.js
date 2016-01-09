var electronicsStore = angular.module("electronicsStore", ['ngRoute']);
	
electronicsStore.controller("electronicsStoreController", function($scope, cartFactory) {	
	
	$scope.cart = cartFactory.data;
	
	cartFactory.loadCart();
	
	// Add to cart button clicked
	$scope.addToCart = function(productId) {
		cartFactory.addToCart(productId);
	}

	// Remove from cart button clicked
	$scope.removeFromCart = function(productId) {
		cartFactory.removeFromCart(productId);
	}
	
	$scope.itemInCart = function(productId) {
		return cartFactory.itemInCart(productId);
	}
	
	$scope.emptyCart = function() {
		return cartFactory.emptyCart();
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
