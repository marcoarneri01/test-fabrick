package com.marco.test.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "payload")
public class Payload {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<TransactionResponsePayload> list;


	public List<TransactionResponsePayload> getList() {
		return list;
	}

	public void setList(List<TransactionResponsePayload> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "Payload [id=" + id + ", list=" + list + "]";
	}
}