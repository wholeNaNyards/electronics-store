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

	$scope.isFirstPage = function() {
		return ProductList.isFirstPage();
	}
	
	$scope.isLastPage = function() {
		return ProductList.isLastPage();
	}
	
	$scope.previousPage = function() {
		ProductList.previousPage();
	}
	
	$scope.nextPage = function() {
		ProductList.nextPage();
	}
	
	$scope.isCurrentPage = function(page) {
		return ProductList.isCurrentPage(page);
	}
	
	// Sort AZ button clicked
	$scope.toggleSortAZ = function() {
		ProductList.toggleSortAZ();
	}
	
	// Sort AZ button clicked
	$scope.toggleSortPrice = function() {
		ProductList.toggleSortPrice();
	}
});
