package com.temenos.gle.dao;

import javax.sql.DataSource;

public class GLEDAO implements GLEDataRepository {

	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
}
