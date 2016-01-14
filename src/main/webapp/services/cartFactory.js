var electronicsStore = angular.module('electronicsStore');

electronicsStore.factory('cartFactory', function($http, $q) {
		
	var factory = {};
	
	factory.data = {
		products: {},
		size: 0,
		subtotal: 0.0
	};
	
	factory.loadCart = function() {
		// factory.data.products = AJAX
		// factory.data.size = factory.data.products.length;
	};
	
	factory.addToCart = function(product) {
		var productId = product.id;
		
		if (!factory.data.products[productId]) {
			factory.data.products[productId] = 0;
		}
		
		// AJAX
		
		factory.data.products[productId]++;
		factory.data.size++;
		factory.data.subtotal += product.price;
	};

	factory.removeFromCart = function(product) {
		var productId = product.id;

		// AJAX
		
		factory.data.products[productId] = null;
		factory.data.size--;
		factory.data.subtotal -= product.price;
	};

	factory.itemInCart = function(productId) {
		return factory.data.products[productId] > 0;
	}
	
	factory.emptyCart = function() {
		return factory.data.size == 0;
	}
	
	return factory;
});
