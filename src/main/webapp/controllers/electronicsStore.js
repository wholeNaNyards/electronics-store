var electronicsStore = angular.module("electronicsStore", []);
	
electronicsStore.controller("electronicsStoreController", function($scope) {	
	
	$scope.data = {
		categories: [
			{
				name: 'All'
			}, {
				name: 'Televisions'
			}, {
				name: 'Video Games'
			}, {
				name: 'Audio'
			}
		],
		products: [
			{ 
				name: 'Zivio 55" LED TV', 
				description: 'A product called one with a very long description about something about the product. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', 
				category: 'Category #1', 
				price: 100 
			}, { 
				name: 'Massung 42" LED Smart TV', 
				description: 'A product called two with a very long description about something about the product. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', 
				category: 'Category #2', 
				price: 150 
			}, { 
				name: 'GL 30" LED TV', 
				description: 'A product called three with a very long description about something about the product. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', 
				category: 'Category #1', 
				price: 10 
			}
		]
	};
});
