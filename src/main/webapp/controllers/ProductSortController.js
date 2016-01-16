var electronicsStore = angular.module('ElectronicsStore');

electronicsStore.controller('ProductSortController', 
	function($filter, $scope,
		FilterService, PaginationService, ProductService) {
	
	$scope.sortAZFrontEnd = function(sortAZ) {
		var allProducts = ProductService.getAllProducts();
			
		allProducts = $filter('orderBy')(allProducts, ['name', 'price'], sortAZ);
			
		ProductService.setAllProducts(allProducts);
		
		var products = PaginationService.getPageOfItems(allProducts);
		ProductService.setProducts(products);
	};

	$scope.sortBackEnd = function() {
		var filterObject = FilterService.getFilterObject();
		
		return ProductService.loadProducts(filterObject);
	};
	
	$scope.sortPriceFrontEnd = function(sortPrice) {
		var allProducts = ProductService.getAllProducts();
			
		allProducts = $filter('orderBy')(allProducts, ['price', 'name'], sortPrice);
			
		ProductService.setAllProducts(allProducts);
		
		var products = PaginationService.getPageOfItems(allProducts);
		ProductService.setProducts(products);
	};
	
	// Sort AZ button clicked
	$scope.toggleSortAZ = function() {
		var sortAZ = FilterService.getSortAZ();
		FilterService.setSortAZ(!sortAZ);
				
		if (ProductService.allProductsLoaded()) {
			$scope.sortAZFrontEnd(sortAZ);
			PaginationService.setCurrentPage(1);
		}
		else {
			$scope.sortBackEnd();
		}
	};
	
	// Sort AZ button clicked
	$scope.toggleSortPrice = function() {
		var sortPrice = FilterService.getSortPrice();
		FilterService.setSortPrice(!sortPrice);
		
		if (ProductService.allProductsLoaded()) {
			$scope.sortPriceFrontEnd(sortPrice);
			PaginationService.setCurrentPage(1);
		}
		else {
			$scope.sortBackEnd();
		}
	};
});
