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
import com.example.demo.hrm.service.EmployeeService;

@WebMvcTest(controllers =EmployeeController.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false) //this annotation disables sign in so that the test can take place, we get 401 status code without this annotation
public class EmployeeControllerMockitoTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EmployeeService employeeService;
	
	@Test
	void shouldReturnAllEmployees() throws Exception
	{
		//given
		List<Employee> employeeList = new ArrayList<>();
		
		//when
		when(employeeService.getAllEmployees()).thenReturn(employeeList);
		
		//then
		this.mockMvc.perform(get("/employees")).andExpect(status().isOk());
	}
	
	@Test
	void shouldGroupEmployeesByDepartment() throws Exception
	{
		//given
		List<Employee> employeeList = new ArrayList<>();
		String dep = "Sales";
		
		//when
		when(employeeService.getEmployeesForEveryDepartment(dep)).thenReturn(employeeList);
		
		//then
		this.mockMvc.perform(get("/employees/by/{dep}",dep)).andExpect(status().isOk());
	}
	
	@Test
	void shouldEditEmployeesById() throws Exception
	{
		//given
		Employee employee= new Employee("Jim","Sales", "123", 7, "jim@halpert.com"); 
		employee.setId(7L);
		Long theId = employee.getId();
		
		//when
		when(employeeService.getEmployeeById(theId)).thenReturn(employee);
		
		//then
		this.mockMvc.perform(get("/employee/edit/{id}",theId)).andExpect(status().isOk());
	}
	
	@Test
	void shouldUpdateEmployee() throws Exception
	{
		//given
		Employee employee= new Employee("Jim","Sales", "123", 7, "jim@halpert.com"); 
		employee.setId(7L);
		Long theId = employee.getId();
		
		//when
		when(employeeService.updateEmployee(employee)).thenReturn(employee);
		
		//then
		this.mockMvc.perform(post("/employees/{id}",theId)
				.param("name","Jim")).andExpect(redirectedUrl("/employees"))
				.andExpect(status().isFound());
	}
	
	@Test
	void shouldGenerateStatistics() throws Exception
	{
		//given
		Map<String, Long> generalStatistics = new HashMap<String,Long>();
		
		//when
		when(employeeService.statisticsAboutEmployees()).thenReturn(generalStatistics);
		
		//then
		this.mockMvc.perform(get("/generalStatistics")).andExpect(status().isOk());
	}
	
	@Test
	void shouldDeleteEmployeesById() throws Exception
	{
		//given
		Employee employee= new Employee("Jim","Sales", "123", 7, "jim@halpert.com"); 
		employee.setId(7L);
		Long theId = employee.getId();
		
		//then
		this.mockMvc.perform(post("/employees/{id}",theId))
					.andExpect(redirectedUrl("/employees"))
					.andExpect(status().isFound());
	}
	
	/*@Test
	void shouldSaveNewEmployee() throws Exception  //runs successfully when we put String err in comments in EmployeeController 
	{
		this.mockMvc.perform(post("/employees")
			    .param("id", "1L")
			    .param("name","Jim")
			    .flashAttr("employee", new Employee()))
				.andExpect(status().isOk());
	}*/
}
