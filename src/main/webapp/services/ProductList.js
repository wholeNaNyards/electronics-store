var electronicsStore = angular.module('electronicsStore');

electronicsStore.factory('ProductList', function($http, $q, $filter, productsURL) {
	this.allProducts = [];
		
	// Pagination Constants
	// Max amount of products displayed per page
	var PRODUCTS_PER_PAGE = 4;
	// Max number of pages brought in at a time
	var NUM_PAGES = 5;

	var minPrice = 0;
	var maxPrice = 1000;
	var categoryId = 0;
	
	// Used to determine current direction of pagination (Next or Previous)
	// true -- Next
	// false -- Previous
	var paginationForward = true;
	
	var productList = {};	
	
	productList.data = {
		products: [],
		currentPage: 1,
		pageRange: [],
		totalNumProducts: 0,
		lastPage: 0,
		sortAZ: true,
		sortPrice: false
	};
	
	productList.loadProducts = function() {
		loadProducts();
	};
	
	productList.loadProductsFiltered = function(min, max, catId) {
		minPrice = min;
		maxPrice = max;
		categoryId = catId;
		
		productList.data.currentPage = 1;
		loadProducts();
	};
	
	productList.toggleSortAZ = function() {
		productList.data.sortAZ = !productList.data.sortAZ;
		
		productList.data.currentPage = 1;
		
		// Check if all data was brought back, just do client side sort
		if (productList.data.totalNumProducts <= NUM_PAGES * PRODUCTS_PER_PAGE) {
			allProducts = $filter('orderBy')(allProducts, ['name', 'price'], !productList.data.sortAZ);
			updatePagination();
			loadPage();
		}
		else {
			// Requery
			loadProducts();
		}
	};
	
	productList.toggleSortPrice = function() {
		productList.data.sortPrice = !productList.data.sortPrice;
		
		productList.data.currentPage = 1;
		
		// Check if all data was brought back, just do client side sort
		if (productList.data.totalNumProducts <= NUM_PAGES * PRODUCTS_PER_PAGE) {
			allProducts = $filter('orderBy')(allProducts, ['price', 'name'], !productList.data.sortPrice);
			updatePagination();
			loadPage();
		}
		else {
			// Requery
			loadProducts();
		}
	};

	// Pagination
	productList.isFirstPage = function() {
		return productList.data.currentPage == 1;
	}
	
	productList.isLastPage = function() {
		return productList.data.currentPage == productList.data.lastPage;
	}
	
	productList.changePage = function(page) {
		productList.data.currentPage = page;
		
		loadPage();
	};
	
	productList.previousPage = function() {
		if (productList.data.currentPage > 1) {
			paginationForward = false;
			productList.data.currentPage--;
			
			if (isPageInRange(productList.data.currentPage)) {
				productList.changePage(productList.data.currentPage);
			}
			else {
				loadProducts();
			}
		}
		else {
			// No more data to load
		}
	}
	
	productList.nextPage = function() {
		if (productList.data.currentPage < productList.data.lastPage) {
			paginationForward = true;
			productList.data.currentPage++;
			
			if (isPageInRange(productList.data.currentPage)) {
				productList.changePage(productList.data.currentPage);
			}
			else {
				loadProducts();
			}
		}
		else {
			// No more data to load
		}
	}

	productList.isCurrentPage = function(page) {
		return page == productList.data.currentPage;
	}
	
	// Internal Methods
	function updatePagination() {
		var startPage = productList.data.currentPage;
		var pageRange = [];
		
		// Calculate last page
		var lastPage = Math.ceil(productList.data.totalNumProducts / PRODUCTS_PER_PAGE)
		
		if (paginationForward) {
			var endPage = startPage + NUM_PAGES - 1;
		
			// Make sure endPage is not out of range
			if (endPage > lastPage) {
				endPage = lastPage;
			}
			
			var i = startPage;

			do {
				pageRange.push(i);
				i++;
			}
			while (i <= endPage);
		}
		else {
			var endPage = startPage - NUM_PAGES + 1;
		
			// Make sure endPage is not out of range
			if (endPage < 1) {
				endPage = 1;
			}
			
			var i = endPage;

			do {
				pageRange.push(i);
				i++;
			}
			while (i <= startPage);
		}
		
		productList.data.currentPage = startPage;
		productList.data.pageRange = pageRange;
		productList.data.lastPage = lastPage;
	}
	
	function loadProducts() {
		var offset;
		
		if (paginationForward) {
			offset = (productList.data.currentPage - 1) * PRODUCTS_PER_PAGE;
		}
		else {
			offset = (productList.data.currentPage - NUM_PAGES) * PRODUCTS_PER_PAGE;
		}
		
		var limit = PRODUCTS_PER_PAGE * NUM_PAGES;

		var params = {
			minPrice: minPrice,
			maxPrice: maxPrice,
			categoryId: categoryId,
			sortAZ: productList.data.sortAZ,
			sortPrice: productList.data.sortPrice,
			limit: limit,
			offset: offset
		}
		
		var me = this;
		
		$http.get(productsURL, {params: params})
			.success(function (data) {
				me.allProducts = data;
				productList.data.totalNumProducts = me.allProducts[0].totalItemCount;
				updatePagination();
				loadPage();
				// TODO: Handle Errors that were sent back from server
			})
			.error(function (error) {
				me.allProducts = [];
				productList.data.totalNumProducts = 0;
				
				// Error - Communication with server failed. Please try again.
				updatePagination();
				loadPage();
			});
	}
	
	// Loads the given page of products
	function loadPage() {
		var displayedProducts = [];
	
		var page = productList.data.currentPage;
	
		// Maps page to page range
		var startIndex = ((page - 1) % NUM_PAGES) * PRODUCTS_PER_PAGE;
		
		var endIndex = startIndex + PRODUCTS_PER_PAGE;
		
		// Make sure endIndex is not out of range
		var lastItem = this.allProducts.length;
		if (endIndex > lastItem) {
			endIndex = lastItem;
		}
		
		// Grab entire page worth of items
		for (var i = startIndex; i < endIndex; i++) {
			displayedProducts.push(this.allProducts[i]);
		}
		
		productList.data.products = displayedProducts;
	}
		
	function isPageInRange(page) {
		var startIndex = 0;
		var endIndex = productList.data.pageRange.length - 1;
		
		return 	page >= productList.data.pageRange[startIndex] &&
				page <= productList.data.pageRange[endIndex];
	}
	
	return productList;
});
