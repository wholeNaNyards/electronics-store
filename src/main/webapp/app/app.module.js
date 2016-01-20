var electronicsStore = angular.module('ElectronicsStore', ['ngRoute']);

electronicsStore.constant("productsURL", "http://localhost:8080/products");
electronicsStore.constant("categoriesURL", "http://localhost:8080/categories");
electronicsStore.constant("usersURL", "http://localhost:8080/users");
