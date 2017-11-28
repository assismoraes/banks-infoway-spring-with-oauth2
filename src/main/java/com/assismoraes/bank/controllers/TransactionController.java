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
import com.assismoraes.bank.models.Transaction;
import com.assismoraes.bank.services.TransactionService;

@RestController
@RequestMapping("api/transaction")
public class TransactionController {
	
	@Autowired
	private TransactionService service;
	
	@RequestMapping
	public List<Transaction> all() {
		return this.service.all();
	}

	@RequestMapping(value="cashOut", method=RequestMethod.POST)
	public Object cashOut(@Validated @RequestBody Transaction transaction, Errors errors) {
		if(errors.hasErrors())
			return BankErrors.formatErrors(errors);
		this.service.cashOut(transaction);
		return "success";
	}
	
	@RequestMapping(value="deposit", method=RequestMethod.POST)
	public Object deposit(@Validated @RequestBody Transaction transaction, Errors errors) {
		if(errors.hasErrors())
			return BankErrors.formatErrors(errors);
		this.service.deposit(transaction);
		return "success";
	}
	
	@RequestMapping(value="transfer/{otherAccountNumber}", method=RequestMethod.POST)
	public Object transfer(@Validated @RequestBody Transaction transaction, Errors errors, 
			@PathVariable("otherAccountNumber") String otherAccountNumber) {
		if(errors.hasErrors())
			return BankErrors.formatErrors(errors);
		this.service.transfer(transaction, otherAccountNumber);
		return "success";
	}
	
}
