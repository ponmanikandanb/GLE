package com.temenos.gle.dao;

import java.util.List;

import com.temenos.gle.model.AccountDetails;

public interface GLEDataRepository {
	List<AccountDetails> getAll();
}	
