var electronicsStore = angular.module('ElectronicsStore');

// Configure Routes
electronicsStore.config(function($routeProvider, $locationProvider) {
	$routeProvider
		.when('/home', {
			templateUrl : 'app/pages/home.html',
			controller  : 'MainController'
		})
		
		.when('/', {
			templateUrl : 'app/pages/products.html',
			controller  : 'MainController'
		})
		
		.when('/products', {
			templateUrl : 'app/pages/products.html',
			controller  : 'MainController'
		})

		.when('/cart', {
			templateUrl : 'app/pages/cart.html',
			controller  : 'MainController'
		})
		
		.when('/underConstruction', {
			templateUrl : 'app/pages/underConstruction.html',
			controller  : 'MainController'
		})
		
		.otherwise({
			redirectTo: '/products'
		});

    $locationProvider.html5Mode(true);
});
