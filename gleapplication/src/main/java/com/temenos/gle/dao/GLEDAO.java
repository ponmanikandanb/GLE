package com.temenos.gle.dao;

import javax.sql.DataSource;

import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class GLEDAO implements GLEDataRepository {

	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
