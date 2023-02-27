package com.example.demo.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.hrm.entity.SalaryReport;

public interface SalaryRepository extends JpaRepository<SalaryReport, Long> {
 
	@Query(value = "SELECT * FROM salary_reports WHERE emp_id = :empId",nativeQuery = true)
	public List<SalaryReport> getSalariesForEveryEmployee(int empId);
	
	@Query(value="SELECT CASE WHEN EXISTS (SELECT * FROM salary_reports WHERE name=:name AND the_month=:month AND emp_id =:empId) THEN 'true' ELSE 'false' END",nativeQuery = true)
	public Boolean checkIfSalaryExists(String name,String month, int empId);
}
