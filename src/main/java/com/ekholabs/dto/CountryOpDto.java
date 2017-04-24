package com.ekholabs.dto;

public class CountryOpDto {

	private int ctyId;

	private String country;

	private int localCryId;

	private String localCryIsoCode;

	private int bteId;

	public CountryOpDto() {
	}

	public int getCtyId() {
		return ctyId;
	}

	public void setCtyId(int ctyId) {
		this.ctyId = ctyId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getLocalCryId() {
		return localCryId;
	}

	public void setLocalCryId(int localCryId) {
		this.localCryId = localCryId;
	}

	public String getLocalCryIsoCode() {
		return localCryIsoCode;
	}

	public void setLocalCryIsoCode(String localCryIsoCode) {
		this.localCryIsoCode = localCryIsoCode;
	}

	public int getBteId() {
		return bteId;
	}

	public void setBteId(int bteId) {
		this.bteId = bteId;
	}

}
