package com.example.demo.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.hrm.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
 
	@Query(value = "SELECT * FROM employees WHERE department = :dep",nativeQuery = true)
	public List<Employee> getEmployeesForEveryDepartment(String dep);
	
	@Query(value="SELECT CASE WHEN EXISTS (SELECT * FROM employees WHERE employee_id =:empId) THEN 'true' ELSE 'false' END",nativeQuery = true)
	public Boolean checkIfIdExists(int empId);
	
	@Query(value="SELECT CASE WHEN EXISTS (SELECT * FROM employees WHERE name=:name AND employee_id =:empId) THEN 'true' ELSE 'false' END",nativeQuery = true)
	public Boolean checkIfEmployeeExists(String name, int empId);
	
	@Query(value = "SELECT * FROM employees WHERE name=:name AND employee_id=:empId",nativeQuery = true)
	public Employee selectEmployeeByNameAndId(String name, int empId);
}
