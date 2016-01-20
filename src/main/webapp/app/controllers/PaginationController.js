var electronicsStore = angular.module('ElectronicsStore');

electronicsStore.controller('PaginationController', 
	function($scope, FilterService, PaginationService, ProductService) {

	$scope.pagination = PaginationService.data;
	
	$scope.changePage = function(page) {
		var allProducts = ProductService.getAllProducts();
		var products = PaginationService.getPageOfProducts(allProducts, page);
		
		ProductService.setProducts(products);
		PaginationService.setCurrentPage(page);
	};

	$scope.isCurrentPage = function(page) {
		return PaginationService.isCurrentPage(page);
	};
	
	$scope.loadPage = function(page, paginationForward) {
		var offset = 
			PaginationService.calculateOffset(page, paginationForward);
		
		var filterObject = FilterService.getFilterObject();
		
		ProductService.loadProducts(filterObject, offset, page, 
									paginationForward);
	};

	$scope.nextPage = function() {
		if (!$scope.onLastPage()) {
			var nextPage = PaginationService.getCurrentPage() + 1;
			
			if (PaginationService.isPageInRange(nextPage)) {
				$scope.changePage(nextPage);
			}
			else {
				$scope.loadPage(nextPage, true);
			}
		}
	};
	
	$scope.onFirstPage = function() {
		return PaginationService.onFirstPage();
	};
	
	$scope.onLastPage = function() {
		return PaginationService.onLastPage();
	};
	
	$scope.previousPage = function() {
		if (!$scope.onFirstPage()) {
			var previousPage = PaginationService.getCurrentPage() - 1;
			
			if (PaginationService.isPageInRange(previousPage)) {
				$scope.changePage(previousPage);
			}
			else {
				$scope.loadPage(previousPage, false);
			}
		}
	};
});
