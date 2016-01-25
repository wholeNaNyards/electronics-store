package com.nickrepetti.estore.controller;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import com.nickrepetti.estore.dao.ProductRepository;
import com.nickrepetti.estore.model.Image;
import com.nickrepetti.estore.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.junit.Before;
import org.junit.Test;

import org.springframework.test.web.servlet.MockMvc;

public class ProductControllerTest {

	@Mock
	ProductRepository mockRepository;
	
	@InjectMocks
	ProductController productController;

	private MockMvc mockMvc;

	private Product productA;
	private final Long PRODUCT_A_ID = new Long(1);
	private final String PRODUCT_A_NAME = "Awesome Television";
	private final String PRODUCT_A_DESCRIPTION = "A really cool television.";
	private final BigDecimal PRODUCT_A_PRICE = new BigDecimal(400);
	
	private Product productB;
	private final Long PRODUCT_B_ID = new Long(42);
	private final String PRODUCT_B_NAME = "Not So Awesome Television";
	private final String PRODUCT_B_DESCRIPTION = "A decent television.";
	private final BigDecimal PRODUCT_B_PRICE = new BigDecimal(100);
	
	private final Long IMAGE_ID = new Long(4);
	private final String IMAGE_NAME = "tv-sm.jpg";
	
	private List<Product> productList;
	private List<Product> emptyProductList;
	
	private final String SEARCH_VALUE = "";
	private final int MIN_PRICE = 0;
	private final int MAX_PRICE = 1000;
	private final int CATEGORY_ID = 0;
	private final boolean SORT_AZ = false;
	private final boolean SORT_PRICE = false;
	private final int LIMIT = 20;
	private final int OFFSET = 10;
	
	@Before
	public void setup() {
		initializeProducts();
		
		MockitoAnnotations.initMocks(this);
		
		mockMvc = standaloneSetup(productController).build();
	}
	
	@Test
	public void testGetProducts_goodRequest() throws Exception {
		when(mockRepository.getProducts(
			SEARCH_VALUE,
			MIN_PRICE,
			MAX_PRICE,
			CATEGORY_ID,
			SORT_AZ,
			SORT_PRICE,
			LIMIT,
			OFFSET))
		.thenReturn(productList);
		
		mockMvc.perform(get("/products")
			.param("searchValue", SEARCH_VALUE)
            .param("minPrice", Integer.toString(MIN_PRICE))
            .param("maxPrice", Integer.toString(MAX_PRICE))
			.param("categoryId", Integer.toString(CATEGORY_ID))
			.param("sortAZ", Boolean.toString(SORT_AZ))
			.param("sortPrice", Boolean.toString(SORT_PRICE))
			.param("limit", Integer.toString(LIMIT))
			.param("offset", Integer.toString(OFFSET)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(2)))
			.andExpect(jsonPath("$[0].id", is(PRODUCT_A_ID.intValue())))
			.andExpect(jsonPath("$[0].name", is(PRODUCT_A_NAME)))
			.andExpect(jsonPath("$[0].description", is(PRODUCT_A_DESCRIPTION)))
			.andExpect(jsonPath("$[0].price", is(PRODUCT_A_PRICE.intValue())))
			.andExpect(jsonPath("$[0].image.id", is(IMAGE_ID.intValue())))
			.andExpect(jsonPath("$[0].image.name", is(IMAGE_NAME)))
			.andExpect(jsonPath("$[1].id", is(PRODUCT_B_ID.intValue())))
			.andExpect(jsonPath("$[1].name", is(PRODUCT_B_NAME)))
			.andExpect(jsonPath("$[1].description", is(PRODUCT_B_DESCRIPTION)))
			.andExpect(jsonPath("$[1].price", is(PRODUCT_B_PRICE.intValue())))
			.andExpect(jsonPath("$[1].image.id", is(IMAGE_ID.intValue())))
			.andExpect(jsonPath("$[1].image.name", is(IMAGE_NAME)));
			
		verify(mockRepository, times(1)).getProducts(
			SEARCH_VALUE,
			MIN_PRICE,
			MAX_PRICE,
			CATEGORY_ID,
			SORT_AZ,
			SORT_PRICE,
			LIMIT,
			OFFSET);
        verifyNoMoreInteractions(mockRepository);
	}
	
	@Test
	public void testGetProducts_goodRequestNoProducts() throws Exception {
		when(mockRepository.getProducts(
			SEARCH_VALUE,
			MIN_PRICE,
			MAX_PRICE,
			CATEGORY_ID,
			SORT_AZ,
			SORT_PRICE,
			LIMIT,
			OFFSET))
		.thenReturn(emptyProductList);
		
		mockMvc.perform(get("/products")
			.param("searchValue", SEARCH_VALUE)
            .param("minPrice", Integer.toString(MIN_PRICE))
            .param("maxPrice", Integer.toString(MAX_PRICE))
			.param("categoryId", Integer.toString(CATEGORY_ID))
			.param("sortAZ", Boolean.toString(SORT_AZ))
			.param("sortPrice", Boolean.toString(SORT_PRICE))
			.param("limit", Integer.toString(LIMIT))
			.param("offset", Integer.toString(OFFSET)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(0)));
			
		verify(mockRepository, times(1)).getProducts(
			SEARCH_VALUE,
			MIN_PRICE,
			MAX_PRICE,
			CATEGORY_ID,
			SORT_AZ,
			SORT_PRICE,
			LIMIT,
			OFFSET);
        verifyNoMoreInteractions(mockRepository);
	}
	
	@Test
	public void testGetProducts_badRequest_1() throws Exception {
		mockMvc.perform(get("/products/3"))
			.andExpect(status().isNotFound());
			
		verifyZeroInteractions(mockRepository);
	}
	
	@Test
	public void testGetProducts_badRequest_2() throws Exception {
		mockMvc.perform(get("/product"))
			.andExpect(status().isNotFound());
			
		verifyZeroInteractions(mockRepository);
	}
	
	private Product createProduct(Long id, String name, String description,
		BigDecimal price) {
		
		Product product = new Product();
		
		product.setId(id);
		product.setName(name);
		product.setDescription(description);
		product.setPrice(price);
	
		Image image = new Image();
		image.setId(IMAGE_ID);
		image.setName(IMAGE_NAME);
		
		product.setImage(image);
	
		return product;
	}
	
	private void initializeProducts() {
		productA = createProduct(PRODUCT_A_ID, PRODUCT_A_NAME, 
			PRODUCT_A_DESCRIPTION, PRODUCT_A_PRICE);
		productB = createProduct(PRODUCT_B_ID, PRODUCT_B_NAME, 
			PRODUCT_B_DESCRIPTION, PRODUCT_B_PRICE);
		
		productList = new ArrayList();
		productList.add(productA);
		productList.add(productB);
		
		emptyProductList = new ArrayList();
	}
}
