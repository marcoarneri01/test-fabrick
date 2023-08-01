package com.marco.test.model;

import java.io.Serializable;

public class MoneyTransferResponse implements Serializable {
	private static final long serialVersionUID = 346295589215449927L;
	
	private String code;
    private String description;
    
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
