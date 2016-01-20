package com.nickrepetti.estore.config.root;

import com.nickrepetti.estore.dao.CategoryRepository;
import com.nickrepetti.estore.dao.ProductRepository;
import com.nickrepetti.estore.dao.UserRepository;

import com.nickrepetti.estore.dao.jdbc.JdbcCategoryRepository;
import com.nickrepetti.estore.dao.jdbc.JdbcProductRepository;
import com.nickrepetti.estore.dao.jdbc.JdbcUserRepository;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class JdbcConfig {

	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
			.setType(EmbeddedDatabaseType.H2)
			.addScript("schema.sql")
			.addScript("data.sql")
			.build();
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public CategoryRepository getCategoryRepository(JdbcTemplate jdbcTemplate) {
		return new JdbcCategoryRepository(jdbcTemplate);
	}
	
	@Bean
	public ProductRepository getProductRepository(JdbcTemplate jdbcTemplate) {
		return new JdbcProductRepository(jdbcTemplate);
	}
	
	@Bean
	public UserRepository getUserRepository(JdbcTemplate jdbcTemplate) {
		return new JdbcUserRepository(jdbcTemplate);
	}
}
