package com.marco.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.marco.test.model.TransactionType;

public interface TransactionTypeRepository extends JpaRepository<TransactionType, Long> {
}
