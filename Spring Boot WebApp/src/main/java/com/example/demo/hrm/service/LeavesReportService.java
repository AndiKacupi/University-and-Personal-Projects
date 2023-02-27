package com.example.demo.hrm.service;

import java.util.List;
import java.util.Map;

import com.example.demo.hrm.entity.LeavesReport;

public interface LeavesReportService {
	
	List<LeavesReport> getAllReports();
	List<LeavesReport> getLeavesForEveryEmployee(int empId);
	LeavesReport saveLeavesReport(LeavesReport leavesReport);  
	LeavesReport getReportById(Long id);
	LeavesReport upadateReport(LeavesReport leavesReport);
	void deleteReportById(Long id);
	void deleteAllLeaves();
	public String checkIfEmployeeExists(LeavesReport leavesReport);
	
	//statistics
	Map<String, Integer> statisticsAboutLeaves();
}
