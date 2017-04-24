package com.ekholabs.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ekholabs.dao.BusinessUnitNlsRepository;
import com.ekholabs.dao.BusinessUnitRepository;
import com.ekholabs.model.BusinessUnit;
import com.ekholabs.model.BusinessUnitNls;

@Service
@Transactional
public class BusinessUnitService {

	private final BusinessUnitRepository businessUnitRepository;

	private final BusinessUnitNlsRepository businessUnitNlsRepository;

	public BusinessUnitService(BusinessUnitRepository businessUnitRepository,
			BusinessUnitNlsRepository businessUnitNlsRepository) {
		this.businessUnitRepository = businessUnitRepository;
		this.businessUnitNlsRepository = businessUnitNlsRepository;

	}

	public List<BusinessUnit> findAll() {
		List<BusinessUnit> businessUnits = new ArrayList<>();
		for (BusinessUnit businessUnit : businessUnitRepository.findAll()) {
			businessUnits.add(businessUnit);
		}
		return businessUnits;

	}

	public List<BusinessUnitNls> findAllNls() {
		List<BusinessUnitNls> businessUnitsNls = new ArrayList<>();
		for (BusinessUnitNls businessUnit : businessUnitNlsRepository.findAll()) {
			businessUnitsNls.add(businessUnit);
		}
		return businessUnitsNls;

	}

	public BusinessUnit findBusinessUnit(int i) {
		return businessUnitRepository.findOne(i);
	}

	public String findBusinessUnitNls(BusinessUnit but, String language) {

		return businessUnitRepository.findBusinessUnitNls(but, language);
	}

}
