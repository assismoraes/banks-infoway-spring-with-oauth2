package com.assismoraes.bank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assismoraes.bank.models.Branch;
import com.assismoraes.bank.repos.BranchRepo;

@Service
public class BranchService {
	
	@Autowired
	private BranchRepo repo;

	public Object update(Branch branch) {
		return this.repo.save(branch);
	}

	public Branch find(Long id) {
		return this.repo.findOne(id);
	}

	public void delete(Long id) {
		this.repo.delete(id);
	}

}
