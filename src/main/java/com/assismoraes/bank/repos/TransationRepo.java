package com.assismoraes.bank.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assismoraes.bank.models.Transaction;

@Repository
public interface TransationRepo extends JpaRepository<Transaction, Long> {

}
