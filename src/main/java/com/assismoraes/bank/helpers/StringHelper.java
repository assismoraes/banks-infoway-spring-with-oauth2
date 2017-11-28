package com.assismoraes.bank.helpers;

public class StringHelper {
	
	public static String randomNumber(Integer min, Integer max) {
		Integer randomNum = min + (int)(Math.random() * max); 
		return randomNum.toString();
	}

}
