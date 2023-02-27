package com.example.demo.hrm.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.hrm.entity.Employee;
import com.example.demo.hrm.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTestMock {

	
	@Mock private EmployeeRepository employeeRepository;
	private EmployeeServiceImpl underTest; 
	
	@BeforeEach
	void setup()
	{
		underTest=new EmployeeServiceImpl(employeeRepository);
	}
	
	@Test
	void canGetAllEmployees()
	{
		//when
		underTest.getAllEmployees();
		
		//then
		verify(employeeRepository).findAll();
	}
	
	@Test
	void canSaveEmployee()
	{
		//given
		Employee employee= new Employee("Jim","Sales", "123", 7, "jim@halpert.com"); 
		
		//when
		underTest.saveEmployee(employee);

		//then
		ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);
		
		verify(employeeRepository).save(employeeArgumentCaptor.capture());
		
		Employee capturedEmployee = employeeArgumentCaptor.getValue();
		
		assertThat(capturedEmployee).isEqualTo(employee);		
	}
}
