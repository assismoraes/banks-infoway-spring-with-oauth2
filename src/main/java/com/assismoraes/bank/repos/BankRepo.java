package com.assismoraes.bank.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assismoraes.bank.models.Bank;

@Repository
public interface BankRepo extends JpaRepository<Bank, Long> {
	
}
