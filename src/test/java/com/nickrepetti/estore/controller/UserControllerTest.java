package com.nickrepetti.estore.controller;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import com.nickrepetti.estore.dao.UserRepository;
import com.nickrepetti.estore.model.Image;
import com.nickrepetti.estore.model.User;
import com.nickrepetti.estore.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.junit.Before;
import org.junit.Test;

import org.springframework.test.web.servlet.MockMvc;

public class UserControllerTest {

	@Mock
	UserRepository mockRepository;
	
	@InjectMocks
	UserController userController;

	private MockMvc mockMvc;

	private User user;
	private final Long USER_ID = new Long(1);
	private final String USER_FIRST_NAME = "Default";
	private final String USER_LAST_NAME = "User";
	private final String USER_USER_NAME = "dUser";
	
	private final Long CART_ITEM_A_ID = new Long(17);
	private final Long CART_ITEM_B_ID = new Long(2);
	
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
	
	private final boolean SORT_AZ = false;
	private final boolean SORT_PRICE = false;
	private final int LIMIT = 20;
	private final int OFFSET = 10;
	
	@Before
	public void setup() {
		initializeTests();
		
		MockitoAnnotations.initMocks(this);
		
		mockMvc = standaloneSetup(userController).build();
	}
	
	@Test
	public void testGetUser_goodRequest() throws Exception {
		when(mockRepository.getUser(USER_ID)).thenReturn(user);
		
		mockMvc.perform(get("/users/" + USER_ID.intValue()))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is(USER_ID.intValue())))
			.andExpect(jsonPath("$.firstName", is(USER_FIRST_NAME)))
			.andExpect(jsonPath("$.lastName", is(USER_LAST_NAME)))
			.andExpect(jsonPath("$.userName", is(USER_USER_NAME)))
			.andExpect(jsonPath("$.cart." + CART_ITEM_A_ID, is(1)))
			.andExpect(jsonPath("$.cart." + CART_ITEM_B_ID, is(1)));
			
		verify(mockRepository, times(1)).getUser(USER_ID);
        verifyNoMoreInteractions(mockRepository);
	}
	
	@Test
	public void testGetUser_goodRequestNullCart() throws Exception {
		user.setCart(null);
		
		when(mockRepository.getUser(USER_ID)).thenReturn(user);
		
		mockMvc.perform(get("/users/" + USER_ID.intValue()))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is(USER_ID.intValue())))
			.andExpect(jsonPath("$.firstName", is(USER_FIRST_NAME)))
			.andExpect(jsonPath("$.lastName", is(USER_LAST_NAME)))
			.andExpect(jsonPath("$.userName", is(USER_USER_NAME)));
			
		verify(mockRepository, times(1)).getUser(USER_ID);
        verifyNoMoreInteractions(mockRepository);
	}
	
	@Test
	public void testGetUser_goodRequestEmptyCart() throws Exception {
		user.setCart(new HashMap());
		
		when(mockRepository.getUser(USER_ID)).thenReturn(user);
		
		mockMvc.perform(get("/users/" + USER_ID.intValue()))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is(USER_ID.intValue())))
			.andExpect(jsonPath("$.firstName", is(USER_FIRST_NAME)))
			.andExpect(jsonPath("$.lastName", is(USER_LAST_NAME)))
			.andExpect(jsonPath("$.userName", is(USER_USER_NAME)));
			
		verify(mockRepository, times(1)).getUser(USER_ID);
        verifyNoMoreInteractions(mockRepository);
	}
	
	@Test
	public void testGetUser_badRequest_1() throws Exception {
		mockMvc.perform(get("/users"))
			.andExpect(status().isNotFound());
			
		verifyZeroInteractions(mockRepository);
	}
	
	@Test
	public void testGetUser_badRequest_2() throws Exception {
		mockMvc.perform(get("/user/" + USER_ID))
			.andExpect(status().isNotFound());
			
		verifyZeroInteractions(mockRepository);
	}
	
	@Test
	public void testGetCartProducts_goodRequest() throws Exception {
		when(mockRepository.getCartProducts(
			USER_ID, SORT_AZ, SORT_PRICE, LIMIT, OFFSET))
		.thenReturn(productList);
		
		mockMvc.perform(get("/users/" + USER_ID + "/products")
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
			
		verify(mockRepository, times(1))
			.getCartProducts(USER_ID, SORT_AZ, SORT_PRICE, LIMIT, OFFSET);
        
		verifyNoMoreInteractions(mockRepository);
	}
		
	@Test
	public void testGetCartProducts_goodRequestEmptyCart() throws Exception {
		when(mockRepository.getCartProducts(
			USER_ID, SORT_AZ, SORT_PRICE, LIMIT, OFFSET))
		.thenReturn(emptyProductList);
		
		mockMvc.perform(get("/users/" + USER_ID + "/products")
			.param("sortAZ", Boolean.toString(SORT_AZ))
			.param("sortPrice", Boolean.toString(SORT_PRICE))
			.param("limit", Integer.toString(LIMIT))
			.param("offset", Integer.toString(OFFSET)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(0)));
			
		verify(mockRepository, times(1))
			.getCartProducts(USER_ID, SORT_AZ, SORT_PRICE, LIMIT, OFFSET);
        
		verifyNoMoreInteractions(mockRepository);
	}
	
	@Test
	public void testGetCartProducts_badRequest_1() throws Exception {
		mockMvc.perform(get("/users/" + USER_ID + "/cart"))
			.andExpect(status().isNotFound());
			
		verifyZeroInteractions(mockRepository);
	}
	
	@Test
	public void testGetCartProducts_badRequest_2() throws Exception {
		mockMvc.perform(get("/user/" + USER_ID + "/products"))
			.andExpect(status().isNotFound());
			
		verifyZeroInteractions(mockRepository);
	}
	
	@Test
	public void testAddToCart_goodRequest() throws Exception {
		mockMvc.perform(post("/users/" + USER_ID + "/products/" + PRODUCT_A_ID))
			.andExpect(status().isOk());
			
		verify(mockRepository, times(1)).addToCart(USER_ID, PRODUCT_A_ID);
        
		verifyNoMoreInteractions(mockRepository);
	}
		
	@Test
	public void testAddToCart_badRequest_1() throws Exception {
		mockMvc.perform(post("/users/" + USER_ID + "/products"))
			.andExpect(status().isMethodNotAllowed());
			
		verifyZeroInteractions(mockRepository);
	}
		
	@Test
	public void testAddToCart_badRequest_2() throws Exception {
		mockMvc.perform(post("/users/" + USER_ID + "/products/tacos"))
			.andExpect(status().isBadRequest());
			
		verifyZeroInteractions(mockRepository);
	}
	
	@Test
	public void testRemoveFromCart_goodRequest() throws Exception {
		mockMvc.perform(delete("/users/" + USER_ID + "/products/" + PRODUCT_A_ID))
			.andExpect(status().isOk());
			
		verify(mockRepository, times(1)).removeFromCart(USER_ID, PRODUCT_A_ID);
        
		verifyNoMoreInteractions(mockRepository);
	}
		
	@Test
	public void testRemoveFromCart_badRequest_1() throws Exception {
		mockMvc.perform(delete("/users/" + USER_ID + "/products"))
			.andExpect(status().isMethodNotAllowed());
			
		verifyZeroInteractions(mockRepository);
	}
		
	@Test
	public void testRemoveFromCart_badRequest_2() throws Exception {
		mockMvc.perform(delete("/users/" + USER_ID + "/products/tacos"))
			.andExpect(status().isBadRequest());
			
		verifyZeroInteractions(mockRepository);
	}
	
	private User createUser(Long id, String firstName, String lastName, 
							String userName) {
		
		User user = new User();
		
		user.setId(id);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setUserName(userName);
		
		Map<Long, Integer> cart = new HashMap();
		cart.put(CART_ITEM_A_ID, 1);
		cart.put(CART_ITEM_B_ID, 1);
		
		user.setCart(cart);
		
		return user;
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
	
	private void initializeTests() {
		user = createUser(USER_ID, USER_FIRST_NAME, USER_LAST_NAME,
						  USER_USER_NAME);
		
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
