package com.ekholabs.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.ColumnDefault;

@Entity(name = "remark_employee")
public class Employee implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REE_SEQ")
	@SequenceGenerator(name = "REE_SEQ", sequenceName = "REE_SEQ", allocationSize = 1)
	private int id;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "e_mail")
	private String email;

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

	@ManyToOne
	@JoinColumn(name = "but_id")
	private BusinessUnit businessUnit;

	Employee() {
	}

	public Employee(String fullName, String email, int enteredReeId, int modifiedReeId, BusinessUnit businessUnit) {
		this.fullName = fullName;
		this.email = email;
		this.enteredReeId = enteredReeId;
		this.modifiedReeId = modifiedReeId;
		this.businessUnit = businessUnit;
	}

	public int getEnteredReeId() {
		return enteredReeId;
	}

	public void setEnteredReeId(int enteredReeId) {
		this.enteredReeId = enteredReeId;
	}

	public int getModifiedReeId() {
		return modifiedReeId;
	}

	public void setModifiedReeId(int modifiedReeId) {
		this.modifiedReeId = modifiedReeId;
	}

	public Date getEnteredDate() {
		return enteredDate;
	}

	public void setEnteredDate(Date enteredDate) {
		this.enteredDate = enteredDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BusinessUnit getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(BusinessUnit businessUnit) {
		this.businessUnit = businessUnit;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", fullName=" + fullName + ", email=" + email + ", enteredReeId=" + enteredReeId
				+ ", enteredDate=" + enteredDate + ", modifiedReeId=" + modifiedReeId + ", modifiedDate=" + modifiedDate
				+ ", businessUnit=" + businessUnit + ", businessUnitNls=" + "]";
	}
}