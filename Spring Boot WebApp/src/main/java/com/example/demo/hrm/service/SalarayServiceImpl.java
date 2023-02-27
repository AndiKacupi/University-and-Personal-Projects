package com.example.demo.hrm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.hrm.entity.Employee;
import com.example.demo.hrm.entity.SalaryReport;
import com.example.demo.hrm.repository.EmployeeRepository;
import com.example.demo.hrm.repository.SalaryRepository;
import com.example.demo.hrm.service.statistics.MaxStatisticStrategy;
import com.example.demo.hrm.service.statistics.MeanStatisticStrategy;
import com.example.demo.hrm.service.statistics.MinStatisticStrategy;
import com.example.demo.hrm.service.statistics.StatisticStrategy;

@Service
public class SalarayServiceImpl implements SalarayService {
	
	private SalaryRepository salaryRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	private Employee employee;//23
	private List<StatisticStrategy> statCalculationStrategies = new ArrayList<StatisticStrategy>();//23
	
	public SalarayServiceImpl(SalaryRepository salaryRepository) {
		super();
		this.salaryRepository = salaryRepository;
	}

	@Override
	public List<SalaryReport> getAllSalaries() {
		return salaryRepository.findAll();
	}

	@Override
	public SalaryReport saveSalary(SalaryReport salaryReport) {
		
		boolean exists = employeeRepository.checkIfEmployeeExists(salaryReport.getName(),salaryReport.getEmployee_id());   
		
		if(!exists) {
			System.out.println("Error");
			return null;
		}		
		
		Employee helperEmployee = employeeRepository.selectEmployeeByNameAndId(salaryReport.getName(),salaryReport.getEmployee_id());
		
		salaryReport.setEmployee(helperEmployee);
		helperEmployee.addSalaryToSet(salaryReport);
		return salaryRepository.save(salaryReport);
	}

	@Override
	public SalaryReport updateSalary(SalaryReport salaryReport) {
		SalaryReport existinSalaryReport = getSalaryById(salaryReport.getId());
		existinSalaryReport.setId(salaryReport.getId());
		existinSalaryReport.setName(salaryReport.getName());
		existinSalaryReport.setEmployee_id(salaryReport.getEmployee_id());
		existinSalaryReport.setMonth(salaryReport.getMonth());
		existinSalaryReport.setTotalPaidDays(salaryReport.getTotalPaidDays());
		existinSalaryReport.setBasicSalary(salaryReport.getBasicSalary());
		existinSalaryReport.setInsuranceCost(salaryReport.getInsuranceCost());
		return salaryRepository.save(existinSalaryReport);
	}

	@Override
	public SalaryReport getSalaryById(Long id) {		
		return salaryRepository.findById(id).get();
	}

	@Override
	public void deleteSalaryById(Long id) {
		salaryRepository.deleteById(id);		
	}

	@Override
	public List<SalaryReport> getSalariesForEveryEmployee(int empId) {
		return salaryRepository.getSalariesForEveryEmployee(empId);
	}	
	
	public void setEmployeeRepository(EmployeeRepository employeeRepository)
	{
		this.employeeRepository=employeeRepository;
	}

	@Override
	public void deleteAllSalaries() {
		salaryRepository.deleteAll();
	}
		
	@Override
	public String checkIfEmployeeExists(SalaryReport salaryReport)
	{
		boolean exists = employeeRepository.checkIfEmployeeExists(salaryReport.getName(),salaryReport.getEmployee_id()); 
		String message="";
		
		if(!exists) {
			message="This Employee does NOT exist!";
		}
		return message;
	}
	
	@Override
	public String checkForDoubleEntriesInOneMonth(SalaryReport salaryReport)
	{
		String message="";
		boolean salaryExists=salaryRepository.checkIfSalaryExists(salaryReport.getName(),salaryReport.getMonth(),salaryReport.getEmployee_id());
		if(salaryExists)
		{
			message="Salary exists for this employee and this month!";
		}
		return message;
	}
	
	@Override
	public Map<String, Double> getSalariesStatistics() {
		 Map<String, Double> statistics = new HashMap<String,Double>();
		 
		 for(StatisticStrategy statistic: statCalculationStrategies)
		 {
			 if(statistic instanceof MaxStatisticStrategy)
			 {
				 statistics.put("Max", statistic.calculateStatistic(employee));
			 }
			 else if(statistic instanceof MinStatisticStrategy)
			 {
				 statistics.put("Min", statistic.calculateStatistic(employee));
			 }
			 else if(statistic instanceof MeanStatisticStrategy)
			 {
				 statistics.put("Mean", statistic.calculateStatistic(employee));
			 }
		 }		 
		 return statistics;
	}
		
	@Override
	public List<StatisticStrategy> getStatCalculationStrategies() {
		return statCalculationStrategies;
	}
		
	@Override
	public void setStatCalculationStrategies(List<StatisticStrategy> statCalculationStrategies) {
		this.statCalculationStrategies = statCalculationStrategies;
	}
	
	@Override 
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	@Override
	public Employee getEmployeeByNameAndId(String name,int id) {
		return employeeRepository.selectEmployeeByNameAndId(name,id);
	}
	
	@Override
	public Map<String,Integer> statisticsAboutSalariesEachMonth()
	{
		Map<String, Integer> generalSalaryStatistics = new HashMap<String,Integer>();
		List<SalaryReport> salaryList = salaryRepository.findAll(); 
		int totalAmount=0;
		for(SalaryReport sal : salaryList)
		{	
		 totalAmount+=sal.getBasicSalary()+sal.getInsuranceCost();
		 if(!generalSalaryStatistics.containsKey(sal.getMonth()))
		    {
			 generalSalaryStatistics.put(sal.getMonth(),(sal.getBasicSalary()+sal.getInsuranceCost()));
		    }
		    else
		    {
		    	generalSalaryStatistics.put(sal.getMonth(),generalSalaryStatistics.get(sal.getMonth())+(sal.getBasicSalary()+sal.getInsuranceCost()));
		    }
		}
		generalSalaryStatistics.put("_Total Salaries_", totalAmount);
		return generalSalaryStatistics;
	}	
}