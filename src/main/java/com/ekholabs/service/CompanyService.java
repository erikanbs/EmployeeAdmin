package com.ekholabs.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ekholabs.dao.CompanyRepository;
import com.ekholabs.model.Company;

@Service
@Transactional
public class CompanyService {

	private final CompanyRepository companyRepository;

	public CompanyService(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	public List<Company> findAllInsurers() {
		List<Company> companyList = new ArrayList<>();
		for (Company company : companyRepository.findAll()) {
			companyList.add(company);
		}
		return companyList;

	}

}
