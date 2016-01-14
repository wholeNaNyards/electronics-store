var electronicsStore = angular.module('electronicsStore');

electronicsStore.factory('CategoryList', function($http, $q) {
	
	var categoryList = {};
	
	categoryList.data = {
		categories: []
	};
	
	categoryList.loadCategories = function() {
		categoryList.data.categories = [
			{
				id: '0',
				name: 'All'
			}, {
				id: '1',
				name: 'Televisions'
			}, {
				id: '2',
				name: 'Video Games'
			}, {
				id: '3',
				name: 'Audio'
			}
		];
	};

	return categoryList;
});
