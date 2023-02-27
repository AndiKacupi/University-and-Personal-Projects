package com.example.demo.hrm.service.statistics;

import com.example.demo.hrm.entity.Employee;
import com.example.demo.hrm.entity.SalaryReport;

public abstract class TemplateStatisticStrategy implements StatisticStrategy{
	
	private Employee employee;
	private double[] allSalaries;
	private double statisticNum;
	
	public TemplateStatisticStrategy() {
		
	}

	@Override
	public double calculateStatistic(Employee employee) {
		this.employee=employee;
		
		prepareDataSet();
		doActualCalculation();
		return statisticNum;
	}
	
	private void prepareDataSet() 
	{
		allSalaries = new double[employee.getSalaryReports().size()];
		int i = 0;
		for (SalaryReport salary : employee.getSalaryReports()) {
			allSalaries[i] = salary.getBasicSalary() + salary.getInsuranceCost();  
			i++;
		}		
	}
	
	public abstract void doActualCalculation();

	public double[] getAllSalaries() {
		return allSalaries;
	}

	public void setAllSalaries(double[] allSalaries) {
		this.allSalaries = allSalaries;
	}

	public double getStatisticNum() {
		return statisticNum;
	}

	public void setStatisticNum(double statisticNum) {
		this.statisticNum = statisticNum;
	}
}
