package com.nickrepetti.estore.config.servlet;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation
	.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation
	.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan("com.nickrepetti.estore.controller")
public class ServletContextConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void configureDefaultServletHandling(
		DefaultServletHandlerConfigurer configurer) {
		
		configurer.enable();
	}
}
