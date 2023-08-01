package com.marco.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.marco.test.model.TransactionType;
import com.marco.test.repository.TransactionTypeRepository;
import com.marco.test.service.TransactionTypeService;

@Service
public class TransactionTypeServiceImpl implements TransactionTypeService {
	
	@Autowired
    private TransactionTypeRepository transactionTypeRepository;

    @Override
    public TransactionType saveTransactionType(TransactionType transactionType) {
        return transactionTypeRepository.save(transactionType);
    }
}
