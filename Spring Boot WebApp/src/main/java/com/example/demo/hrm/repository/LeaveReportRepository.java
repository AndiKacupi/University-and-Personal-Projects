package com.example.demo.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.hrm.entity.LeavesReport;

public interface LeaveReportRepository extends JpaRepository<LeavesReport, Long>{
 
	@Query(value = "SELECT * FROM leave_reports WHERE emp_id=:empId",nativeQuery = true)
	public List<LeavesReport> getLeavesForEveryEmployee(int empId);
}
