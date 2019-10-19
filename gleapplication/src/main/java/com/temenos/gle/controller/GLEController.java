package com.temenos.gle.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.temenos.gle.dao.GlobalLiquidEngineDAO;
import com.temenos.gle.model.AccountDetails;

@Controller
public class GLEController {

	/*
	 * @Autowired private GLEDataRepository repository;
	 */
	@Autowired
	private GlobalLiquidEngineDAO gleDAO;

	@GetMapping("/AccountList")
	@ResponseBody
	List<AccountDetails> findAccountsByClinetName(@RequestParam String clinetName) {
		List<AccountDetails> accountList = new ArrayList<AccountDetails>();
		accountList.addAll(gleDAO.getAccountListByClinetName(clinetName));
		return accountList;
	}
	/*
	 * // Find
	 * 
	 * @GetMapping("/AccountList") List<AccountDetails>
	 * findAccountsByClinetName(@RequestParam String clinetName) { return new
	 * ArrayList<AccountDetails>(); }
	 * 
	 * @PutMapping("/books/{id}") AccountDetails saveOrUpdate(@RequestBody
	 * AccountDetails newBook, @PathVariable Long id) { return newBook;
	 * 
	 * }
	 */

}
