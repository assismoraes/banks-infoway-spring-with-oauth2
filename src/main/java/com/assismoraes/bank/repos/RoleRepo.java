package com.assismoraes.bank.repos;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assismoraes.bank.models.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
	
	public ArrayList<Role> findByName(String name);
	
}
