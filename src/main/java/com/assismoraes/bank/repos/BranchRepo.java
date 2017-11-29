package com.assismoraes.bank.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assismoraes.bank.models.Branch;

@Repository
public interface BranchRepo extends JpaRepository<Branch, String> {

	Branch findByNumber(String number);

}
