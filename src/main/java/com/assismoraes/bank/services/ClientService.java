package com.assismoraes.bank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assismoraes.bank.models.Client;
import com.assismoraes.bank.repos.ClientRepo;

@Service
public class ClientService {

	@Autowired
	private ClientRepo repo;
	
	public Client save(Client client) {
		return this.repo.save(client);
	}
	
	
	
}
