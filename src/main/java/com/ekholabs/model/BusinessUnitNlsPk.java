package com.ekholabs.model;

import java.io.Serializable;

public class BusinessUnitNlsPk implements Serializable {
	protected int businessUnit;
	protected String language;

	public BusinessUnitNlsPk() {
	}

	public BusinessUnitNlsPk(int businessUnit, String language) {
		this.businessUnit = businessUnit;
		this.language = language;
	}

}
