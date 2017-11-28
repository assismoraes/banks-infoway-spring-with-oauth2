package com.assismoraes.bank.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Client {
	
	@NotEmpty(message="O nome é obrigatório")
	private String name;	
	
	@NotEmpty(message="O CPF é obrigatório")
	@NotNull(message="O CPF é obrigatório")
	@Column(unique=true)
	@CPF(message="CPF inválido")
	@Id
	private String registerNumber;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegisterNumber() {
		return registerNumber;
	}

	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
	}
}
