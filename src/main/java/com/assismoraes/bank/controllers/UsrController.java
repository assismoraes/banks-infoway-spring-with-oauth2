package com.assismoraes.bank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.assismoraes.bank.exceptions.InvalidRequestException;
import com.assismoraes.bank.helpers.UsrEditable;
import com.assismoraes.bank.services.UsrService;

@RestController
@RequestMapping("api/usr")
public class UsrController {
	
	@Autowired
	private UsrService service;

	@RequestMapping(value="changePassword", method=RequestMethod.POST)
	public Object changePassword(@Validated @RequestBody UsrEditable usrEditable, Errors errors) {
		this.service.verifyPasswords(usrEditable, errors);
		if(errors.hasErrors())
			throw new InvalidRequestException("Usuário inválido", errors);
		this.service.updatePassword(usrEditable.getNewPassword());
		return "success";
	}
	
	@RequestMapping(value="getCurrentBalance", method=RequestMethod.POST)
	public Double getCurrentBalance() {
		return this.service.getCurrentBalance();
	}
}
