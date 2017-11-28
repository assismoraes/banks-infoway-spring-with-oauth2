package com.assismoraes.bank.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assismoraes.bank.models.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {

	Account findByNumber(String accountNumber);
	
}
