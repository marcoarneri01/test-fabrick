package com.marco.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marco.test.model.BalanceResponsePayload;

public interface BalanceResponseRepository extends JpaRepository<BalanceResponsePayload, Long>  {

}
