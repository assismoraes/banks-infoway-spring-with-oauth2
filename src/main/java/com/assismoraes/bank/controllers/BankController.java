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

import com.assismoraes.bank.errors.BankErrors;
import com.assismoraes.bank.models.Bank;
import com.assismoraes.bank.models.Branch;
import com.assismoraes.bank.services.BankService;

@RestController
@RequestMapping("api/bank")
public class BankController {
	
	@Autowired
	private BankService service;
	
	@GetMapping
	public Object index() {
		return this.service.allBanks();
	}
	
	/**
	 * This method receives a Bank object, verifies its fields and saves it on database
	 * @param bank
	 * @param errors
	 * @return Bank object or a HashMap object with fields errors
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Object save(@Validated @RequestBody Bank bank, Errors errors) {
		if(errors.hasErrors())
			return BankErrors.formatErrors(errors);
		this.service.save(bank);
		return "success";
	}
	
	/**
	 * This method receives a modified Bank, verifies its fields and saves it on database 
	 * @param bank
	 * @param errors
	 * @return Modified Bank object or a HashMap object with the bank fields errors
	 */
	@RequestMapping(method=RequestMethod.PUT)
	public Object update(@Validated @RequestBody Bank bank, Errors errors) {
		if(errors.hasErrors())
			return BankErrors.formatErrors(errors);
		this.service.update(bank);
		return "success";
	}	
	
	/**
	 * This method receives a Bank id and returns the Bank referred to the id
	 * @param id
	 * @return Bank
	 */
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public Bank show(@PathVariable("id") Long id) {
		return this.service.find(id);
	}
	
	/**
	 * This method receive and id of a Bank instance and returns 
	 * @param id
	 * @return Message of success
	 */
	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id) {
		this.service.delete(id);
		return "success";
	}
	
	/**
	 * Receives an id of a Bank and return all its branches
	 * @param id
	 * @return List of branches of the bank
	 */
	@RequestMapping(value="{id}/branches")
	public List<Branch> listBranches(@PathVariable("id") Long id) {
		return this.service.find(id).getBranches();
	}
	
	/**
	 * Receives an id of a bank, a branch and adds the branch to referred bank list
	 * @param branch
	 * @param errors
	 * @param bankId
	 * @return message of success or a HashMap object with the branch fields errors
	 */
	@RequestMapping(value="{bankId}/branches", method=RequestMethod.POST)
	public Object addBranch(@Validated @RequestBody Branch branch, Errors errors, @PathVariable("bankId") Long bankId) {
		if(errors.hasErrors())
			return BankErrors.formatErrors(errors);
		this.service.addBranchToABank(bankId, branch);
		return "success";
	}
	
}
