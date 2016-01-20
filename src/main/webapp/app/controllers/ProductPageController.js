var electronicsStore = angular.module('ElectronicsStore');

electronicsStore.controller('ProductPageController', 
	function($scope, FilterService, PaginationService, ProductService) {
		
	$scope.productList = ProductService.data;
	
	// Initial page load
	FilterService.init();
	PaginationService.init();
	ProductService.setLoadCart(false);
	var filterObject = FilterService.getFilterObject();
	ProductService.loadProducts(filterObject);
});
