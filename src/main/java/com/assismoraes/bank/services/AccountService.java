package com.assismoraes.bank.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assismoraes.bank.helpers.StringHelper;
import com.assismoraes.bank.models.Account;
import com.assismoraes.bank.models.Client;
import com.assismoraes.bank.repos.AccountRepo;
import com.assismoraes.bank.repos.ClientRepo;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepo repo;
	
	@Autowired
	private ClientRepo clientRepo;
	
	public Account save(Account account) {
		account.setNumber(this.generateAccountNumber());
		
		Client client = new Client();
		client.setName(account.getClient().getName());
		client.setRegisterNumber(account.getClient().getRegisterNumber());
		client = this.clientRepo.save(client);
		account.setClient(client);
		return this.repo.save(account);
	}
	
	public String generateAccountNumber() {
		String number = StringHelper.randomNumber(1000, 9999);
		while(this.repo.findByNumber(number) != null) {
			number = StringHelper.randomNumber(1000, 9999);
		}
		return number;
	}

	public List<Account> allAccounts() {
		return this.repo.findAll();
	}

	public Account findByNumber(String accountNumber) {
		return this.repo.findByNumber(accountNumber);
	}

	public void delete(Long id) {
		this.repo.delete(id);
	}
	
}
