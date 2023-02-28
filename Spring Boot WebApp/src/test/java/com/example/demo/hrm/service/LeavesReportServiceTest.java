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
import com.example.demo.hrm.entity.LeavesReport;
import com.example.demo.hrm.repository.EmployeeRepository;
import com.example.demo.hrm.repository.LeaveReportRepository;

@DataJpaTest
public class LeavesReportServiceTest {
	
	@Autowired
	private EmployeeRepository employeeRepository;	
	@Autowired
	private LeaveReportRepository leaveReportRepository;
	
	private LeavesReportServiceImpl underTest;
	
	@BeforeEach     
	void setup()
	{
		underTest = new LeavesReportServiceImpl(leaveReportRepository);
	}
		
	@Test
	void canUpdateReport()
	{
		//given
		Employee employee= new Employee("Jim","Sales", "123", 11, "jim@halpert.com"); 
		employeeRepository.save(employee);
		
		LeavesReport leavesReport = new LeavesReport("Jim",11,"June", 5, 7, "Football Match", employee);
		leaveReportRepository.save(leavesReport);  
		
		LeavesReport leavesReport2 = new LeavesReport("Jim",11,"June", 3, 6, "Basketball Match", employee);
		leavesReport2.setId(leavesReport.getId());
		
		//when
		LeavesReport leavesReport3 = underTest.upadateReport(leavesReport2);
		
		//then
		assertEquals(leavesReport.getName(), leavesReport3.getName()); 
		assertEquals(leavesReport.getFromDate(), leavesReport3.getFromDate()); 
		assertEquals(leavesReport.getToDate(), leavesReport3.getToDate()); 
		assertEquals(leavesReport.getReason(), leavesReport3.getReason());		
	}
	
	@Test
	void canSaveLeaveReport()
	{
		//given
		underTest.setEmployeeRepository(employeeRepository);
		Employee employee= new Employee("Jim","Sales", "123", 7, "jim@halpert.com"); 
		employeeRepository.save(employee);
		Employee employee1= new Employee("Dwight","Sales", "123", 8, "dwight@schrute.com"); 
		employeeRepository.save(employee1);
		
		LeavesReport leavesReport = new LeavesReport("Jim",7,"June", 5, 7, "Football Match", employee);
	
		//when
		LeavesReport leavesReport2=underTest.saveLeavesReport(leavesReport);
		
		//then
		assertEquals(leavesReport, leavesReport2);
	}
	
	@Test
	void canCheckIfEmployeeExists()
	{
		//given
		underTest.setEmployeeRepository(employeeRepository);
		Employee employee= new Employee("Jim","Sales", "123", 8, "jim@halpert.com"); 
		employeeRepository.save(employee);
		
		LeavesReport leavesReport = new LeavesReport("Jim",7,"June", 5, 7, "Football Match", employee);

		String correctMessage = "This Employee does NOT exist!";
		
		//when
		String actualMessage = underTest.checkIfEmployeeExists(leavesReport);
		
		//then
		assertEquals(correctMessage, actualMessage); 
	}
	
	@Test
	void canCalculateStatisticsAboutLeaves()
	{
		//given
		Employee employee= new Employee("Jim","Sales", "123", 7, "jim@halpert.com"); 
		employeeRepository.save(employee);
		
		LeavesReport leavesReport1 = new LeavesReport("Jim",7,"February", 5, 7, "Football Match", employee);
		leaveReportRepository.save(leavesReport1);
		LeavesReport leavesReport2 = new LeavesReport("Jim",7,"February", 8, 10, "Football Match", employee);
		leaveReportRepository.save(leavesReport2);

		Map<String, Integer> correctStats = new HashMap<>();
		correctStats.put("February", 4);
		correctStats.put("_Total Leaves_", 4);
		
		//when
		Map<String, Integer> actualStats = underTest.statisticsAboutLeaves();
		
		//then
		assertEquals(correctStats, actualStats);
		assertTrue(correctStats.equals(actualStats));
	}
}
