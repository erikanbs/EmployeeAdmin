package com.ekholabs.dto;

import java.sql.Date;

public class OperatingPlanDto {

	private int id;

	private int opYear;

	private int opnId;

	private Date mailDate;

	private Date closeDate;

	private String campaignNumber;

	private String country;

	private int countryId;

	private int currencyId;

	private String insurer;

	private String listowner;

	private int bteId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOpYear() {
		return opYear;
	}

	public void setOpYear(int opYear) {
		this.opYear = opYear;
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

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public int getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}

	public int getBteId() {
		return bteId;
	}

	public void setBteId(int bteId) {
		this.bteId = bteId;
	}

}
