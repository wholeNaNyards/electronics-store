var electronicsStore = angular.module('electronicsStore');

electronicsStore.controller('productListController', function($scope, ProductList) {
		
	$scope.productList = ProductList.data;
	
	// Initial page load
	ProductList.loadProducts();
	
	// View Event Handlers
	// Pagination Clicked
	$scope.changePage = function(page) {
		ProductList.changePage(page)
	};

	// Sort AZ button clicked
	$scope.toggleSortAZ = function() {
		ProductList.toggleSortAZ();
	}
	
	// Sort AZ button clicked
	$scope.toggleSortPrice = function() {
		ProductList.toggleSortPrice();
	}
});
