package com.assismoraes.bank.helpers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

public class ClientHelper {
	
	/**
	 * @return the logged client
	 */
	public static Authentication loggedUser() {
		OAuth2Authentication oauth2Authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
		Authentication userAuth = oauth2Authentication.getUserAuthentication();
		return userAuth;
	}

}
