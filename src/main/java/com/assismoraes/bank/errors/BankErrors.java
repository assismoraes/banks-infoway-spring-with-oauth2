package com.assismoraes.bank.errors;

import java.util.HashMap;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

public class BankErrors {
	
	public static HashMap<String, String> formatErrors(Errors errors) {
		List<ObjectError> err = errors.getAllErrors();
		HashMap<String, String> hMErrors = new HashMap<>();
		for (ObjectError oe : err) {
			hMErrors.put(oe.getCodes()[1], oe.getDefaultMessage());
		}
		return hMErrors;
	}

}
