package com.assismoraes.bank.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/")
	public String home() {
		return "HOME";
	}
	
	@GetMapping("/api")
	public Object privateArea() {		
		OAuth2Authentication oauth2Authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
		Authentication userAuth = oauth2Authentication.getUserAuthentication();
		return userAuth;
	}
	
}
