package com.example.demo.hrm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.hrm.entity.Employee;
import com.example.demo.hrm.repository.EmployeeRepository;

@DataJpaTest
public class EmployeeServiceTest {

	@Autowired
	private EmployeeRepository employeeRepository;

	private EmployeeServiceImpl underTest; 
	
	@BeforeEach
	void setup()
	{
		underTest=new EmployeeServiceImpl(employeeRepository);
	}
	
	@Test
	void canUpdateEmployee()
	{
		//given
		Employee employee= new Employee("Jim","Sales", "123", 7, "jim@halpert.com"); 
		underTest.saveEmployee(employee);
		
		Employee emp =new Employee("Dwight","Sales", "345", 9, "dwight@schrute.com"); 
		emp.setId(employee.getId());
		
		//when
		Employee employee1= underTest.updateEmployee(emp);
		
		//then
		assertEquals(employee.getName(), employee1.getName()); 
		assertEquals(employee.getDepartment(), employee1.getDepartment()); 
		assertEquals(employee.getContactNum(), employee1.getContactNum()); 
		assertEquals(employee.getEmail(), employee1.getEmail());
	}
	
	@Test
	void canCheckIfEmployeeIdExists()
	{
		//given
		Employee employee= new Employee("Jim","Sales", "123", 7, "jim@halpert.com"); 
		underTest.saveEmployee(employee);
		
		Employee employee2 =new Employee("Dwight","Sales", "345", 9, "dwight@schrute.com"); 
		employee2.setId(employee.getId());

		String correctMessage = "This Employee ID is already taken!";
		
		//when
		String actualMessage = underTest.checkIfEmployeeIdExists(employee);
		
		//then
		assertEquals(correctMessage, actualMessage); 
	}
	
	@Test
	void canCalculateStatisticsAboutEmployees()
	{
		//given
		Employee employee= new Employee("Jim","Sales", "123", 7, "jim@halpert.com"); 
		employeeRepository.save(employee);
		
		Employee employee2= new Employee("Michael","IT", "123", 3, "michael@scott.com"); 
		employeeRepository.save(employee2);
		
		Map<String, Long> correctStats = new HashMap<>();
		correctStats.put("Sales", 1L);
		correctStats.put("IT", 1L);
		correctStats.put("-Total Number of Employees", 2L);
		
		//when
		Map<String, Long> actualStats = underTest.statisticsAboutEmployees();
		
		//then
		assertEquals(correctStats, actualStats);
		assertTrue(correctStats.equals(actualStats));
	}
}
