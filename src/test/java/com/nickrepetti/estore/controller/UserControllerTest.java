package com.nickrepetti.estore.controller;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import com.nickrepetti.estore.dao.UserRepository;
import com.nickrepetti.estore.model.Image;
import com.nickrepetti.estore.model.User;

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

public class UserControllerTest {
/*
	@Mock
	UserRepository mockRepository;
	
	@InjectMocks
	UserController userController;

	private MockMvc mockMvc;

	private User userA;
	private final Long USER_A_ID = new Long(1);
	private final String USER_A_NAME = "Awesome Television";
	private final String USER_A_DESCRIPTION = "A really cool television.";
	private final BigDecimal USER_A_PRICE = new BigDecimal(400);
	
	private User userB;
	private final Long USER_B_ID = new Long(42);
	private final String USER_B_NAME = "Not So Awesome Television";
	private final String USER_B_DESCRIPTION = "A decent television.";
	private final BigDecimal USER_B_PRICE = new BigDecimal(100);
	
	private final Long IMAGE_ID = new Long(4);
	private final String IMAGE_NAME = "tv-sm.jpg";
	
	private List<User> userList;
	private List<User> emptyUserList;
	
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
		initializeUsers();
		
		MockitoAnnotations.initMocks(this);
		
		mockMvc = standaloneSetup(userController).build();
	}
	
	@Test
	public void testGetUsers_goodRequest() throws Exception {
		when(mockRepository.getUsers(
			SEARCH_VALUE,
			MIN_PRICE,
			MAX_PRICE,
			CATEGORY_ID,
			SORT_AZ,
			SORT_PRICE,
			LIMIT,
			OFFSET))
		.thenReturn(userList);
		
		mockMvc.perform(get("/users")
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
			.andExpect(jsonPath("$[0].id", is(USER_A_ID.intValue())))
			.andExpect(jsonPath("$[0].name", is(USER_A_NAME)))
			.andExpect(jsonPath("$[0].description", is(USER_A_DESCRIPTION)))
			.andExpect(jsonPath("$[0].price", is(USER_A_PRICE.intValue())))
			.andExpect(jsonPath("$[0].image.id", is(IMAGE_ID.intValue())))
			.andExpect(jsonPath("$[0].image.name", is(IMAGE_NAME)))
			.andExpect(jsonPath("$[1].id", is(USER_B_ID.intValue())))
			.andExpect(jsonPath("$[1].name", is(USER_B_NAME)))
			.andExpect(jsonPath("$[1].description", is(USER_B_DESCRIPTION)))
			.andExpect(jsonPath("$[1].price", is(USER_B_PRICE.intValue())))
			.andExpect(jsonPath("$[1].image.id", is(IMAGE_ID.intValue())))
			.andExpect(jsonPath("$[1].image.name", is(IMAGE_NAME)));
			
		verify(mockRepository, times(1)).getUsers(
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
	public void testGetUsers_goodRequestNoUsers() throws Exception {
		when(mockRepository.getUsers(
			SEARCH_VALUE,
			MIN_PRICE,
			MAX_PRICE,
			CATEGORY_ID,
			SORT_AZ,
			SORT_PRICE,
			LIMIT,
			OFFSET))
		.thenReturn(emptyUserList);
		
		mockMvc.perform(get("/users")
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
			
		verify(mockRepository, times(1)).getUsers(
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
	public void testGetUsers_badRequest_1() throws Exception {
		mockMvc.perform(get("/users/3"))
			.andExpect(status().isNotFound());
			
		verifyZeroInteractions(mockRepository);
	}
	
	@Test
	public void testGetUsers_badRequest_2() throws Exception {
		mockMvc.perform(get("/user"))
			.andExpect(status().isNotFound());
			
		verifyZeroInteractions(mockRepository);
	}
	
	private User createUser(Long id, String name, String description,
		BigDecimal price) {
		
		User user = new User();
		
		user.setId(id);
		user.setName(name);
		user.setDescription(description);
		user.setPrice(price);
	
		Image image = new Image();
		image.setId(IMAGE_ID);
		image.setName(IMAGE_NAME);
		
		user.setImage(image);
	
		return user;
	}
	
	private void initializeUsers() {
		userA = createUser(USER_A_ID, USER_A_NAME, 
			USER_A_DESCRIPTION, USER_A_PRICE);
		userB = createUser(USER_B_ID, USER_B_NAME, 
			USER_B_DESCRIPTION, USER_B_PRICE);
		
		userList = new ArrayList();
		userList.add(userA);
		userList.add(userB);
		
		emptyUserList = new ArrayList();
	}
*/	
}
