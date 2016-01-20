package com.nickrepetti.estore.controller;

import static org.mockito.Matchers.isA;
//import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.junit.Before;
import org.junit.Test;

import org.springframework.test.web.servlet.MockMvc;

public class CategoryControllerTest {

	/*
	@Mock
	PolicyService policyService;
	*/
	
	//@InjectMocks
	AngularRedirectController angularRedirectController;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		/*
		//MockitoAnnotations.initMocks(this);
		angularRedirectController = new AngularRedirectController();
		//this.mockMvc = MockMvcBuilders.standaloneSetup(controllerUnderTest).build();
		mockMvc = MockMvcBuilders.standaloneSetup(angularRedirectController).build();
		*/
	}
	
	@Test
	public void testRedirect_blah() {
		/*
		mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("/"));
		*/
		
		/*
		this.mockMvc.perform(post("/create")
            .param("email", "mvcemail@test.com")
            .param("firstName", "mvcfirst")
            .param("lastName", "mvclastname"))
            .andExpect(forwardedUrl("/"));
		
		
			 when(policyService.save(isA(Policy.class))).thenReturn(new Policy());

			mockMvc.perform(
                post("/policies/persist").param("companyName", "Company Name")
                .param("name", "Name").param("effectiveDate", "2001-01-01"))
                .andExpect(status().isMovedTemporarily())
       
			
			SpitterRepository mockRepository = mock(SpitterRepository.class);
		
			Spitter unsaved = new Spitter("jbauer", "24hours", "Jack", "Bauer");
			Spitter saved = new Spitter(24L, "jbauer", "24hours", "Jack", "Bauer");
			
			when(mockRepository.save(unsaved)).thenReturn(saved);
			
		*/
	}
}
