var electronicsStore = angular.module('electronicsStore');

electronicsStore.controller('productListFilterController', function($scope, CategoryList, ProductList) {
	
	// Bind and load data from service to scope
	$scope.categoryList = CategoryList.data;
	CategoryList.loadCategories();
	
	// Initialize filters
	var minPriceRange = [5, 10, 15, 20, 25, 50, 100, 150, 250, 500];
	var maxPriceRange = [25, 50, 75, 100, 125, 250, 500, 750, 1000, 1250];
	var initialCategoryId = $scope.categoryList.categories[0].id
	
	$scope.minPriceRange = minPriceRange;
	$scope.minPrice = minPriceRange[0];
	$scope.maxPriceRange = maxPriceRange;
	$scope.maxPrice = maxPriceRange[4];
	$scope.categoryId = initialCategoryId;
	
	// Event handlers
	// Filter clicked
	$scope.filterProducts = function() {
		if (isFilterValid()) {
			ProductList.loadProductsFiltered(
			$scope.minPrice,
			$scope.maxPrice,
			$scope.categoryId);
		}
	};

	$scope.isValidMin = function() {
		return $scope.minPrice < $scope.maxPrice;
	};
	
	$scope.isValidMax = function() {
		return $scope.maxPrice > $scope.minPrice;
	};
	
	function isFilterValid() {
		return $scope.isValidMin() && $scope.isValidMax();
	}
});
