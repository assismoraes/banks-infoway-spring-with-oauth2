package com.assismoraes.bank.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import com.assismoraes.bank.helpers.ClientHelper;
import com.assismoraes.bank.models.Account;
import com.assismoraes.bank.models.Transaction;
import com.assismoraes.bank.repos.AccountRepo;

@Service
public class TransactionService {
		
	@Autowired
	private AccountRepo accountRepo;
	
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
	
	public Object depositToOtherAccount(Transaction transaction, String otherAccountNumber) {
		Account account = this.accountRepo.findByNumber(otherAccountNumber);
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
	
	public List<Transaction> allMyTransactions() {
		return this.currentAccount().getTransactions();
	}
	
	public List<Transaction> allMyTransactionsByDate(String begin, String finish) {
		Date beginDate = new Date(new Long(begin));
		Date finishDate = new Date(new Long(finish));		
		return (List<Transaction>) this.currentAccount().getTransactions().stream()
				.filter(t -> t.getCreatedAt().after(beginDate) && t.getCreatedAt().before(finishDate)).collect(Collectors.toList());
	}
	
	public void verifyInsufficientFunds(Transaction transaction, Errors errors) {
		Account account = this.currentAccount();
		if(transaction.getValue() == null){
			errors.rejectValue("value", "value", "O valor da transação é obrigatório");
			return;
		}
		this.verifyValueOfDeposit(transaction, errors);
		if(account.getCurrentBalance() < transaction.getValue())
			errors.rejectValue("value", "value", "Saldo insuficiente");
	}
	
	public void verifyValueOfDeposit(Transaction transaction, Errors errors) {
		if(transaction.getValue() == null){
			errors.rejectValue("value", "value", "O valor da transação é obrigatório");
			return;
		}
		if(transaction.getValue() <= 0)
			errors.rejectValue("value", "value", "Valor inválido");
	}
	
	public void verifyConditionsToTransfer(Transaction transaction, Errors errors, String accountNumber) {
		if(transaction.getValue() == null){
			errors.rejectValue("value", "value", "O valor da transação é obrigatório");
			return;
		}
		this.verifyInsufficientFunds(transaction, errors);
		this.verifyValueOfDeposit(transaction, errors);
		if(!this.accountExists(accountNumber))
			errors.rejectValue("value", "value", "Conta inexistente");
	}
	
	public void verifyIfAccountExists(Errors errors, String otherAccountNumber) {
		if(!this.accountExists(otherAccountNumber))
			errors.rejectValue("value", "value", "Conta inexistente");
	}
	
	private Boolean accountExists(String accountNumber) {
		return this.accountRepo.findByNumber(accountNumber) == null ? false : true;
	}
	
	private Account currentAccount() {
		return this.accountRepo.findByNumber(ClientHelper.loggedUser().getName());
	}	
}
