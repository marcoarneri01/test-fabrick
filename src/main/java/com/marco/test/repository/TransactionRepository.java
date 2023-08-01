package com.marco.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marco.test.model.TransactionResponsePayload;

public interface TransactionRepository extends JpaRepository<TransactionResponsePayload, Long> {

}
