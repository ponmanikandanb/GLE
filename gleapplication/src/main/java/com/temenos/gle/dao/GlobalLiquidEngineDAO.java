package com.temenos.gle.dao;

import java.sql.SQLException;
import java.util.List;

import com.temenos.gle.model.AccountDetails;

public interface GlobalLiquidEngineDAO {

	void accountList(String clinetName);

	List<AccountDetails> getAccountListByClinetName(String clinetName);

	List<AccountDetails> getAccountListInGroup(String groupId);

	void saveGoup(List<AccountDetails> accountList);
	
	boolean insertIntoCodexData(List<String> codexData) throws SQLException;
	
	Double getBalance(String accountNumber,String companyId); 

}
