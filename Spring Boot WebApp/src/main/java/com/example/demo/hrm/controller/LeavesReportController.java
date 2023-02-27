package com.example.demo.hrm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.hrm.entity.LeavesReport;
import com.example.demo.hrm.service.LeavesReportService;

@Controller 
public class LeavesReportController {
	
	private LeavesReportService leavesReportService;
	
	public LeavesReportController(LeavesReportService leavesReportService) {
		super();
		this.leavesReportService = leavesReportService;
	}

	@GetMapping("/leaveReports") 
	public String listLeaveReprots(Model theModel)
	{
		theModel.addAttribute("leaveReports", leavesReportService.getAllReports());
		return "leave_reports";
	}
	
	@GetMapping("/leaveReport/add")
	public String createLeaveForm(Model theModel)
	{
		LeavesReport leavesReport = new LeavesReport();
		theModel.addAttribute("leavesReport", leavesReport);
		return "create_leaves_form";
	}
	
	@PostMapping("/leaveReports")
	public String saveReport(@ModelAttribute("leavesReport") LeavesReport leavesReport, BindingResult result)
	{
		 String err = leavesReportService.checkIfEmployeeExists(leavesReport);
		 
		 if (!err.isEmpty()) {
		        ObjectError error = new ObjectError("globalError", err);
		        result.addError(error);
		    }
		 if (result.hasErrors()) {
		        return "create_leaves_form";
		    }
		leavesReportService.saveLeavesReport(leavesReport);
		return "redirect:/leaveReports"; 
	} 
	
	@GetMapping("/leaveReports/by/{emp_id}")
	public String groupLeavesByEmployee(@PathVariable("emp_id") int employee_id, Model theModel)
	{
		theModel.addAttribute("leaveReports", leavesReportService.getLeavesForEveryEmployee(employee_id));
		return "leaves_by_employee"; 
	}
	
	@GetMapping("/leaveReport/edit/{id}")
	public String editLeaveReport(@PathVariable Long id, Model theModel)
	{
		theModel.addAttribute("leaveReport", leavesReportService.getReportById(id));
		return "edit_leave_report";
	}
	
	@PostMapping("/leaveReports/{id}")
	public String updateLeaveReports(@PathVariable Long id,@ModelAttribute("leaveReport") LeavesReport leavesReport)
	{
		leavesReportService.upadateReport(leavesReport);
		return "redirect:/leaveReports";
	}
	
	@GetMapping("/deleteReport/{id}")
	public String deleteLeavesReport(@PathVariable Long id)
	{
		leavesReportService.deleteReportById(id);
		return "redirect:/leaveReports"; 
	}
	
	@GetMapping("/deleteReport/all")
	public String deleteLeavesReport()
	{
		leavesReportService.deleteAllLeaves();
		return "redirect:/leaveReports"; 
	}
	
	@GetMapping("/generalLeavesStatistics")
	public String presentStatistics(Model model) {
		model.addAttribute("generalStatistics", leavesReportService.statisticsAboutLeaves());
		return "leaves_statistics";  
	}
}