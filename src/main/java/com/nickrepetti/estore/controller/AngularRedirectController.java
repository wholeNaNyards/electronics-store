package com.nickrepetti.estore.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AngularRedirectController {

	// Redirect all urls to Angular except for existing REST mappings
	// and direct resources
	@RequestMapping(value = "/{[path:[^\\.]*}")
	public String redirect() {
		
		return "forward:/";
	}
}
