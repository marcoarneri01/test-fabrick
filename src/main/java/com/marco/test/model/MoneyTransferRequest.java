package com.marco.test.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TransferRequest")
public class MoneyTransferRequest implements Serializable {
	private static final long serialVersionUID = -2295738192015826116L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private long accountId;
    private String receiverName;
    private String description;
    private String currency;
    private String amount;
    private String executionDate;
   
	public MoneyTransferRequest() {
	}
	
	public MoneyTransferRequest(
			String receiverName, 
			String description, 
			String currency,
			String amount, 
			String executionDate) {
		this.receiverName = receiverName;
		this.description = description;
		this.currency = currency;
		this.amount = amount;
		this.executionDate = executionDate;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getExecutionDate() {
		return executionDate;
	}
	public void setExecutionDate(String executionDate) {
		this.executionDate = executionDate;
	}
}