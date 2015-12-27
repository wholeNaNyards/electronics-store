var electronicsStore = angular.module("electronicsStore");

electronicsStore.controller("productListController", function($scope) {
		
	$scope.addToCart = function(product) {
		alert(product.name + ' ' + product.price);
	}
	
	$scope.paginationTest = function() {
		alert('Pagination Clicked');
	}
});
