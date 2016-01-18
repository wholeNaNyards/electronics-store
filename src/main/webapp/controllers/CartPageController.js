var electronicsStore = angular.module('ElectronicsStore');

electronicsStore.controller('CartPageController', 
	function($scope, FilterService, PaginationService, ProductService) {
		
	$scope.cartList = ProductService.data;
	
	// Initial page load
	FilterService.init();
	PaginationService.init();
	ProductService.setLoadCart(true);
	var filterObject = FilterService.getFilterObject();
	ProductService.loadProducts(filterObject);
});
