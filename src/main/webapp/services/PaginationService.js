var electronicsStore = angular.module('ElectronicsStore');

electronicsStore.factory('PaginationService', function() {

	var ITEMS_PER_PAGE = 4;
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
	
	service.calculateLastPage = function(totalNumItems) {
		return Math.ceil(totalNumItems / ITEMS_PER_PAGE);
	};
	
	service.calculateOffset = function(page, paginationForward) {
		var offset;
		
		if (paginationForward) {
			offset = (page - 1) * ITEMS_PER_PAGE;
		}
		else {
			offset = (page - PAGE_RANGE) * ITEMS_PER_PAGE;
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
	
	service.getPageOfItems = function(itemsArr, page) {
		page = typeof page !== 'undefined' ? page : 1;
		
		var pageOfItems = [];
	
		// Maps page to page range
		var startIndex = ((page - 1) % PAGE_RANGE) * ITEMS_PER_PAGE;
		
		var endIndex = startIndex + ITEMS_PER_PAGE;
		
		// Make sure endIndex is not out of range
		var lastItem = itemsArr.length;
		if (endIndex > lastItem) {
			endIndex = lastItem;
		}
		
		// Grab entire page worth of items
		for (var i = startIndex; i < endIndex; i++) {
			pageOfItems.push(itemsArr[i]);
		}
		
		return pageOfItems;
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
		function(totalNumItems, startPage, paginationForward) {
			
		startPage = typeof startPage !== 'undefined' ? 
			startPage : service.data.currentPage;
		
		paginationForward = typeof paginationForward !== 'undefined' ? 
			paginationForward : true;
		
		// Calculate last page
		var lastPage = service.calculateLastPage(totalNumItems);
		
		var pageRange = 
			service.calculatePageRange(startPage, lastPage, paginationForward);
		
		service.data.currentPage = startPage;
		service.data.pageRange = pageRange;
		service.data.lastPage = lastPage;
	};
	
	return service;
});
