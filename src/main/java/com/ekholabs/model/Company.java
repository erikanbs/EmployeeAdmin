package com.ekholabs.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "company")
public class Company implements Serializable {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private int id;
	private String name;
	@Column(name = "cot_id")
	private Integer cotId;

	public Company() {
	}

	public Company(String name, Integer cotId) {
		super();
		this.name = name;
		this.cotId = cotId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCotId() {
		return cotId;
	}

	public void setCotId(Integer cotId) {
		this.cotId = cotId;
	}

}
