package com.marco.test.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transaction_type")
public class TransactionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String enumeration;
    private String value;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEnumeration() {
		return enumeration;
	}
	public void setEnumeration(String enumeration) {
		this.enumeration = enumeration;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	} 
}
