package com.assismoraes.bank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.assismoraes.bank.errors.BankErrors;
import com.assismoraes.bank.models.Branch;
import com.assismoraes.bank.services.BranchService;

@RestController
@RequestMapping("api/branch")
public class BranchController {
	
	@Autowired
	private BranchService service;
	
	/**
	 * Receives an id of a branch and returns its referred branch
	 * @param id
	 * @return branch
	 */
	@RequestMapping("{id}")
	public Branch show(@PathVariable("id") Long id) {
		return this.service.find(id);
	}
	
	/**
	 * Receives an id of a branch and delete the referred branch
	 * @param id
	 * @return message of success
	 */
	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id) {
		this.service.delete(id);
		return "success";
	}
	
	/**
	 * Receive a branch, verify fields errors and update the referred branch
	 * @param branch
	 * @param errors
	 * @return updated branch or an HashMap object with the branch fields errors
	 */
	@RequestMapping(method=RequestMethod.PUT)
	public Object save(@Validated @RequestBody Branch branch, Errors errors) {
		if(errors.hasErrors())
			return BankErrors.formatErrors(errors);
		return this.service.update(branch);
	}

}
