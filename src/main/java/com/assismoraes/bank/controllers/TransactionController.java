package com.assismoraes.bank.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.assismoraes.bank.exceptions.InvalidRequestException;
import com.assismoraes.bank.models.Transaction;
import com.assismoraes.bank.services.TransactionService;

@RestController
@RequestMapping("api/transaction")
public class TransactionController {
	
	@Autowired
	private TransactionService service;
	
	@GetMapping
	public List<Transaction> all() {
		return this.service.allMyTransactions();
	}
	
	@GetMapping("{begin}/{finish}")
	public List<Transaction> allByDate(@PathVariable("begin") String begin, @PathVariable("finish") String finish) {
		return this.service.allMyTransactionsByDate(begin, finish);
	}

	@RequestMapping(value="cashOut", method=RequestMethod.POST)
	public Object cashOut(@Validated @RequestBody Transaction transaction, Errors errors) {
		this.service.verifyInsufficientFunds(transaction, errors);
		if(errors.hasErrors())
			throw new InvalidRequestException("Transação inválida", errors);
		this.service.cashOut(transaction);
		return "success";
	}
	
	@RequestMapping(value="deposit", method=RequestMethod.POST)
	public Object deposit(@Validated @RequestBody Transaction transaction, Errors errors) {
		this.service.verifyValueOfDeposit(transaction, errors);
		if(errors.hasErrors())
			throw new InvalidRequestException("Transação inválida", errors); 
		this.service.deposit(transaction);
		return "success";
	}
	
	@RequestMapping(value="deposit/{otherAccountNumber}", method=RequestMethod.POST)
	public Object depositToOtherAccount(@Validated @RequestBody Transaction transaction, Errors errors,
			@PathVariable("otherAccountNumber") String otherAccountNumber) {
		this.service.verifyValueOfDeposit(transaction, errors);
		this.service.verifyIfAccountExists(errors, otherAccountNumber);
		if(errors.hasErrors())
			throw new InvalidRequestException("Transação inválida", errors);
		this.service.depositToOtherAccount(transaction, otherAccountNumber);
		return "success";
	}
	
	@RequestMapping(value="transfer/{otherAccountNumber}", method=RequestMethod.POST)
	public Object transfer(@Validated @RequestBody Transaction transaction, Errors errors, 
			@PathVariable("otherAccountNumber") String otherAccountNumber) {
		this.service.verifyConditionsToTransfer(transaction, errors, otherAccountNumber);
		if(errors.hasErrors())
			throw new InvalidRequestException("Transação inválida", errors);
		this.service.transfer(transaction, otherAccountNumber);
		return "success";
	}
	
}
