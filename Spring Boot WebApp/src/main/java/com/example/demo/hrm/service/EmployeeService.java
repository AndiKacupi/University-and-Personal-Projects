package com.example.demo.hrm.service;

import java.util.List;
import java.util.Map;

import com.example.demo.hrm.entity.Employee;

public interface EmployeeService {

	List<Employee> getAllEmployees();
	List<Employee> getEmployeesForEveryDepartment(String dep);
	Employee saveEmployee(Employee employee);
	Employee updateEmployee(Employee employee);
	Employee getEmployeeById(Long id); 
	void deleteEmployeeById(Long id);
	void deleteAllEmployees();
	public String checkIfEmployeeIdExists(Employee employee);
	
	//for the statistics
	Map<String, Long> statisticsAboutEmployees();
}
