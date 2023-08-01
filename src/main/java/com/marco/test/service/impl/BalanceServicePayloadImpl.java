package com.marco.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marco.test.model.BalanceResponsePayload;
import com.marco.test.repository.BalanceResponseRepository;
import com.marco.test.service.BalanceResponsePayloadService;

@Service
public class BalanceServicePayloadImpl implements BalanceResponsePayloadService {
	@Autowired
	BalanceResponseRepository balanceResponseRepository; 
	
	@Override
	public void save(BalanceResponsePayload balance) {
		balanceResponseRepository.save(balance);
	}
}
