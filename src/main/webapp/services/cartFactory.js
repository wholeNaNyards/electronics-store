var electronicsStore = angular.module('electronicsStore');

electronicsStore.factory('cartFactory', function($http, $q) {
		
	var factory = {};
	
	factory.data = {
		products: {
			'3': 1,
			'2': 1
		},
		size: 2
	};
	
	factory.loadCart = function() {
		// factory.data.products = AJAX
		// factory.data.size = factory.data.products.length;
	};
	
	factory.addToCart = function(productId) {
		if (!factory.data.products[productId]) {
			factory.data.products[productId] = 0;
		}
		
		factory.data.products[productId]++;
		factory.data.size++;
	};

	factory.removeFromCart = function(productId) {
		factory.data.products[productId] = null;
		factory.data.size--;
	};

	factory.itemInCart = function(productId) {
		return factory.data.products[productId] > 0;
	}
	
	return factory;
});
