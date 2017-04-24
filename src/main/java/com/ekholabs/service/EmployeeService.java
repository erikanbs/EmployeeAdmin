package com.ekholabs.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ekholabs.dao.EmployeeRepository;
import com.ekholabs.model.BusinessUnitNls;
import com.ekholabs.model.Employee;

@Service
@Transactional
public class EmployeeService {

	private final EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	// List of all employees with specific Business unit
	public Map<Employee, String> findAll(String language) {
		Map<Employee, String> employees = new HashMap<Employee, String>();
		BusinessUnitNls bus;
		for (Employee employee : employeeRepository.findAll()) {
			bus = employeeRepository.findBusinessUnitNls(language);
			employees.put(employee, bus.getDescription());
		}
		return employees;
	}

	public List<BusinessUnitNls> findAllBusinessUnitNls() {
		List<BusinessUnitNls> business = new ArrayList<>();
		for (BusinessUnitNls bus : employeeRepository.findAllBusinessUnitNls("us")) {
			business.add(bus);
		}
		return business;
	}

	public Employee findEmployee(int i) {
		return employeeRepository.findOne(i);
	}

	public List<Employee> findEmployee(String name) {
		List<Employee> employees = new ArrayList<>();
		if (name != null && name != "")
			employees = employeeRepository.findByfullNameIgnoreCaseContaining(name);
		return employees;
	}

	public List<Employee> findEmployeeByEmail(String email) {
		List<Employee> employees = new ArrayList<>();
		if (email != null && email != "")
			employees = employeeRepository.findByemailIgnoreCase(email);
		return employees;
	}

	public void save(Employee employee) {
		employeeRepository.save(employee);
	}

	public void deleteEmployee(int i) {
		Employee e = employeeRepository.findOne(i);
		employeeRepository.delete(e);
	}

}
