package com.assismoraes.bank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.assismoraes.bank.models.Usr;
import com.assismoraes.bank.repos.UserRepository;


@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public Object saveUser(Usr user) {
		try {
			return this.repository.save(user);
		} catch (DataIntegrityViolationException e) {
			return e;
		}		
	}
	
}
