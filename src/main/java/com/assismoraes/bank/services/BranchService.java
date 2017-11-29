package com.assismoraes.bank.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assismoraes.bank.helpers.StringHelper;
import com.assismoraes.bank.models.Account;
import com.assismoraes.bank.models.Branch;
import com.assismoraes.bank.models.Client;
import com.assismoraes.bank.models.Transaction;
import com.assismoraes.bank.models.Usr;
import com.assismoraes.bank.repos.BranchRepo;
import com.assismoraes.bank.repos.RoleRepo;

@Service
public class BranchService {
	
	@Autowired
	private BranchRepo repo;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRepo roleRepo;

	public Object update(Branch branch) {
		Branch b = this.repo.findByNumber(branch.getNumber());
		b.setName(branch.getName());
		return this.repo.save(b);
	}

	public Branch findByNumber(String branchNumber) {
		return this.repo.findByNumber(branchNumber);
	}

	public void delete(String id) {
		this.repo.delete(id);
	}

	public List<Branch> allBranches() {
		return this.repo.findAll();
	}

	public void addAccountToABranch(String branchNumber, Account account) {
		Branch branch = this.repo.findByNumber(branchNumber);
		account.setNumber(branch.getNumber() + this.accountService.generateAccountNumber());
		
		Usr usr = new Usr(account.getNumber(), account.getUsr().getPassword(), roleRepo.findByName("ROLE_USER"));
		usr = (Usr) this.userService.saveUser(usr);
		
		Client client = new Client();
		client.setName(account.getClient().getName());
		client.setRegisterNumber(account.getClient().getRegisterNumber());
		client = this.clientService.save(client);
		
		account.setClient(client);
		account.setTransactions(new ArrayList<Transaction>());
		account.setUsr(usr);
		account.setCurrentBalance(new Double(0));
		
		if(branch != null) {
			branch.getAccounts().add(account);
		}
		this.repo.save(branch);
	}
	
	public String generateBranchNumber() {
		String number = StringHelper.randomNumber(100, 999);
		while(this.repo.findByNumber(number) != null) {
			number = StringHelper.randomNumber(100, 999);
		}
		return number;
	}

}


