package com.nickrepetti.estore.controller;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import com.nickrepetti.estore.dao.CategoryRepository;
import com.nickrepetti.estore.model.Category;

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

public class CategoryControllerTest {

	@Mock
	CategoryRepository mockRepository;
	
	@InjectMocks
	CategoryController categoryController;

	private MockMvc mockMvc;

	private Category categoryA;
	private final Long CATEGORY_A_ID = new Long(1);
	private final String CATEGORY_A_NAME = "Televisions";
	
	private Category categoryB;
	private final Long CATEGORY_B_ID = new Long(1);
	private final String CATEGORY_B_NAME = "Audio";
	
	private List<Category> categoryList;
	private List<Category> emptyCategoryList;
	
	@Before
	public void setup() {
		initializeCategories();
		
		MockitoAnnotations.initMocks(this);
		
		mockMvc = standaloneSetup(categoryController).build();
	}
	
	@Test
	public void testGetCategories_goodRequest() throws Exception {
		when(mockRepository.getCategories()).thenReturn(categoryList);
		
		mockMvc.perform(get("/categories"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(2)))
			.andExpect(jsonPath("$[0].id", is(CATEGORY_A_ID.intValue())))
			.andExpect(jsonPath("$[0].name", is(CATEGORY_A_NAME)))
			.andExpect(jsonPath("$[1].id", is(CATEGORY_B_ID.intValue())))
			.andExpect(jsonPath("$[1].name", is(CATEGORY_B_NAME)));
			
		verify(mockRepository, times(1)).getCategories();
        verifyNoMoreInteractions(mockRepository);
	}
	
	@Test
	public void testGetCategories_goodRequestNoCategories() throws Exception {
		when(mockRepository.getCategories()).thenReturn(emptyCategoryList);
		
		mockMvc.perform(get("/categories"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(0)));
			
		verify(mockRepository, times(1)).getCategories();
        verifyNoMoreInteractions(mockRepository);
	}
	
	@Test
	public void testGetCategories_badRequest_1() throws Exception {
		mockMvc.perform(get("/categories/3"))
			.andExpect(status().isNotFound());
			
		verifyZeroInteractions(mockRepository);
	}
	
	@Test
	public void testGetCategories_badRequest_2() throws Exception {
		mockMvc.perform(get("/category"))
			.andExpect(status().isNotFound());
			
		verifyZeroInteractions(mockRepository);
	}
	
	private Category createCategory(Long id, String name) {
		Category category = new Category();
		category.setId(id);
		category.setName(name);
		
		return category;
	}
	
	private void initializeCategories() {
		categoryA = createCategory(CATEGORY_A_ID, CATEGORY_A_NAME);
		categoryB = createCategory(CATEGORY_B_ID, CATEGORY_B_NAME);
		
		categoryList = new ArrayList();
		categoryList.add(categoryA);
		categoryList.add(categoryB);
		
		emptyCategoryList = new ArrayList();
	}
}
