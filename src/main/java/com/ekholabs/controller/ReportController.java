package com.ekholabs.controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ekholabs.service.CompanyService;
import com.ekholabs.service.EmployeeService;

@Controller
public class ReportController {

	private static final String REPORT = "report";

	private Logger log = Logger.getLogger("reports");

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private CompanyService companyService;

	@GetMapping("/report")
	public String home(HttpServletRequest request) {
		request.setAttribute("username", request.getHeader("Osso-User-Dn"));
		log.info("user " + request.getHeader("Osso-User-Dn") + " " + request.getRemoteUser());
		request.setAttribute("employees", employeeService.findAll());
		request.setAttribute("reinsurers", companyService.findAllInsurers());
		return REPORT;
	}

	@GetMapping("/report/report-employee")
	public String allEmployees(HttpServletRequest request) {
		request.setAttribute("employees", employeeService.findAll());
		request.setAttribute("mode", "MODE_EMPLOYEES");
		return REPORT;
	}
}