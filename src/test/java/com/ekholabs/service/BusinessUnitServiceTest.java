package com.ekholabs.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.util.List;

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

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@WebAppConfiguration
public class BusinessUnitServiceTest {

	@Autowired
	private BusinessUnitService businessUnitService;

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

	}

	@Test
	public void testFindAll() {
		final List<BusinessUnit> businessUnits = businessUnitService.findAll();
		assertFalse(businessUnits.isEmpty());
	}

	@Test
	public void testFindAllNls() {
		final List<BusinessUnitNls> businessUnitsNls = businessUnitService.findAllNls();
		assertFalse(businessUnitsNls.isEmpty());
	}

	@Test
	public void testFindBusinessUnitNls() {
		final List<BusinessUnit> businessUnits = businessUnitService.findAll();
		final String but = businessUnitService.findBusinessUnitNls(businessUnits.get(0), "us");
		assertEquals("Bu English", but);
		assertNull(businessUnitService.findBusinessUnitNls(businessUnits.get(0), "u"));
	}

}
