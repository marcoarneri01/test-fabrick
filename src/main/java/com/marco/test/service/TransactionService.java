package com.marco.test.service;

import com.marco.test.model.TransactionResponsePayload;

public interface TransactionService {
    TransactionResponsePayload saveTransaction(TransactionResponsePayload transaction);
}