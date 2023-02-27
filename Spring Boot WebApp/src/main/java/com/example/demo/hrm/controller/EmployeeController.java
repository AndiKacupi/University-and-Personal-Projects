package com.example.demo.hrm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.hrm.entity.Employee;
import com.example.demo.hrm.service.EmployeeService;

@Controller 
public class EmployeeController {
	
	private EmployeeService employeeService;  
 
	public EmployeeController(EmployeeService employeeService) {
		super(); 
		this.employeeService = employeeService;
	}
	
	@GetMapping("/")
	public String startPage(){
		
		return "redirect:/home";
	}
	
	@GetMapping("/home")
	public String homePage()
	{
		return "homepage";
	}
	
	@GetMapping("/employees") 
	public String listEmployees(Model theModel)
	{
		theModel.addAttribute("employees", employeeService.getAllEmployees());
		return "employees"; 
	}
	
	@GetMapping("/employee/add")
	public String createEmployeeForm(Model theModel)
	{
		Employee employee = new Employee();
		theModel.addAttribute("employee", employee);
		return "create_employee";
	}
	
	@PostMapping("/employees") 
	public String saveNewEmployee(@ModelAttribute("employee") Employee employee,BindingResult result)
	{
		String err = employeeService.checkIfEmployeeIdExists(employee);
		
		 if (!err.isEmpty()) {
		        ObjectError error = new ObjectError("globalError", err);
		        result.addError(error);
		    }
		 if (result.hasErrors()) {
		        return "create_employee";
		    }
		 else {
		    	employeeService.saveEmployee(employee);
		    	return "redirect:/employees";  
		    }
	}
	
	@GetMapping("/employees/by/{dep}")
	public String groupByDepartment(@PathVariable String dep, Model theModel)
	{
		theModel.addAttribute("employees", employeeService.getEmployeesForEveryDepartment(dep));
		return "employees_by_department"; 
	}
	
	@GetMapping("/employee/edit/{id}")
	public String editEmployeeForm(@PathVariable Long id, Model theModel)
	{
		theModel.addAttribute("employee", employeeService.getEmployeeById(id));
		return "edit_employee";
	}
	
	@PostMapping("/employees/{id}")
	public String updateEmployee(@PathVariable Long id, @ModelAttribute("employee") Employee employee)
	{
		employeeService.updateEmployee(employee);
		return "redirect:/employees";
	}
	
	@GetMapping("/employees/{id}")
	public String deleteEmployee(@PathVariable Long id)
	{
		employeeService.deleteEmployeeById(id);
		return "redirect:/employees";
	}
	
	@GetMapping("/employees/all")
	public String deleteAllEmployees()
	{
		employeeService.deleteAllEmployees();
		return "redirect:/employees";
	}
	
	@GetMapping("/generalStatistics")
	public String presentStatistics(Model model) {
		model.addAttribute("generalStatistics", employeeService.statisticsAboutEmployees());
		return "general_statistics";  
	}
}