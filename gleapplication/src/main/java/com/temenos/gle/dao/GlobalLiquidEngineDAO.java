package com.temenos.gle.dao;

import java.util.Collection;
import java.util.List;

import com.temenos.gle.model.AccountDetails;

public interface GlobalLiquidEngineDAO {

	String  getAll();

	void accountList(String clinetName);

	List<AccountDetails> getAccountListByClinetName(String clinetName);

	List<AccountDetails> getAccountListInGroup(String groupId);
}
