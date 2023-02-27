package com.example.demo.hrm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.hrm.entity.Employee;
import com.example.demo.hrm.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public List<Employee> getAllEmployees() {		
		return employeeRepository.findAll();
	}

	@Override
	public Employee saveEmployee(Employee employee) {		
		return employeeRepository.save(employee);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		Employee existingEmployee=getEmployeeById(employee.getId());
		existingEmployee.setId(employee.getId());
		existingEmployee.setName(employee.getName());
		existingEmployee.setDepartment(employee.getDepartment());
		existingEmployee.setContactNum(employee.getContactNum());
		existingEmployee.setEmployee_id(employee.getEmployee_id());
		existingEmployee.setEmail(employee.getEmail());
		return employeeRepository.save(existingEmployee);
	}

	@Override
	public Employee getEmployeeById(Long id) {
		return employeeRepository.findById(id).get();
	}
		
	@Override
	public void deleteEmployeeById(Long id) {
		employeeRepository.deleteById(id);	
	}

	@Override
	public List<Employee> getEmployeesForEveryDepartment(String dep) {
		return employeeRepository.getEmployeesForEveryDepartment(dep);
	}

	@Override
	public void deleteAllEmployees() {
		employeeRepository.deleteAll();
	}
	
	@Override
	public String checkIfEmployeeIdExists(Employee employee)
	{
		String message="";
		boolean idExists=employeeRepository.checkIfIdExists(employee.getEmployee_id());
		if(idExists)
		{
			message="This Employee ID is already taken!";
		}
		return message;
	}
	
	@Override
	public Map<String,Long> statisticsAboutEmployees()
	{
		Map<String, Long> generalStatistics = new HashMap<String,Long>();
		List<Employee> employeeList = employeeRepository.findAll(); 
		
		for(Employee empl : employeeList)
		{	
		 if(!generalStatistics.containsKey(empl.getDepartment()))
		    {
			 generalStatistics.put(empl.getDepartment(),1L);
		    }
		    else
		    {
		    	generalStatistics.put(empl.getDepartment(),generalStatistics.get(empl.getDepartment())+1);
		    }
		}
		generalStatistics.put("_Total Number of Employees_", employeeRepository.count());
		return generalStatistics;
	}	
}
