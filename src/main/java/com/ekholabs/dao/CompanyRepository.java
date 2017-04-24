package com.ekholabs.dao;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.ekholabs.model.Company;

public interface CompanyRepository extends CrudRepository<Company, Serializable> {

}
