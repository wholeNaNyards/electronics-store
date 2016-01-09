var electronicsStore = angular.module('electronicsStore');

electronicsStore.factory('cartFactory', function($http, $q) {
		
	var factory = {};
	
	factory.data = {
		products: {},
		size: 0
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
	
	factory.emptyCart = function() {
		return factory.data.size == 0;
	}
	
	return factory;
});
