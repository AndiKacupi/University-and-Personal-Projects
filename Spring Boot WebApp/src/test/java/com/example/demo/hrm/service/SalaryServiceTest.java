package com.example.demo.hrm.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.hrm.entity.Employee;
import com.example.demo.hrm.entity.SalaryReport;
import com.example.demo.hrm.repository.EmployeeRepository;
import com.example.demo.hrm.repository.SalaryRepository;
import com.example.demo.hrm.service.statistics.MaxStatisticStrategy;
import com.example.demo.hrm.service.statistics.MeanStatisticStrategy;
import com.example.demo.hrm.service.statistics.MinStatisticStrategy;
import com.example.demo.hrm.service.statistics.StatisticStrategy;

@DataJpaTest
public class SalaryServiceTest {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private SalaryRepository salaryRepository;
	
	private SalarayServiceImpl underTest;
	
	@BeforeEach     
	void setup()
	{
		underTest = new SalarayServiceImpl(salaryRepository);
	}
	
	@Test
	void canSaveSalary()
	{
		//given
		underTest.setEmployeeRepository(employeeRepository);
		Employee employee= new Employee("Jim","Sales", "123", 7, "jim@halpert.com"); 
		employeeRepository.save(employee);
		Employee employee1= new Employee("Dwight","Sales", "123", 8, "dwight@schrute.com"); 
		employeeRepository.save(employee1);
		
		SalaryReport salaryReport = new SalaryReport("Jim",7,"May", 21, 2000, 200, employee);
		
		//when
		SalaryReport salaryReport2 = underTest.saveSalary(salaryReport);
		
		//then
		assertEquals(salaryReport, salaryReport2);
	}
	
	@Test
	void canUpdateSalary()
	{
		//given
		Employee employee= new Employee("Jim","Sales", "123", 11, "jim@halpert.com"); 
		employeeRepository.save(employee);
		
		SalaryReport salaryReport = new SalaryReport("Jim",11,"May", 21, 2000, 200, employee);
		salaryRepository.save(salaryReport);
		
		SalaryReport salaryReport1 = new SalaryReport("Jim",11,"March", 21, 2200, 300, employee);
		salaryReport1.setId(salaryReport.getId());
		
		//when
		SalaryReport salaryReport2 = underTest.updateSalary(salaryReport1);
		
		//then
		assertThat(salaryReport).isEqualTo(salaryReport2);
	}
	
	@Test
	void canGetSalariesStatistics()
	{
		//given
		Employee employee= new Employee("Jim","Sales", "123", 7, "jim@halpert.com"); 
		employeeRepository.save(employee);
		underTest.setEmployee(employee);
		
		SalaryReport salaryReport = new SalaryReport("Jim",7,"May", 21, 2000, 200, employee);
		salaryRepository.save(salaryReport);
		
		employee.addSalaryToSet(salaryReport);
				
		List<StatisticStrategy> strategies = underTest.getStatCalculationStrategies();
		strategies.add(new MinStatisticStrategy());
		strategies.add(new MaxStatisticStrategy());
		strategies.add(new MeanStatisticStrategy());
		underTest.setStatCalculationStrategies(strategies);
		
		Map<String, Double> myStats = new HashMap<>();
		myStats.put("Min", 2200.0);
		myStats.put("Max", 2200.0);
		myStats.put("Mean", 2200.0);
		
		//when
		Map<String, Double> statistics = underTest.getSalariesStatistics();
		
		//then
		assertEquals(myStats, statistics);
		assertTrue(myStats.equals(statistics));
	}
	
	@Test
	void canCheckIfEmployeeExists()
	{
		//given
		underTest.setEmployeeRepository(employeeRepository);
		Employee employee= new Employee("Jim","Sales", "123", 8, "jim@halpert.com"); 
		employeeRepository.save(employee);
		
		SalaryReport salaryReport = new SalaryReport("Jim",7,"May", 21, 2000, 200, employee);

		String correctMessage = "This Employee does NOT exist!";
		
		//when
		String actualMessage = underTest.checkIfEmployeeExists(salaryReport);
		
		//then
		assertEquals(correctMessage, actualMessage); 
	}
	
	@Test
	void canCheckForDoubleEntriesInOneMonth()
	{
		//given
		underTest.setEmployeeRepository(employeeRepository);
		Employee employee= new Employee("Jim","Sales", "123", 8, "jim@halpert.com"); 
		employeeRepository.save(employee);
		
		SalaryReport salaryReport1 = new SalaryReport("Jim",8,"May", 21, 2000, 200, employee);
		salaryRepository.save(salaryReport1);
		
		SalaryReport salaryReport2 = new SalaryReport("Jim",8,"May", 21, 2000, 200, employee);

		String correctMessage = "Salary exists for this employee and this month!";
		
		//when
		String actualMessage = underTest.checkForDoubleEntriesInOneMonth(salaryReport2);
		
		//then
		assertEquals(correctMessage, actualMessage); 
	}
	
	@Test
	void canCalculateStatisticsAboutSalariesEachMonth()
	{
		//given
		Employee employee= new Employee("Jim","Sales", "123", 7, "jim@halpert.com"); 
		employeeRepository.save(employee);
		
		SalaryReport salaryReport1 = new SalaryReport("Jim",8,"February", 21, 2000, 200, employee);
		salaryRepository.save(salaryReport1);
		
		SalaryReport salaryReport2 = new SalaryReport("Jim",8,"May", 21, 2000, 200, employee);
		salaryRepository.save(salaryReport2);

		Map<String, Integer> correctStats = new HashMap<>();
		correctStats.put("February", 2200);
		correctStats.put("May", 2200);
		correctStats.put("ZZ Total Salaries", 4400);
		
		//when
		Map<String, Integer> actualStats = underTest.statisticsAboutSalariesEachMonth();
		
		//then
		assertEquals(correctStats, actualStats);
		assertTrue(correctStats.equals(actualStats));
	}
}
