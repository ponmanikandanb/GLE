package com.temenos.gle.data;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO: Document me!
 *
 * @author rsreleka
 *
 */
public class ProcessT24Data {

    public static List<String> getData() throws Exception {
    	Map<String, List<String>> custMap = new HashMap<String, List<String>>();
    	List<String> newtmpData = new ArrayList<>() ;
        Map<String, Map<String, List<String>>> companyMap = new HashMap<String, Map<String, List<String>>>();
        String data = readFileAsString("C:\\Projects\\Hackethon\\t24data\\&SAVEDLISTS&\\&SAVEDLISTS&\\CUSTOMER.LIST");
        String[] tmpData = data.split("AcctDetails");
        String[] accdata = tmpData[1].split("companyDetails");
        List<String> companyList = new ArrayList<String>(Arrays.asList(accdata[1].split("&")));
        List<String> customerList = new ArrayList<String>(Arrays.asList(tmpData[0].split("%")));
        List<String> accountList = new ArrayList<String>(Arrays.asList(accdata[0].split("##")));

        List<String> cusList;
        List<String> accData;
        for (String company : companyList) {
            int count = 1;
            cusList = new ArrayList<String>();
            String companySpecCus = customerList.get(count).replace(company.concat("#"), "");
            cusList.add(companySpecCus);
            String[] customrArray = companySpecCus.split("#");
            for (String customer : customrArray) {
                for (String account : accountList) {
                    if (!(account.contains("Company"))) {
                        if (account.contains(customer)) {
                            custMap.remove(customer);
                            accData = new ArrayList<String>();
                            accData.add(account);
                            custMap.put(customer, accData);
                            break;
                        }
                    }
                }
            }

            companyMap.put(company, custMap);
            newtmpData = companyMap.get("BNK").get("100160");
            System.out.println(newtmpData);
            count++;
        }
        return newtmpData;
    }

    public static String readFileAsString(String fileName) throws Exception {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }

}
