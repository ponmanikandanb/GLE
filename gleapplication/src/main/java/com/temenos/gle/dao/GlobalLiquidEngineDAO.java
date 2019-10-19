package com.temenos.gle.dao;

import java.util.Collection;

public interface GlobalLiquidEngineDAO {

	String  getAll();

	void accountList(String clinetName);

	Collection getAccountListByClinetName(String clinetName);
}
