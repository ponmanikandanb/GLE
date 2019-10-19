package com.temenos.gle.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.annotation.ComponentScan;

import com.temenos.gle.model.AccountDetails;

public class GLEDAO implements GLEDataRepository {

	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<AccountDetails> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
