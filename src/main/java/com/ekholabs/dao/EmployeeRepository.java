package com.ekholabs.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ekholabs.model.BusinessUnit;
import com.ekholabs.model.BusinessUnitNls;
import com.ekholabs.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Serializable> {

	List<Employee> findAll();

	List<Employee> findByfullNameIgnoreCase(String fullName);

	List<Employee> findByfullNameContaining(String fullName);

	List<Employee> findByfullNameIgnoreCaseContaining(String fullName);

	List<Employee> findByemailIgnoreCase(String email);

	@Query("select but from business_unit_nls but where but.language = ?1")
	List<BusinessUnitNls> findAllBusinessUnitNls(String language);

	@Query("select but from business_unit_nls but where but.language = ?1")
	BusinessUnitNls findBusinessUnitNls(String language);

	@Query("select but from business_unit_nls nls, business_unit but where but.id = nls.businessUnit.id and nls.language = ?1 ")
	List<BusinessUnit> findAllBusinessUnit();

}
