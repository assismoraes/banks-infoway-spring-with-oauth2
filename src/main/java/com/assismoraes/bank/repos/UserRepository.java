package com.assismoraes.bank.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assismoraes.bank.models.Usr;

public interface UserRepository extends JpaRepository<Usr, Long> {
	
	Usr findByUsername(String username);

}
