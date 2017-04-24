package com.ekholabs.controller;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.View;

import com.ekholabs.dto.EmployeeDto;
import com.ekholabs.model.BusinessUnit;
import com.ekholabs.model.Employee;
import com.ekholabs.service.BusinessUnitService;
import com.ekholabs.service.EmployeeService;

@RunWith(SpringRunner.class)
@WebAppConfiguration
public class MainControllerTest {

	private MockMvc mockMvc;

	@Spy
	private ModelMapper modelMapper;

	@Mock
	private EmployeeService employeeService;

	@Mock
	private BusinessUnitService businessUnitService;

	@Mock
	View mockView;

	@InjectMocks
	private MainController mainController;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(mainController).setSingleView(mockView).build();
	}

	@Test
	public void testAllEmployees() throws Exception {
		final BusinessUnit but = new BusinessUnit("y");
		final Employee employeeEntity = new Employee("Erika", "erika@", 1, 1, but);
		final List<Employee> expectedEmployees = Arrays.asList(employeeEntity);

		final EmployeeDto expectedDto = new EmployeeDto();
		expectedDto.setBusinessUnit("but desc");
		expectedDto.setEmail("erika@");
		expectedDto.setFullName("Erika");

		when(employeeService.findAll()).thenReturn(expectedEmployees);
		when(businessUnitService.findBusinessUnitNls(but, "us")).thenReturn("but desc");
		when(modelMapper.map(employeeEntity, EmployeeDto.class)).thenReturn(expectedDto);

		mockMvc.perform(get("/ReMarkReports/all-employees"))
			.andExpect(status().isOk())
			.andExpect(request().attribute("employees", contains(expectedDto)))
			.andExpect(request().attribute("mode", "MODE_EMPLOYEES"))
			.andExpect(view().name("index"));

		verify(employeeService, times(1)).findAll();
		verify(businessUnitService, times(1)).findBusinessUnitNls(but, "us");
		verify(modelMapper, times(1)).map(employeeEntity, EmployeeDto.class);
	}

	@Test
	public void testConvertToDto() {
		final BusinessUnit but = new BusinessUnit("y");
		final List<Employee> expectedEmployees = Arrays.asList(new Employee("Erika", "erika@", 1, 1, but));
		when(businessUnitService.findBusinessUnitNls(but, "us")).thenReturn("but desc");
		
		final EmployeeDto employeeDto = mainController.convertToDto(expectedEmployees.get(0));
		assertEquals("Erika", employeeDto.getFullName());
		assertEquals("but desc", employeeDto.getBusinessUnit());
	}

	@Test
	public void testResultSearchEmployee() throws Exception {
		final BusinessUnit but = new BusinessUnit("y");
		final List<Employee> expectedEmployees = Arrays.asList(new Employee("Erika", "erika@", 1, 1, but));
		when(employeeService.findEmployee("Erika")).thenReturn(expectedEmployees);

		mockMvc.perform(get("/ReMarkReports/result-search-employee")
			.param("fullName", "Erika").param("email", "erika@"))
			.andExpect(status().isOk()).andExpect(request().attribute("employees", expectedEmployees))
			.andExpect(request().attribute("mode", "MODE_SEARCH")).andExpect(view().name("index"));
	}
}