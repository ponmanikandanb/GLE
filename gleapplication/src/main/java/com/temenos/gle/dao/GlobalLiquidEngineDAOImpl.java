package com.temenos.gle.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.temenos.gle.model.AccountDetails;

@Component
public class GlobalLiquidEngineDAOImpl implements GlobalLiquidEngineDAO {

	JdbcTemplate jdbcTemplate;

	/*
	 * private final String SQL_FIND_PERSON = "select * from people where id = ?";
	 * private final String SQL_DELETE_PERSON = "delete from people where id = ?";
	 * private final String SQL_UPDATE_PERSON =
	 * "update people set first_name = ?, last_name = ?, age  = ? where id = ?";
	 * private final String SQL_GET_ALL = "select * from people"; private final
	 * String SQL_INSERT_PERSON =
	 * "insert into people(id, first_name, last_name, age) values(?,?,?,?)";
	 */
	@Autowired
	public GlobalLiquidEngineDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public String getAll() {
		return "Test JPA";
	}
	

}
