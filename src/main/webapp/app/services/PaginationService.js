var electronicsStore = angular.module('ElectronicsStore');

electronicsStore.factory('PaginationService', function() {

	var PRODUCTS_PER_PAGE = 4;
	var PAGE_RANGE = 5;

	var service = {};	
	
	service.data = {
		currentPage: 1,
		pageRange: [],
		lastPage: 0
	};

	service.init = function() {
		service.data.currentPage = 1;
		service.data.pageRange = [];
		service.data.lastPage = 0;
	};
	
	service.calculateLastPage = function(totalNumProducts) {
		return Math.ceil(totalNumProducts / PRODUCTS_PER_PAGE);
	};
	
	service.calculateOffset = function(page, paginationForward) {
		var offset;
		
		if (paginationForward) {
			offset = (page - 1) * PRODUCTS_PER_PAGE;
		}
		else {
			offset = (page - PAGE_RANGE) * PRODUCTS_PER_PAGE;
		}
		
		return offset;
	};

	service.calculatePageRange = 
		function(startPage, lastPage, paginationForward) {
			
		var pageRange = [];
		
		if (paginationForward) {
			var endPage = startPage + PAGE_RANGE - 1;
		
			// Make sure endPage is not out of range
			if (endPage > lastPage) {
				endPage = lastPage;
			}
			
			for (var i = startPage; i <= endPage; i++) {
				pageRange.push(i);
			}
		}
		else {
			var endPage = startPage - PAGE_RANGE + 1;
		
			// Make sure endPage is not out of range
			if (endPage < 1) {
				endPage = 1;
			}
			
			for (var i = endPage; i <= startPage; i++) {
				pageRange.push(i);
			}
		}
		
		return pageRange;
	};
	
	service.getCurrentPage = function() {
		return service.data.currentPage;
	};
	
	service.getPageOfProducts = function(productsArr, page) {
		page = typeof page !== 'undefined' ? page : 1;
		
		var pageOfProducts = [];
	
		// Maps page to page range
		var startIndex = ((page - 1) % PAGE_RANGE) * PRODUCTS_PER_PAGE;
		
		var endIndex = startIndex + PRODUCTS_PER_PAGE;
		
		// Make sure endIndex is not out of range
		var lastProduct = productsArr.length;
		if (endIndex > lastProduct) {
			endIndex = lastProduct;
		}
		
		// Grab entire page worth of products
		for (var i = startIndex; i < endIndex; i++) {
			pageOfProducts.push(productsArr[i]);
		}
		
		return pageOfProducts;
	};
	
	service.isCurrentPage = function(page) {
		return page == service.data.currentPage;
	};
	
	service.isPageInRange = function(page) {
		var pageRange = service.data.pageRange;
		
		for (var i = 0; i < pageRange.length; i++) {
			if (page == pageRange[i]) {
				return true;
			}
		}
		
		return false;
	};
	
	service.onFirstPage = function() {
		return service.data.currentPage == 1;
	};
	
	service.onLastPage = function() {
		return service.data.currentPage == service.data.lastPage;
	};
	
	service.setCurrentPage = function(currentPage) {
		service.data.currentPage = currentPage;
	};
	
	service.updatePagination = 
		function(totalNumProducts, startPage, paginationForward) {
			
		startPage = typeof startPage !== 'undefined' ? startPage : 1;
		
		paginationForward = typeof paginationForward !== 'undefined' ? 
			paginationForward : true;
		
		// Calculate last page
		var lastPage = service.calculateLastPage(totalNumProducts);
		
		var pageRange = 
			service.calculatePageRange(startPage, lastPage, paginationForward);
		
		service.data.currentPage = startPage;
		service.data.pageRange = pageRange;
		service.data.lastPage = lastPage;
	};
	
	return service;
});
