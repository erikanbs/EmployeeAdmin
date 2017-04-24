package com.ekholabs.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "business_unit_nls")
@IdClass(BusinessUnitNlsPk.class)
public class BusinessUnitNls implements Serializable {

	@ManyToOne
	@JoinColumn(name = "but_id")
	@Id
	private BusinessUnit businessUnit;

	@Id
	private String language;

	private String description;

	public BusinessUnitNls() {
	}

	public BusinessUnitNls(BusinessUnit businessUnit, String language, String description) {
		this.businessUnit = businessUnit;
		this.language = language;
		this.description = description;
	}

	public BusinessUnit getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(BusinessUnit businessUnit) {
		this.businessUnit = businessUnit;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
