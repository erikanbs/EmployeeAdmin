package com.ekholabs.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "operating_plan_line")
public class OperatingPlanLine {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OPE_SEQ")
	@SequenceGenerator(name = "OPE_SEQ", sequenceName = "OPE_SEQ", allocationSize = 1)
	private int id;

	@Column(name = "opn_id")
	private int opnId;

	private Date mailDate;

	private Date closeDate;

	@Column(name = "campaign_number")
	private String campaignNumber;

	private String country;

	private String insurer;

	private String listowner;

	public OperatingPlanLine() {
	}

	public OperatingPlanLine(int opnId, Date mailDate, Date closeDate, String campaignNumber, String country,
			String insurer, String listowner) {
		this.opnId = opnId;
		this.mailDate = mailDate;
		this.closeDate = closeDate;
		this.campaignNumber = campaignNumber;
		this.country = country;
		this.insurer = insurer;
		this.listowner = listowner;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOpnId() {
		return opnId;
	}

	public void setOpnId(int opnId) {
		this.opnId = opnId;
	}

	public Date getMailDate() {
		return mailDate;
	}

	public void setMailDate(Date mailDate) {
		this.mailDate = mailDate;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public String getCampaignNumber() {
		return campaignNumber;
	}

	public void setCampaignNumber(String campaignNumber) {
		this.campaignNumber = campaignNumber;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getInsurer() {
		return insurer;
	}

	public void setInsurer(String insurer) {
		this.insurer = insurer;
	}

	public String getListowner() {
		return listowner;
	}

	public void setListowner(String listowner) {
		this.listowner = listowner;
	}

	@Override
	public String toString() {
		return "OperatingPlanLine [id=" + id + ", opnId=" + opnId + ", mailDate=" + mailDate + ", closeDate="
				+ closeDate + ", campaignNumber=" + campaignNumber + ", country=" + country + ", insurer=" + insurer
				+ ", listowner=" + listowner + "]";
	}

}
