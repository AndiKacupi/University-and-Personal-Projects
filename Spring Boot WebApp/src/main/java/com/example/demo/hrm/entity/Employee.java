package com.example.demo.hrm.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="employees")  
public class Employee {
	
	@Id     
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	private Set<LeavesReport> leavesReports = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	private Set<SalaryReport> salaryReports = new HashSet<>();
	
	@Column(name="name", nullable=false)
	private String name; 
	
	@Column(name="department", nullable = false) 
	private String department;
	
	@Column(name="contact_Num", nullable = false)
	private String contactNum;
	
	@Column(name="employee_id",unique = true, nullable = false)
	private int employee_id;
	
	@Column(name="email", nullable = false)
	private String email;
	
	
	public Employee() {
	
	}

	public Employee(Set<LeavesReport> leavesReports, Set<SalaryReport> salaryReports, String name,String department, String contactNum, int employee_id, String email) {
		super();
		this.leavesReports = leavesReports;
		this.salaryReports = salaryReports;
		this.name = name;
		this.department = department;
		this.contactNum = contactNum;
		this.employee_id = employee_id;
		this.email = email;
	}
	
	// created for testing purposes
	public Employee(String name, String department, String contactNum, int employee_id, String email) {
		super();
		this.name = name;
		this.department = department;
		this.contactNum = contactNum;
		this.employee_id = employee_id;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<LeavesReport> getLeavesReports() {
		return leavesReports;
	}

	public void setLeavesReports(Set<LeavesReport> leavesReports) {
		this.leavesReports = leavesReports;
	}

	public Set<SalaryReport> getSalaryReports() {
		return salaryReports;
	}

	public void setSalaryReports(Set<SalaryReport> salaryReports) {
		this.salaryReports = salaryReports;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getContactNum() {
		return contactNum;
	}

	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void addSalaryToSet(SalaryReport salaryReport)
	{
		salaryReports.add(salaryReport);
	}
	
	public void addLeaveToSet(LeavesReport leavesReport)
	{
		leavesReports.add(leavesReport);
	}
}
