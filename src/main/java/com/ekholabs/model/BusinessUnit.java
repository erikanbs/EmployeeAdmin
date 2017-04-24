package com.ekholabs.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.ColumnDefault;

@Entity(name = "business_unit")
public class BusinessUnit implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BUT_SEQ")
	@SequenceGenerator(name = "BUT_SEQ", sequenceName = "BUT_SEQ", allocationSize = 1)
	private int id;

	@Column(name = "is_corporate_y_n")
	private String isCorporate;

	@Column(name = "entered_ree_id")
	@ColumnDefault(value = "1")
	private int enteredReeId;

	@Column(name = "entered_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date enteredDate;

	@Column(name = "modified_ree_id")
	@ColumnDefault(value = "1")
	private int modifiedReeId;

	@Column(name = "modified_date")
	@Temporal(TemporalType.TIMESTAMP)
	@Version
	private Date modifiedDate;

	@OneToMany(mappedBy = "businessUnit", cascade = CascadeType.REFRESH)
	private Set<Employee> employees;

	@OneToMany(mappedBy = "businessUnit")
	private Set<BusinessUnitNls> businessUnitNls;

	public BusinessUnit() {
	}

	public BusinessUnit(String isCorporate) {
		this.isCorporate = isCorporate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIsCorporate() {
		return isCorporate;
	}

	public void setIsCorporate(String isCorporate) {
		this.isCorporate = isCorporate;
	}

	public int getEnteredReeId() {
		return enteredReeId;
	}

	public void setEnteredReeId(int enteredReeId) {
		this.enteredReeId = enteredReeId;
	}

	public Date getEnteredDate() {
		return enteredDate;
	}

	public void setEnteredDate(Date enteredDate) {
		this.enteredDate = enteredDate;
	}

	public int getModifiedReeId() {
		return modifiedReeId;
	}

	public void setModifiedReeId(int modifiedReeId) {
		this.modifiedReeId = modifiedReeId;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public Set<BusinessUnitNls> getBusinessUnitNls() {
		return businessUnitNls;
	}

	public void setBusinessUnitNls(Set<BusinessUnitNls> businessUnitNls) {
		this.businessUnitNls = businessUnitNls;
	}

}
