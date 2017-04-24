package com.ekholabs.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.util.mime.MimeUtility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ekholabs.dto.EmployeeDto;
import com.ekholabs.model.BusinessUnit;
import com.ekholabs.model.Employee;
import com.ekholabs.service.BusinessUnitService;
import com.ekholabs.service.EmployeeService;

@Controller
@RequestMapping("/admin")
public class MainController {

	private static final String INDEX = "index";
	
	private Logger log = Logger.getLogger("mainController");

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private BusinessUnitService businessUnitService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public String home(HttpServletRequest request) {
		String user = getUserName(request.getHeader("Osso-User-Dn"));
		request.setAttribute("mode", "MODE_HOME");
		request.setAttribute("user", user);
		return INDEX;
	}

	@GetMapping("/all-employees")
	public String allEmployees(HttpServletRequest request) {
		List<Employee> employees = employeeService.findAll();

		request.setAttribute("employees",
				employees.stream().map(employee -> convertToDto(employee)).collect(Collectors.toList()));

		request.setAttribute("mode", "MODE_EMPLOYEES");
		return INDEX;
	}

	@GetMapping("/new-employee")
	public String newEmployee(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_NEW");
		request.setAttribute("businessUnits", employeeService.findAllBusinessUnitNls());
		return INDEX;
	}

	@PostMapping("/save-employee")
	public String saveEmployee(@ModelAttribute Employee employee, BindingResult bidingResultEmployee,
			HttpServletRequest request) {

		Employee employeeSave = null;
		// If it is a new employee, the id == 0
		if (employee.getId() != 0) {
			employeeSave = employeeService.findEmployee(employee.getId());
		} else {
			employeeSave = employee;
		}

		final int id = Integer.valueOf(request.getParameter("businessUnit"));
		final BusinessUnit but = businessUnitService.findBusinessUnit(id);
		employeeSave.setBusinessUnit(but);
		employeeSave.setEmail(employee.getEmail());
		employeeService.save(employeeSave);

		final List<Employee> employees = employeeService.findAll();
		request.setAttribute("employees",
				employees.stream().map(employeeMap -> convertToDto(employeeMap)).collect(Collectors.toList()));
		request.setAttribute("mode", "MODE_EMPLOYEES");
		return INDEX;

	}

	@GetMapping("/update-employee")
	public String updateEmployee(@RequestParam int id, HttpServletRequest request) {
		request.setAttribute("employee", employeeService.findEmployee(id));
		request.setAttribute("businessUnits", businessUnitService.findAllNls());
		request.setAttribute("mode", "MODE_UPDATE");
		return INDEX;

	}

	@GetMapping("/delete-employee")
	public String deleteEmployee(@RequestParam int id, HttpServletRequest request) {
		employeeService.deleteEmployee(id);
		List<Employee> employees = employeeService.findAll();
		request.setAttribute("employees",
				employees.stream().map(employee -> convertToDto(employee)).collect(Collectors.toList()));
		request.setAttribute("mode", "MODE_EMPLOYEES");
		return INDEX;
	}

	@GetMapping("/search-employee")
	public String searchEmployee(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_SEARCH");
		return INDEX;
	}

	@GetMapping("/result-search-employee")
	public String resultSearchEmployee(@RequestParam String fullName, @RequestParam String email,
			HttpServletRequest request) {
		final List<Employee> results = employeeService.findEmployee(fullName);
		results.addAll(employeeService.findEmployeeByEmail(email));

		request.setAttribute("employees", results);
		request.setAttribute("mode", "MODE_SEARCH");
		return INDEX;
	}

	public EmployeeDto convertToDto(Employee employee) {
		final EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
		final BusinessUnit but = employee.getBusinessUnit();

		if (but != null) {
			employeeDto.setBusinessUnit(businessUnitService.findBusinessUnitNls(but, "us"));
		}

		return employeeDto;
	}

	private Employee convertToEntity(EmployeeDto employeeDto) {
		return modelMapper.map(employeeDto, Employee.class);
	}

	private String getUserName(String userDn) {
		String user = "";
		if (userDn != null) {
			try {
				user = MimeUtility.decodeText(userDn);
				int beginIndex = user.indexOf("=");
				int endIndex = user.indexOf(",");
				user = user.substring(beginIndex + 1, endIndex);
			} catch (UnsupportedEncodingException e) {
				log.info("get User " + e.getMessage());
			}
		}
		return user;
	}
}