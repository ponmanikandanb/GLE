package com.temenos.gle.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	
	Map<String,String> dummyValue=new HashMap<String,String>();
    @PostConstruct
    public void initialize(){
        dummyValue.put("Hi","hello how can I help you");
        dummyValue.put("How are you?","I am good.What about you");
     
        dummyValue.put("I need to change my mobile number", "pls fill this form and give it to our clerk,your request will be updated in 2 to 3 working days");
        dummyValue.put("I need to know when I will get my maturity amount for this account","We will check and let you know sir");
        dummyValue.put("thank you", "Welcome.Have a nice Day");
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/process")
    @ResponseBody
    public String processRequestMessage(@RequestParam String request){
        String response=null;
        if(Pattern.matches("[A-Za-z0-9]*hi[A-Za-z0-9]*", request.toLowerCase().trim().replace(" ",""))){
           response=dummyValue.get("Hi");
           }
        if(Pattern.matches("[A-Za-z0-9]*hello[A-Za-z0-9]*", request.toLowerCase().trim().replace(" ",""))){
            response=dummyValue.get("Hi");
            }
        if(Pattern.matches("[A-Za-z0-9]*balance[A-Za-z0-9]*", request.toLowerCase().trim().replace(" ",""))){
          response = "your balance is Rs.4000";
        }
        if(Pattern.matches("[A-Za-z0-9]*change[A-Za-z0-9]*", request.toLowerCase().trim().replace(" ",""))){
            response=dummyValue.get("I need to change my mobile number");
        }

        if(Pattern.matches("[A-Za-z0-9]*hey[A-Za-z0-9]*", request.toLowerCase().trim().replace(" ",""))){
            response=dummyValue.get("Hi");
        }
        
        if(Pattern.matches("[A-Za-z0-9]*maturity[A-Za-z0-9]*", request.toLowerCase().trim().replace(" ",""))){
            response=dummyValue.get("I need to know when I will get my maturity amount for this account");
        }
        if(Pattern.matches("[A-Za-z0-9]*thank[A-Za-z0-9]*", request.toLowerCase().trim().replace(" ",""))){
            response=dummyValue.get("thank you");
        }
        
        
        
        return response;
                 
                 
    }

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
