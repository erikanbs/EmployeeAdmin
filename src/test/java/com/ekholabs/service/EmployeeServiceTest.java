package com.ekholabs.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ekholabs.dao.BusinessUnitNlsRepository;
import com.ekholabs.dao.BusinessUnitRepository;
import com.ekholabs.model.BusinessUnit;
import com.ekholabs.model.BusinessUnitNls;
import com.ekholabs.model.Employee;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@WebAppConfiguration
public class EmployeeServiceTest {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private BusinessUnitRepository businessUnitRepo;

	@Autowired
	private BusinessUnitNlsRepository businessUnitRepoNls;

	@Before
	public void setUp() throws Exception {
		final BusinessUnit bu1 = new BusinessUnit("y");
		businessUnitRepo.save(bu1);

		final BusinessUnitNls bu1Nls = new BusinessUnitNls(bu1, "us", "Bu English");
		businessUnitRepoNls.save(bu1Nls);

		final Employee employee = new Employee("Erika", "erikanbs@", 1, 2, bu1);
		employeeService.save(employee);
	}

	@Test
	public void testFindEmployeeInt() {
		final List<Employee> employees = employeeService.findAll();
		assertNotNull(employees);

		assertEquals(1, employees.size());
		assertEquals("Erika", employees.get(0).getFullName());
		assertEquals("erikanbs@", employees.get(0).getEmail());
	}

	@Test
	public void testFindAll() {
		final Map<Employee, String> employees = employeeService.findAll("us");
		final List<Employee> emp = employeeService.findEmployee("Erika");
		assertEquals("Bu English", employees.get(emp.get(0)));
	}

	@Test
	public void testFindAllBusinessUnitNls() {
		final List<BusinessUnitNls> business = employeeService.findAllBusinessUnitNls();
		assertNotNull(business);
		assertEquals("Bu English", business.get(0).getDescription());
	}

	@Test
	public void testDeleteEmployee() {
		final List<Employee> employees = employeeService.findEmployee("Erika");
		final Employee emp = employees.get(0);
		employeeService.deleteEmployee(emp.getId());
		assertTrue(employeeService.findEmployee("Erika").isEmpty());
	}
}