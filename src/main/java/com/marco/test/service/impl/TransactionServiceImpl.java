package com.marco.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.marco.test.model.TransactionResponsePayload;
import com.marco.test.repository.TransactionRepository;
import com.marco.test.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
    private TransactionRepository transactionRepository;

    @Override
    public TransactionResponsePayload saveTransaction(TransactionResponsePayload transaction) {
        return transactionRepository.save(transaction);
    }
}

