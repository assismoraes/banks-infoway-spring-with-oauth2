package com.assismoraes.bank.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
public class Account {
	
	@Id
	@Column(unique=true)
	private String number;
	
	@NotNull(message="Os dados do cliente são obrigatórios")
	@Valid
	@OneToOne(cascade=CascadeType.ALL)
	private Client client;
	
	@NotNull(message="Os dados do usuário são obrigatórios")
	@Valid
	@OneToOne(cascade=CascadeType.ALL)
	private Usr usr;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "account_id")
	private List<Transaction> transactions;
	
	@CreatedDate
	private Date createdAt;
	
	@LastModifiedDate
	private Date updateAt;
	
	private Double currentBalance;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public Usr getUsr() {
		return usr;
	}

	public void setUsr(Usr user) {
		this.usr = user;
	}

	public Double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(Double currentBalance) {
		this.currentBalance = currentBalance;
	}	
}
