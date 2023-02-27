package com.example.demo.hrm.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.hrm.entity.Employee;

@DataJpaTest
public class EmployeeRepositoryTest {
	
	@Autowired
	private EmployeeRepository underTest; 
	
	@AfterEach
	void tearDown()
	{
		underTest.deleteAll();
	}

	@Test
	void itShouldSelectEmployeesForEveryDepartment()
	{
		//given
		String dep = "Sales";
		Employee employee1 = new Employee("Michael", "Sales", "321" , 1, "michael@scott.com");
		underTest.save(employee1);
		Employee employee2 = new Employee("Jim", "Sales", "345" , 2, "jim@halpert.com");
		underTest.save(employee2);
		
		List<Employee> testList2 = new ArrayList<>();
		testList2.add(employee1);
		testList2.add(employee2);
		
		//when
		List<Employee> testList = underTest.getEmployeesForEveryDepartment(dep);
		
		//then
		assertTrue(testList.size() == testList2.size() && testList.containsAll(testList2) && testList2.containsAll(testList));
	}

	@Test
	void itShouldCheckIfEmployeeIdExists()
	{
		//given
		int empId= 1;
		Employee employee1 = new Employee("Michael", "Sales", "321" , 1, "michael@scott.com");
		underTest.save(employee1);
		
		//when
		boolean answer=true;
		boolean exists = underTest.checkIfIdExists(empId);
		
		//then
		assertEquals(answer,exists);
	}
	
	@Test
	void itShouldCheckIfEmployeeExists()
	{
		//given
		String name="Michael";
		int empId= 1;
		
		Employee employee1 = new Employee("Michael", "Sales", "321" , 1, "michael@scott.com");
		underTest.save(employee1);
		
		//when
		boolean answer=true;
		boolean exists = underTest.checkIfEmployeeExists(name, empId);
		
		//then
		assertEquals(answer,exists);
	}
	
	@Test
	void itShouldSelectEmployeesByNameAndId()
	{
		//given
		String name="Michael";
		int empId= 1;
		
		Employee employee1 = new Employee("Michael", "Sales", "321" , 1, "michael@scott.com");
		underTest.save(employee1);
		Employee employee2 = new Employee("Jim", "Sales", "345" , 2, "jim@halpert.com");
		underTest.save(employee2);
						
		//when
		Employee answer = underTest.selectEmployeeByNameAndId(name, empId);;
		
		//then
		assertEquals(employee1,answer);
	}
}