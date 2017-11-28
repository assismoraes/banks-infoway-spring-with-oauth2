package com.assismoraes.bank.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Branch {
	
	@NotEmpty(message="O nome da agência é obrigatório")
	private String name;
	
	@Id
	@Column(unique=true)
	private String number;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "branch_id")
	private List<Account> accounts;
	
	@CreationTimestamp
	@Column(updatable=false)
	private Date createdAt;
	
	@UpdateTimestamp
	private Date updateAt;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}
