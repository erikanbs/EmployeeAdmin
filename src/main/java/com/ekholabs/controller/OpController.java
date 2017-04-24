package com.ekholabs.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.util.mime.MimeUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ekholabs.dto.CountryOpDto;
import com.ekholabs.dto.OperatingPlanDto;
import com.ekholabs.service.OperatingPlanService;

@Controller
@RequestMapping("/report/op")
public class OpController {

	private static final String OP = "op";
	
	private Logger log = Logger.getLogger("opController");
	
	@Autowired
	private OperatingPlanService operatingPlanService;

	@GetMapping
	public String home(HttpServletRequest request, ModelMap model) {
		final String user = getUserName(request.getHeader("Osso-User-Dn"));
		request.setAttribute("mode", "MODE_HOME");
		request.setAttribute("user", user);
		model.addAttribute("opFormDetails", new OperatingPlanDto());
		return OP;
	}

	@PostMapping(path = "/search")
	public String opLiveSearch(HttpServletRequest request,
			@ModelAttribute("opFormDetails") OperatingPlanDto operatingPlanDto, BindingResult resultsForm) {

		final String user = getUserName(request.getHeader("Osso-User-Dn"));
		final int opYear = operatingPlanDto.getOpYear();
		final List<CountryOpDto> results = operatingPlanService.getOpCountryList(opYear);

		// Show the operating plan for the first year
		if (!results.isEmpty()) {
			final CountryOpDto firstResult = results.get(0);
			List<OperatingPlanDto> resultsOp = operatingPlanService.getOpList(opYear, firstResult.getCtyId(), 0,
					firstResult.getLocalCryId(), firstResult.getBteId(), "erika nattrodt");
			request.setAttribute("opResultList", resultsOp);
			operatingPlanDto.setCountry(firstResult.getCountry());
			operatingPlanDto.setCountryId(firstResult.getCtyId());
			operatingPlanDto.setBteId(firstResult.getBteId());
		}

		request.setAttribute("opFormDetails", operatingPlanDto);
		request.setAttribute("countries", results);
		request.setAttribute("opYear", opYear);
		request.setAttribute("user", user);
		request.setAttribute("mode", "MODE_SHOW");
		return OP;
	}

	@RequestMapping(path = "/country", method = RequestMethod.POST)
	public String opCountryResults(HttpServletRequest request,
			@ModelAttribute("opFormDetails") OperatingPlanDto operatingPlanDto, BindingResult result) {

		final String user = getUserName(request.getHeader("Osso-User-Dn"));
		final String countryId = request.getParameter("countryId");
		final String currencyId = request.getParameter("currencyId");
		final String bteId = request.getParameter("bteId");
		final int year = Integer.valueOf(request.getParameter("opYear"));

		// calling package to get results List<CountryOpDto> resultsCountry =
		// operatingPlanService.getCountryList(year);

		// calling native query
		final List<CountryOpDto> resultsCountry = operatingPlanService.getOpCountryList(year);

		request.setAttribute("countries", resultsCountry);

		final List<OperatingPlanDto> results = operatingPlanService.getOpList(year, Integer.valueOf(countryId), 0,
				Integer.valueOf(currencyId), Integer.valueOf(bteId), "erika nattrodt");

		operatingPlanDto.setCountry(countryId);
		request.setAttribute("opFormDetails", operatingPlanDto);
		request.setAttribute("opResultList", results);
		request.setAttribute("opYear", year);
		request.setAttribute("user", user);
		request.setAttribute("mode", "MODE_SHOW");
		return OP;
	}

	@ModelAttribute("opYearList")
	public HashMap<Integer, String> getOpYearList() {
		final HashMap<Integer, String> opList = new LinkedHashMap<Integer, String>();
		opList.put(2014, "2014");
		opList.put(2015, "2015");
		opList.put(2016, "2016");
		return opList;
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
				log.info("get User" + e.getMessage());
			}
		}
		return user;
	}
}