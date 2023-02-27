package com.example.demo.hrm.service;

import java.util.List;
import java.util.Map;

import com.example.demo.hrm.entity.Employee;
import com.example.demo.hrm.entity.SalaryReport;
import com.example.demo.hrm.service.statistics.StatisticStrategy;

public interface SalarayService {
	
	List<SalaryReport> getAllSalaries();
	List<SalaryReport> getSalariesForEveryEmployee(int empId);
	SalaryReport saveSalary(SalaryReport salaryReport);
	SalaryReport updateSalary(SalaryReport salaryReport);
	SalaryReport getSalaryById(Long id);
	void deleteSalaryById(Long id);
	void deleteAllSalaries();
	String checkIfEmployeeExists(SalaryReport salaryReport);
	String checkForDoubleEntriesInOneMonth(SalaryReport salaryReport);
	
	void setEmployee(Employee employee);
	Employee getEmployeeByNameAndId(String name, int id);

	//salary statistics
	List<StatisticStrategy> getStatCalculationStrategies();
	void setStatCalculationStrategies(List<StatisticStrategy> statCalculationStrategies);
	Map<String, Double> getSalariesStatistics();
	Map<String, Integer> statisticsAboutSalariesEachMonth();
}

