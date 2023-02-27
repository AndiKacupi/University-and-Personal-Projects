package com.example.demo.hrm.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.hrm.entity.Employee;
import com.example.demo.hrm.entity.LeavesReport;

@DataJpaTest
public class LeavesReportRepositoryTest {
	
	@Autowired
	private LeaveReportRepository underTest; 
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@AfterEach
	void tearDown()
	{
		underTest.deleteAll();
	}
	
	@Test
	void itShouldSelectLeaveReportsByEmployeeId()
	{
		//given
		int empId = 1;
		Employee employee1 = new Employee("Michael", "Sales", "321" , 1, "michael@scott.com");

		employeeRepository.save(employee1);
		
		LeavesReport report1 = new LeavesReport("Michael",1,"June", 3 , 5, "Basketball",employee1);
		underTest.save(report1);
		
		report1.setEmployee(employee1);
		
		LeavesReport report2 = new LeavesReport("Michael",1,"June", 4 , 5, "Football",employee1);
		underTest.save(report2);
		
		report2.setEmployee(employee1);
		
		List<LeavesReport> testList2 = new ArrayList<>();
		testList2.add(report1);
		testList2.add(report2);
		
		//when
		List<LeavesReport> testList = underTest.getLeavesForEveryEmployee(empId);
		
		//then
		assertTrue(testList.size() == testList2.size() && testList.containsAll(testList2) && testList2.containsAll(testList));
	}
}

