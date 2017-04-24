package com.ekholabs.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ekholabs.model.BusinessUnitNls;

public interface BusinessUnitNlsRepository extends CrudRepository<BusinessUnitNls, Serializable> {

	@Query("select but from business_unit_nls but where but.language = ?1")
	BusinessUnitNls findBusinessUnitNls(String language);

}
