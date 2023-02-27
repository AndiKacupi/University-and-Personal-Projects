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
import com.example.demo.hrm.entity.SalaryReport;
@DataJpaTest
public class SalaryRepositoryTest {
	
	@Autowired
	private SalaryRepository underTest;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@AfterEach
	void tearDown()
	{
		underTest.deleteAll();
	}
	
	@Test
	void itShouldSelectSalariesForEveryEmployee()
	{
		//given
		int empId = 1;

		Employee employee1 = new Employee("Michael", "Sales", "321" , 1, "michael@scott.com");

		employeeRepository.save(employee1);
		
		SalaryReport salarie1= new SalaryReport("Michael",1, "June", 21, 2000, 500, employee1);
		underTest.save(salarie1);
		salarie1.setEmployee(employee1);
		
		SalaryReport salarie2= new SalaryReport("Michael",1,"May", 21, 2100, 500, employee1);
		underTest.save(salarie2);
		salarie2.setEmployee(employee1);
		
		List<SalaryReport> testList2 = new ArrayList<>();
		testList2.add(salarie1);
		testList2.add(salarie2);
		
		//when
		List<SalaryReport> testList = underTest.getSalariesForEveryEmployee(empId);
		
		//then
		assertTrue(testList.size() == testList2.size() && testList.containsAll(testList2) && testList2.containsAll(testList));
	}
	
	@Test
	void itShouldCheckIfSalaryExists()
	{
		//given
		String name= "Michael";
		int empId = 1;
		String month="March";

		Employee employee1 = new Employee("Michael", "Sales", "321" , 1, "michael@scott.com");

		employeeRepository.save(employee1);
		
		SalaryReport salarie1= new SalaryReport("Michael",1, "March", 21, 2000, 500, employee1);
		underTest.save(salarie1);
		salarie1.setEmployee(employee1);
		
		SalaryReport salarie2= new SalaryReport("Michael",1,"May", 21, 2100, 500, employee1);
		underTest.save(salarie2);
		salarie2.setEmployee(employee1);
		
		//when
		boolean answer=true;
		boolean exists = underTest.checkIfSalaryExists(name,month,empId);
		
		//then
		assertEquals(answer,exists);
	}
}
