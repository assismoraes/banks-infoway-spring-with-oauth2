package com.assismoraes.bank.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;

@Entity
public class Transaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@CreatedDate
	private Date createdAt;
	
	private String otherAccountNumber;
	
	@NotNull(message="O valor da transação é obrigatório")
	private Double value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getOtherAccountNumber() {
		return otherAccountNumber;
	}

	public void setOtherAccountNumber(String otherAccountNumber) {
		this.otherAccountNumber = otherAccountNumber;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
	
}
