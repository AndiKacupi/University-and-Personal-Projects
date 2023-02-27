package com.example.demo.hrm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.hrm.entity.Employee;
import com.example.demo.hrm.entity.LeavesReport;
import com.example.demo.hrm.repository.EmployeeRepository;
import com.example.demo.hrm.repository.LeaveReportRepository;

@Service
public class LeavesReportServiceImpl implements LeavesReportService {
	
	private LeaveReportRepository leaveReportRepository; 
	
	@Autowired
	private EmployeeRepository employeeRepository;

	public LeavesReportServiceImpl(LeaveReportRepository leaveReportRepository) {
		super();
		this.leaveReportRepository = leaveReportRepository;
	}
	
	@Override
	public List<LeavesReport> getAllReports() {
		return leaveReportRepository.findAll();
	}

	@Override
	public LeavesReport saveLeavesReport(LeavesReport leavesReport) {
		boolean exists = employeeRepository.checkIfEmployeeExists(leavesReport.getName(),leavesReport.getEmployee_id());
		
		if(!exists) {
			System.out.println("Error");
			return null;
		}		
		Employee helperEmployee = employeeRepository.selectEmployeeByNameAndId(leavesReport.getName(),leavesReport.getEmployee_id());
		
		leavesReport.setEmployee(helperEmployee);
		helperEmployee.addLeaveToSet(leavesReport);
		return leaveReportRepository.save(leavesReport);
	}

	@Override
	public LeavesReport upadateReport(LeavesReport leavesReport) {
		LeavesReport existingLeavesReport = getReportById(leavesReport.getId());
		
		existingLeavesReport.setId(leavesReport.getId());
		existingLeavesReport.setName(leavesReport.getName());
		existingLeavesReport.setEmployee_id(leavesReport.getEmployee_id());
		existingLeavesReport.setTheMonth(leavesReport.getTheMonth());
		existingLeavesReport.setFromDate(leavesReport.getFromDate());
		existingLeavesReport.setToDate(leavesReport.getToDate());
		existingLeavesReport.setReason(leavesReport.getReason());
		
		return leaveReportRepository.save(existingLeavesReport);
	}

	@Override
	public LeavesReport getReportById(Long id) {
		return leaveReportRepository.findById(id).get();
	}

	@Override
	public void deleteReportById(Long id) {
		leaveReportRepository.deleteById(id);	
	}

	@Override
	public List<LeavesReport> getLeavesForEveryEmployee(int empId) {
		return leaveReportRepository.getLeavesForEveryEmployee(empId);
	}
	
	public void setEmployeeRepository(EmployeeRepository employeeRepository)
	{
		this.employeeRepository=employeeRepository;
	}

	@Override
	public void deleteAllLeaves() {
		leaveReportRepository.deleteAll();
	}
	
	@Override
	public String checkIfEmployeeExists(LeavesReport leavesReport)
	{
		boolean exists = employeeRepository.checkIfEmployeeExists(leavesReport.getName(),leavesReport.getEmployee_id()); 
		String message="";
		
		if(!exists) {
			message="This Employee does NOT exist!";
		}
		return message;
	}
	
	@Override
	public Map<String,Integer> statisticsAboutLeaves()
	{
		Map<String, Integer> generalSalaryStatistics = new HashMap<String,Integer>();
		List<LeavesReport> leavesList = leaveReportRepository.findAll(); 
		int totalAmount=0;
		for(LeavesReport leave : leavesList)
		{	
		 totalAmount+=leave.getToDate()-leave.getFromDate();
		 if(!generalSalaryStatistics.containsKey(leave.getTheMonth()))
		    {
			 generalSalaryStatistics.put(leave.getTheMonth(),(leave.getToDate()-leave.getFromDate()));
		    }
		    else
		    {
		    	generalSalaryStatistics.put(leave.getTheMonth(),generalSalaryStatistics.get(leave.getTheMonth())+(leave.getToDate()-leave.getFromDate()));
		    }
		}
		generalSalaryStatistics.put("_Total Leaves_", totalAmount);
		return generalSalaryStatistics;
	}	
}
