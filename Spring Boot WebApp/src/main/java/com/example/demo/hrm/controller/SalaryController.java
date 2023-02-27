package com.example.demo.hrm.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.hrm.entity.Employee;
import com.example.demo.hrm.entity.SalaryReport;
import com.example.demo.hrm.service.SalarayService;
import com.example.demo.hrm.service.statistics.MaxStatisticStrategy;
import com.example.demo.hrm.service.statistics.MeanStatisticStrategy;
import com.example.demo.hrm.service.statistics.MinStatisticStrategy;
import com.example.demo.hrm.service.statistics.StatisticStrategy;

@Controller 
public class SalaryController {
	
	private SalarayService salarayService;

	public SalaryController(SalarayService salarayService) {
		super();
		this.salarayService = salarayService;
	}

	@GetMapping("/salaries")
	public String listSalaries(Model theModel)
	{
		theModel.addAttribute("salaries", salarayService.getAllSalaries());
		return "salaries";
	}
	
	@GetMapping("/salaries/add")
	public String addNewSalarie(Model theModel)
	{
		SalaryReport salaryReport = new SalaryReport();
		theModel.addAttribute("salaryReport", salaryReport);
		return "create_salary";
	}  
	
	@PostMapping("/salaries")
	public String saveNewSalary(@ModelAttribute("salaryReport") SalaryReport salaryReport, BindingResult result)
	{
		 String err = salarayService.checkIfEmployeeExists(salaryReport);
		 String err1 = salarayService.checkForDoubleEntriesInOneMonth(salaryReport);
		 
		 if (!err.isEmpty()) {
		        ObjectError error = new ObjectError("globalError", err);
		        result.addError(error);
		    }
		 if (!err1.isEmpty()) {
		        ObjectError error1 = new ObjectError("globalError", err1);
		        result.addError(error1);
		    }
		 if (result.hasErrors()) {
		        return "create_salary";
		    }
		 
		salarayService.saveSalary(salaryReport);
		return "redirect:/salaries";
	}
	
	@GetMapping("/salaries/by/{emp_id}")
	public String groupSalariesByEmployee(@PathVariable("emp_id") int employee_id, Model theModel)
	{
		theModel.addAttribute("salaries", salarayService.getSalariesForEveryEmployee(employee_id));
		return "salaries_by_employee"; 
	}
	
	@GetMapping("/salaries/edit/{id}")
	public String editSalarie(@PathVariable Long id, Model theModel)
	{
		theModel.addAttribute("salaryReport", salarayService.getSalaryById(id));
		return "edit_salary";  
	}
	
	@PostMapping("/salaries/{id}")
	public String updateSalary(@PathVariable Long id, @ModelAttribute("salaryReport") SalaryReport salaryReport)
	{
		salarayService.updateSalary(salaryReport);
		return "redirect:/salaries";	
	}
	 
	@GetMapping("/salaries/{id}")
	public String deleteSalary(@PathVariable Long id)
	{
		salarayService.deleteSalaryById(id);
		return "redirect:/salaries";
	}
	
	@GetMapping("/salaries/all")
	public String deleteSalaries()
	{
		salarayService.deleteAllSalaries();
		return "redirect:/salaries";
	}
	
	@GetMapping("/salaries/{emp_name}/{emp_Id}/statistics")
	public String presentPersonalStatistics(@PathVariable String emp_name,@PathVariable int emp_Id, Model model) {
		
		Employee employee = salarayService.getEmployeeByNameAndId(emp_name,emp_Id);
		salarayService.setEmployee(employee);
		
		List<StatisticStrategy> strategies = salarayService.getStatCalculationStrategies();
		strategies.add(new MinStatisticStrategy());
		strategies.add(new MaxStatisticStrategy());
		strategies.add(new MeanStatisticStrategy());
		salarayService.setStatCalculationStrategies(strategies);
		
		model.addAttribute("statistics", salarayService.getSalariesStatistics());
		return "statistics";  
	}
	
	@GetMapping("/generalMonthlyStatistics")
	public String presentMonthlyStatistics(Model model) {
		model.addAttribute("generalStatistics", salarayService.statisticsAboutSalariesEachMonth());
		return "monthlySalary_statistics";  
	}
}