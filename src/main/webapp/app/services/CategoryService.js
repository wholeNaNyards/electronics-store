var electronicsStore = angular.module('ElectronicsStore');

electronicsStore.factory('CategoryService', function($http, categoriesURL) {
	
	var service = {};
	
	service.data = {
		categories: [{
			id: "0",
			name: "All"
		}]
	};
	
	service.loadCategories = function() {
		$http.get(categoriesURL)
			.success(function (data) {
				service.data.categories = data;
				service.data.categories.unshift({
					id: "0",
					name: "All"
				})
			})
			.error(function (error) {
				service.data.categories = [{
					id: "0",
					name: "All"
				}]
			});
	};

	return service;
});
