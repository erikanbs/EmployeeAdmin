package com.ekholabs.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import com.ekholabs.dto.CountryOpDto;
import com.ekholabs.dto.OperatingPlanDto;

@Service
public class OperatingPlanService {

	private SimpleJdbcCall procReadOp;

	private SimpleJdbcCall procReadCountryOp;

	private JdbcTemplate jdbcTemplateQuery;

	@Autowired
	private EntityManager em;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.setResultsMapCaseInsensitive(true);

		jdbcTemplateQuery = new JdbcTemplate(dataSource);

		this.procReadOp = new SimpleJdbcCall(jdbcTemplate).withCatalogName("opn_ccn_java").withFunctionName("query_opn")
				.returningResultSet("ope", BeanPropertyRowMapper.newInstance(OperatingPlanDto.class));

		this.procReadCountryOp = new SimpleJdbcCall(jdbcTemplate).withCatalogName("opn_ccn_java")
				.withFunctionName("query_country_opn")
				.returningResultSet("cty", BeanPropertyRowMapper.newInstance(CountryOpDto.class));

	}

	public List<OperatingPlanDto> getOpList(int opYear, int countryId, int opnId, int currencyId, int bteId,
			String user) {
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("p_plan_year", opYear);
		parameter.put("p_cty_id", countryId);
		parameter.put("p_opn_id", opnId);
		parameter.put("p_cry_id", currencyId);
		parameter.put("p_bte_id", bteId);
		parameter.put("p_user", user);

		SqlParameterSource in = new MapSqlParameterSource().addValues(parameter);

		Map m = procReadOp.execute(in);
		return (List) m.get("ope");
	}

	public List<CountryOpDto> getCountryList(int opYear) {

		String planFrom = opYear + "01";
		String planTo = opYear + "12";

		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("p_opn_id", 0);
		parameter.put("p_cty_id", 0);
		parameter.put("p_plan_from", planFrom);
		parameter.put("p_plan_until", planTo);
		parameter.put("p_user", "erika nattrodt");

		SqlParameterSource in = new MapSqlParameterSource().addValues(parameter);

		Map m = procReadCountryOp.execute(in);
		return (List) m.get("cty");

	}

	public List<CountryOpDto> getOpCountryList(int opYear) {

		String sql = "select distinct ope.cty_id cty_id, ope.country || decode(ope.bte_id, 3 ,'-DD','') country, ope.local_cry_id, ope.local_cry_iso_code, ope.bte_id from operating_plan_line ope, operating_plan opn where ope.opn_id = (select max(id) from operating_plan where op_year = ?1) and phase in (1,2,3,4) and op_status in ('Certain', 'Possible') and opn.id = ope.opn_id order by 2,4";

		// Query qListCountry = em.createNativeQuery("select distinct
		// ope.cty_id, ope.region_group_order, ope.country || decode(ope.bte_id,
		// 3 ,'-DD',''), ope.local_cry_id, ope.local_cry_iso_code from
		// operating_plan_line ope, operating_plan opn where ope.opn_id =
		// (select max(id) from operating_plan where op_year = ?1) and phase in
		// (1,2,3,4) and op_status in ('Certain', 'Possible') and opn.id =
		// ope.opn_id order by 2,3,5");
		// qListCountry.setParameter(1, opYear);
		// List<CountryOpDto> results = (List<CountryOpDto>)
		// qListCountry.getResultList();

		List<CountryOpDto> results = jdbcTemplateQuery.query(sql, new RowMapper<CountryOpDto>() {
			public CountryOpDto mapRow(ResultSet result, int rowNum) throws SQLException {
				CountryOpDto op = new CountryOpDto();
				op.setCtyId(result.getInt("cty_id"));
				op.setCountry(result.getString("country"));
				op.setLocalCryId(result.getInt("local_cry_id"));
				op.setLocalCryIsoCode(result.getString("local_cry_iso_code"));
				op.setBteId(result.getInt("bte_id"));
				return op;
			}
		}, opYear);

		return results;

	}

}
