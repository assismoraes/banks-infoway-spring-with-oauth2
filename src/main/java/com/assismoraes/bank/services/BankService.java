package com.assismoraes.bank.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assismoraes.bank.models.Account;
import com.assismoraes.bank.models.Bank;
import com.assismoraes.bank.models.Branch;
import com.assismoraes.bank.repos.BankRepo;

@Service
public class BankService {

	@Autowired
	private BankRepo repo;
	
	@Autowired
	private BranchService branchService;
	
	public Object save(Bank bank) {
		bank.setBranches(new ArrayList<Branch>());
		return this.repo.save(bank);
	}
	
	public Bank update(Bank bank) {
		bank.setUpdateAt(new Date());
		bank.setBranches(this.repo.findOne(bank.getId()).getBranches());
		return this.repo.save(bank);
	}

	public void delete(Long id) {
		this.repo.delete(id);
	}

	public Bank find(Long id) {
		return this.repo.findOne(id);
	}

	public List<Bank> allBanks() {
		return this.repo.findAll();
	}

	public void addBranchToABank(Long bankId, Branch branch) {
		Bank bank = this.repo.findOne(bankId);
		branch.setAccounts(new ArrayList<Account>());
		if(bank != null){
			branch.setNumber(this.branchService.generateBranchNumber());
			bank.getBranches().add(branch);
			this.repo.save(bank);
		}
	}

}
