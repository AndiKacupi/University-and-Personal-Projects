package com.example.demo.hrm.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.hrm.entity.Employee;
import com.example.demo.hrm.entity.SalaryReport;
import com.example.demo.hrm.service.SalarayService;

@WebMvcTest(controllers =SalaryController.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false) //this annotation disables sign in so that the test can take place, we get 401 status code without this annotation
public class SalaryControllerMockitoTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private SalarayService salarayService;
	
	@Test
	void shouldReturnAllSalaries() throws Exception
	{
		//given
		List<SalaryReport> salaryList = new ArrayList<>();
		
		//when
		when(salarayService.getAllSalaries()).thenReturn(salaryList);
		
		//then
		this.mockMvc.perform(get("/salaries")).andExpect(status().isOk());
	}
	
	@Test
	void shouldGroupSalariesByEmployee() throws Exception
	{
		//given
		List<SalaryReport> salaryList = new ArrayList<>();
		int empId = 9;
		
		//when
		when(salarayService.getSalariesForEveryEmployee(empId)).thenReturn(salaryList);
		
		//then
		this.mockMvc.perform(get("/salaries/by/{emp_id}",empId)).andExpect(status().isOk());
	}
	
	@Test
	void shouldEditSalaryById() throws Exception
	{
		//given
		Employee employee= new Employee("Jim","Sales", "123", 11, "jim@halpert.com"); 
		SalaryReport salaryReport = new SalaryReport("Jim",7,"May", 21, 2000, 200, employee);
		salaryReport.setId(7L);
		Long theId = salaryReport.getId();
		
		//when
		when(salarayService.getSalaryById(theId)).thenReturn(salaryReport);
		
		//then
		this.mockMvc.perform(get("/salaries/edit/{id}",theId)).andExpect(status().isOk());
	}
	
	@Test
	void shouldUpdateSalary() throws Exception
	{
		//given
		Employee employee= new Employee("Jim","Sales", "123", 11, "jim@halpert.com"); 
		SalaryReport salaryReport = new SalaryReport("Jim",7,"May", 21, 2000, 200, employee);
		salaryReport.setId(7L);
		Long theId = salaryReport.getId();
		
		//when
		when(salarayService.updateSalary(salaryReport)).thenReturn(salaryReport);
		
		//then
		this.mockMvc.perform(post("/salaries/{id}",theId)
				.param("name","Jim")).andExpect(redirectedUrl("/salaries"))
				.andExpect(status().isFound());
	}
	
	@Test
	void shouldGenerateStatisticsAboutMonthlySalaries() throws Exception
	{
		//given
		Map<String, Integer> statistics = new HashMap<String,Integer>();
		
		//when
		when(salarayService.statisticsAboutSalariesEachMonth()).thenReturn(statistics);
		
		//then
		this.mockMvc.perform(get("/generalMonthlyStatistics")).andExpect(status().isOk());
	}
	
	@Test
	void shouldGenerateStatisticsAboutPersonalSalaries() throws Exception
	{
		//given
		Map<String, Double> statistics = new HashMap<String,Double>();
		String empName="Jim";
		int empId=9;
		//when
		when(salarayService.getSalariesStatistics()).thenReturn(statistics);
		
		//then
		this.mockMvc.perform(get("/salaries/{emp_name}/{emp_Id}/statistics",empName,empId)).andExpect(status().isOk());
	}
	
	@Test
	void shouldDeleteSalaryById() throws Exception
	{
		//given
		Employee employee= new Employee("Jim","Sales", "123", 11, "jim@halpert.com"); 
		SalaryReport salaryReport = new SalaryReport("Jim",7,"May", 21, 2000, 200, employee);
		salaryReport.setId(7L);
		Long theId = salaryReport.getId();
		
		//then
		this.mockMvc.perform(get("/salaries/{id}",theId))
					.andExpect(redirectedUrl("/salaries"))
					.andExpect(status().isFound());
	}
}
