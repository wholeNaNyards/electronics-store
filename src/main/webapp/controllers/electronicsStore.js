var electronicsStore = angular.module("electronicsStore", []);
	
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
});
