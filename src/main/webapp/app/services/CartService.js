var electronicsStore = angular.module('ElectronicsStore');

electronicsStore.factory('CartService', function($http, usersURL) {
		
	var service = {};
	
	service.data = {
		products: {},
		size: 0,
		subtotal: 0.0
	};
	
	service.addToCart = function(product) {
		var productId = product.id;
		
		$http.post(usersURL + '/1/products/' + productId)
			.success(function (data) {
				if (!service.data.products[productId]) {
					service.data.products[productId] = 0;
				}
				
				service.data.products[productId]++;
				service.data.size++;
				service.data.subtotal += product.price;
			})
			.error(function (error) {
				// error.errorCode + error.message;
			});
	};
	
	service.emptyCart = function() {
		return service.data.size == 0;
	};
	
	service.productInCart = function(productId) {
		return service.data.products[productId] > 0;
	};

	service.loadCart = function() {
		// For now, use default user with id = 1
		$http.get(usersURL + '/1')
			.success(function (data) {
				service.data.products = data.cart;
				service.updateCart();
			})
			.error(function (error) {
				// TODO
			});
	};
	
	service.removeFromCart = function(product) {
		var productId = product.id;

		$http.delete(usersURL + '/1/products/' + productId)
			.success(function (data) {
				service.data.products[productId] = null;
				service.data.size--;
				service.data.subtotal -= product.price;
			})
			.error(function (error) {
				// TODO
				// error.errorCode + error.message;
			});
	};

	service.setSubtotal = function(subtotal) {
		service.data.subtotal = subtotal;
	};
	
	service.updateCart = function() {
		service.data.size = 0;
		
		for (var prop in service.data.products) {
			if (service.data.products.hasOwnProperty(prop)) {
				service.data.size++;
			}
		}
	};
	
	return service;
});
