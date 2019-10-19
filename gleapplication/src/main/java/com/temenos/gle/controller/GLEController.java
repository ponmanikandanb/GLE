package com.temenos.gle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.temenos.gle.dao.GLEDataRepository;
import com.temenos.gle.dao.GlobalLiquidEngineDAO;
import com.temenos.gle.model.AccountDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class GLEController {

	/*
	 * @Autowired private GLEDataRepository repository;
	 */
	@Autowired
	private GlobalLiquidEngineDAO gleDAO;

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
