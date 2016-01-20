package com.nickrepetti.estore.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.Before;
import org.junit.Test;

import org.springframework.test.web.servlet.MockMvc;

public class AngularRedirectControllerTest {

	AngularRedirectController angularRedirectController;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		/*
		angularRedirectController = new AngularRedirectController();
		//this.mockMvc = standaloneSetup(controllerUnderTest).build();
		mockMvc = standaloneSetup(angularRedirectController).build();
		*/
	}
	
	@Test
	public void testRedirect_blah() {
		/*
		mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("/"));
		*/
	}
}
