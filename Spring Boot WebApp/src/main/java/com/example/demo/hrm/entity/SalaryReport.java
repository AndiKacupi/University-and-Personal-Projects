package com.example.demo.hrm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="salaryReports")
public class SalaryReport {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@Column(name="name",nullable = false)
		private String name;
		
		@Column(name="emp_id", nullable = false)
		private int employee_id;
		
		@Column(name="theMonth", nullable = false)  // changed for h2 database during JUnit testing to "theMonth" because "month" is reserved word
		private String theMonth;
		
		@Column(name="totalPaidDays", nullable = false)
		private int totalPaidDays;
		
		@Column(name="basicSalary", nullable = false)
		private int basicSalary;
		
		@Column(name="insuranceCost", nullable = false)
		private int insuranceCost;
		
		@ManyToOne
		@JoinColumn(name="employee_id", nullable= false)
		private Employee employee;
				
		public SalaryReport() {
			
		}

		public SalaryReport(String name,int employee_id, String theMonth, int totalPaidDays, int basicSalary, int insuranceCost, Employee employee) {
			super();
			this.name = name;
			this.theMonth = theMonth;
			this.employee_id=employee_id;
			this.totalPaidDays = totalPaidDays;
			this.basicSalary = basicSalary;
			this.insuranceCost = insuranceCost;
			this.employee = employee;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getEmployee_id() {
			return employee_id;
		}

		public void setEmployee_id(int employee_id) {
			this.employee_id = employee_id;
		}

		public String getMonth() {
			return theMonth;
		}

		public void setMonth(String month) {
			this.theMonth = month;
		}

		public int getTotalPaidDays() {
			return totalPaidDays;
		}

		public void setTotalPaidDays(int totalPaidDays) {
			this.totalPaidDays = totalPaidDays;
		}

		public int getBasicSalary() {
			return basicSalary;
		}

		public void setBasicSalary(int basicSalary) {
			this.basicSalary = basicSalary;
		}

		public int getInsuranceCost() {
			return insuranceCost;
		}

		public void setInsuranceCost(int insuranceCost) {
			this.insuranceCost = insuranceCost;
		}

		public Employee getEmployee() {
			return employee;
		}

		public void setEmployee(Employee employee) {
			this.employee = employee;
		}
}
