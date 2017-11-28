package com.assismoraes.bank.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.assismoraes.bank.errors.BankErrors;
import com.assismoraes.bank.models.Account;
import com.assismoraes.bank.services.AccountService;

@RestController
@RequestMapping("api/account")
public class AccountController {
	
	@Autowired
	private AccountService service;

	@RequestMapping(method=RequestMethod.GET)
	public List<Account> allAccounts() { 
		return this.service.allAccounts();
	}
	
	@RequestMapping(value="{accountNumber}", method=RequestMethod.GET)
	public Account show(@PathVariable("accountNumber") String accountNumber) { 
		return this.service.showOne(accountNumber);
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public Object update(@Validated @RequestBody Account account, Errors errors) {
		if(errors.hasErrors())
			return BankErrors.formatErrors(errors);
		return this.service.update(account);
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	public Object delete(@PathVariable("id") Long id) {
		this.service.delete(id);
		return "success";
	}
	
}
