package com.assismoraes.bank.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assismoraes.bank.helpers.ClientHelper;
import com.assismoraes.bank.models.Account;
import com.assismoraes.bank.models.Transaction;
import com.assismoraes.bank.repos.AccountRepo;
import com.assismoraes.bank.repos.TransationRepo;

@Service
public class TransactionService {
		
	@Autowired
	private AccountRepo accountRepo;
	
	@Autowired
	private TransationRepo repo;
	
	public Object cashOut(Transaction transaction) {
		Double transactionValue = transaction.getValue();
		transaction.setValue(transactionValue * (-1));
		transaction.setCreatedAt(new Date());
		Account account = this.currentAccount();
		account.setCurrentBalance(account.getCurrentBalance() - transactionValue);
		account.getTransactions().add(transaction);
		return this.accountRepo.save(account);
	}
	
	public Object deposit(Transaction transaction) {
		Account account = this.currentAccount();
		account.setCurrentBalance(account.getCurrentBalance() + transaction.getValue());
		transaction.setCreatedAt(new Date());
		account.getTransactions().add(transaction);
		return this.accountRepo.save(account);
	}
	
	public Object transfer(Transaction transaction, String otherAccountNumber) {
		Double transactionValue = transaction.getValue();		
		//debit
		transaction.setValue(transactionValue * (-1));
		Account account = this.currentAccount();
		account.setCurrentBalance(account.getCurrentBalance() - transactionValue);		
		//credit
		Account destinyAccount = this.accountRepo.findByNumber(otherAccountNumber);
		destinyAccount.setCurrentBalance(destinyAccount.getCurrentBalance() + transactionValue);
		
		transaction.setOtherAccountNumber(destinyAccount.getNumber());// setOtherAccount(destinyAccount);
		transaction.setCreatedAt(new Date());
		
		Transaction creditTransaction = new Transaction();
		creditTransaction.setCreatedAt(new Date());
		creditTransaction.setOtherAccountNumber(account.getNumber());//setOtherAccount(account);
		creditTransaction.setValue(transactionValue);		
		
		account.getTransactions().add(transaction);
		destinyAccount.getTransactions().add(creditTransaction);
		this.accountRepo.save(account);
		this.accountRepo.save(destinyAccount);
		
		return "success";
	}
	
	public List<Transaction> all() {
		return this.repo.findAll();
	}
	
	private Account currentAccount() {
		return this.accountRepo.findByNumber(ClientHelper.loggedUser().getName());
	}	

}
