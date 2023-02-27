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
import com.example.demo.hrm.entity.LeavesReport;
import com.example.demo.hrm.service.LeavesReportService;

@WebMvcTest(controllers =LeavesReportController.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false) //this annotation disables sign in so that the test can take place, we get 401 status code without this annotation
public class LeavesReportControllerMockitoTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private LeavesReportService leavesReportService;
	
	@Test
	void shouldReturnAllLeaveReports() throws Exception
	{
		//given
		List<LeavesReport> leavesList = new ArrayList<>();
		
		//when
		when(leavesReportService.getAllReports()).thenReturn(leavesList);
		
		//then
		this.mockMvc.perform(get("/leaveReports")).andExpect(status().isOk());
	}
	
	@Test
	void shouldGroupLeavesByEmployee() throws Exception
	{
		//given
		List<LeavesReport> leavesList = new ArrayList<>();
		int empId = 7;
		
		//when
		when(leavesReportService.getLeavesForEveryEmployee(empId)).thenReturn(leavesList);
		
		//then
		this.mockMvc.perform(get("/leaveReports/by/{emp_id}",empId)).andExpect(status().isOk());
	}
	
	@Test
	void shouldEditLeaveById() throws Exception
	{
		//given
		Employee employee= new Employee("Jim","Sales", "123", 11, "jim@halpert.com"); 
		LeavesReport leavesReport = new LeavesReport("Jim",11,"June", 5, 7, "Football Match",employee); 
		leavesReport.setId(7L);
		Long theId = leavesReport.getId();
		
		//when
		when(leavesReportService.getReportById(theId)).thenReturn(leavesReport);
		
		//then
		this.mockMvc.perform(get("/leaveReport/edit/{id}",theId)).andExpect(status().isOk());
	}
	
	@Test
	void shouldUpdateLeaveReport() throws Exception
	{
		//given
		Employee employee= new Employee("Jim","Sales", "123", 11, "jim@halpert.com"); 
		LeavesReport leavesReport = new LeavesReport("Jim",11,"June", 5, 7, "Football Match",employee); 
		leavesReport.setId(7L);
		Long theId = leavesReport.getId();
		
		//when
		when(leavesReportService.upadateReport(leavesReport)).thenReturn(leavesReport);
		
		//then
		this.mockMvc.perform(post("/leaveReports/{id}",theId)
				.param("name","Jim")).andExpect(redirectedUrl("/leaveReports"))
				.andExpect(status().isFound());
	}
	
	@Test
	void shouldGenerateStatisticsAboutLeaves() throws Exception
	{
		//given
		Map<String, Integer> statistics = new HashMap<String,Integer>();
		
		//when
		when(leavesReportService.statisticsAboutLeaves()).thenReturn(statistics);
		
		//then
		this.mockMvc.perform(get("/generalLeavesStatistics")).andExpect(status().isOk());
	}
	
	@Test
	void shouldDeleteLeaveReportById() throws Exception
	{
		//given
		Employee employee= new Employee("Jim","Sales", "123", 11, "jim@halpert.com"); 
		LeavesReport leavesReport = new LeavesReport("Jim",11,"June", 5, 7, "Football Match",employee); 
		leavesReport.setId(7L);
		Long theId = leavesReport.getId();
		
		//then
		this.mockMvc.perform(get("/deleteReport/{id}",theId))
					.andExpect(redirectedUrl("/leaveReports"))
					.andExpect(status().isFound());
	}
}
