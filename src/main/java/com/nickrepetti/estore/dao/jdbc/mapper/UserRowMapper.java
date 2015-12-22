package com.nickrepetti.estore.dao.jdbc.mapper;

import com.nickrepetti.estore.dao.jdbc.mapper.UserResultSetExtractor;
import com.nickrepetti.estore.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User> {
	
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new UserResultSetExtractor().extractData(rs);
	}
}
