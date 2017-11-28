package com.assismoraes.bank.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assismoraes.bank.models.Client;

@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {
	
	Client findByRegisterNumber(String registerNumber);

}
