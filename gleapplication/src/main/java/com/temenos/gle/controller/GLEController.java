package com.temenos.gle.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.temenos.gle.dao.GlobalLiquidEngineDAO;
import com.temenos.gle.model.AccountDetails;

@Controller
public class GLEController {

	/*
	 * @Autowired private GLEDataRepository repository;
	 */
	@Autowired
	private GlobalLiquidEngineDAO gleDAO;

	@CrossOrigin(origins = "*")
	@GetMapping("/AccountList")
	@ResponseBody
	List<AccountDetails> findAccountsByClinetName(@RequestParam String clientName) {
		List<AccountDetails> accountList = new ArrayList<AccountDetails>();
		accountList.addAll(gleDAO.getAccountListByClinetName(clientName));
		/*
		 * ObjectWriter ow = new ObjectMapper().writer(); String json = ""; try { json =
		 * ow.writeValueAsString(accountList); } catch (JsonProcessingException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); } json =
		 * "{\"employees\":[{\"firstName\":\"John\", \"lastName\":\"Doe\"},{\"firstName\":\"Anna\", \"lastName\":\"Smith\"},{\"firstName\":\"Peter\", \"lastName\":\"Jones\"}]}";
		 */
		return accountList;
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/userAccount")
	@ResponseBody
	List<AccountDetails> gerUserAccount(@RequestParam String groupId) {
		List<AccountDetails> accountList = new ArrayList<AccountDetails>();
		accountList.addAll(gleDAO.getAccountListInGroup(groupId));
		return accountList;
	}
	
	
	@CrossOrigin(origins = "*")
	@GetMapping("/userAccountInGroup")
	@ResponseBody
	List<AccountDetails> gerUserAccountInGroup(@RequestParam String groupId) {
		List<AccountDetails> accountList = new ArrayList<AccountDetails>();
		accountList.addAll(gleDAO.getAccountListInGroup(groupId));
		return accountList;
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/saveGroup")
	@ResponseBody
	String saveGroup(@RequestParam String accountDetails) {
		List<AccountDetails> accountList = new ArrayList<AccountDetails>();
		if(accountDetails != null) {
			String [] accounts = accountDetails.split("--");
			for(int i=0 ; i<accounts.length ; i++) {
				String[] acctdets =  accounts[0].split("-");
				AccountDetails acct = new AccountDetails();
				acct.setAccountNumber(acctdets[0]);
				acct.setBranchCode(acctdets[2]);
				accountList.add(acct);
			}
		}		
		System.out.println(accountDetails);
		gleDAO.saveGoup(accountList);
		return "SUCCESS";
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
