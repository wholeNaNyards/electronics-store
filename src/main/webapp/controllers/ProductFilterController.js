var electronicsStore = angular.module('ElectronicsStore');

electronicsStore.controller('ProductFilterController', 
	function($scope, 
		CategoryService, FilterService, PaginationService, ProductService) {
	
	// Bind and load data from service to scope
	$scope.categoryList = CategoryService.data;
	CategoryService.loadCategories();
	
	// Initialize filters
	var minPriceRange = [5, 10, 15, 20, 25, 50, 100, 150, 250, 500];
	var maxPriceRange = [25, 50, 75, 100, 125, 250, 500, 750, 1000, 1250];
	var initialCategoryId = $scope.categoryList.categories[0].id;
	
	$scope.minPriceRange = minPriceRange;
	$scope.minPrice = minPriceRange[0];
	$scope.maxPriceRange = maxPriceRange;
	$scope.maxPrice = maxPriceRange[8];
	$scope.categoryId = initialCategoryId;

	$scope.filterCategory = function(categoryId) {
		FilterService.setCategoryId(categoryId);
		$scope.filterProducts();
	};
		
	$scope.filterMaxPrice = function(maxPrice) {
		if ($scope.isValidMax()) {
			FilterService.setMaxPrice(maxPrice);
			$scope.filterProducts();
		}
	};

	$scope.filterMinPrice = function(minPrice) {
		if ($scope.isValidMin()) {
			FilterService.setMinPrice(minPrice);
			$scope.filterProducts();
		}
	};
	
	$scope.filterProducts = function() {
		$scope.updateFilters();
		var filterObject = FilterService.getFilterObject();
		
		ProductService.loadProducts(filterObject);
	};
	
	$scope.isValidMax = function() {
		return $scope.maxPrice > $scope.minPrice;
	};
	
	$scope.isValidMin = function() {
		return $scope.minPrice < $scope.maxPrice;
	};
	
	// This method is a helper method used to ensure that all filter values are
	// up to date when filterProducts() is called.
	$scope.updateFilters = function() {
		FilterService.setMinPrice($scope.minPrice);
		FilterService.setMaxPrice($scope.maxPrice);
		FilterService.setCategoryId($scope.categoryId);
	};
});
