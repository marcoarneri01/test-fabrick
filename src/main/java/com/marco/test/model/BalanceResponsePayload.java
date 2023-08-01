package com.marco.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "balance_payload")
public class BalanceResponsePayload {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JsonProperty("date")
	private String date;
	@JsonProperty("balance")
	private double balance;
	@JsonProperty("availableBalance")
	@Column(nullable = false, columnDefinition = "DECIMAL(10, 2) default 0.0")
	private double availableBalance;
	@JsonProperty("currency")
	private String currency;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(double availableBalance) {
		this.availableBalance = availableBalance;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "BalanceResponsePayload [date=" + date + ", balance=" + balance + ", availableBalance="
				+ availableBalance + ", currency=" + currency + "]";
	}
}
