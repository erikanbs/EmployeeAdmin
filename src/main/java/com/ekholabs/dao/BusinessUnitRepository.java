package com.ekholabs.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ekholabs.model.BusinessUnit;

public interface BusinessUnitRepository extends CrudRepository<BusinessUnit, Serializable> {

	@Query("select but.description from business_unit_nls but where but.businessUnit = ?1 and but.language = ?2")
	String findBusinessUnitNls(BusinessUnit but, String language);

}
