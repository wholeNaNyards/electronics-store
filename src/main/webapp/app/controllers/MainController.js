var electronicsStore = angular.module('ElectronicsStore');
	
electronicsStore.controller('MainController', 
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
	$scope.productInCart = function(productId) {
		return CartService.productInCart(productId);
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
