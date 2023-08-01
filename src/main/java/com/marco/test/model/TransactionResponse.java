package com.marco.test.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "balance")
public class TransactionResponse implements Serializable {
	private static final long serialVersionUID = 5800855237954038917L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@JsonProperty("status")
	private String status;
	
	@ElementCollection
	private List<String> error;

	@OneToOne(cascade = CascadeType.ALL)
	private Payload payload;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getError() {
		return error;
	}

	public void setError(List<String> error) {
		this.error = error;
	}

	public Payload getPayload() {
		return payload;
	}

	public void setPayload(Payload payload) {
		this.payload = payload;
	}
}
