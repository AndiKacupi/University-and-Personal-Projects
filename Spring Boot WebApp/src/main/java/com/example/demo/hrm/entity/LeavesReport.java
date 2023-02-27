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
@Table(name="leaveReports")
public class LeavesReport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name",nullable = false)
	private String name;
	
	@Column(name="emp_id", nullable = false)
	private int employee_id;
	
	@Column(name="theMonth", nullable = false)  // changed for h2 database during JUnit testing to "theMonth" because "month" is reserved word
	private String theMonth;
	
	@Column(name="fromDate", nullable = false)
	private int fromDate;
	
	@Column(name="toDate", nullable = false)
	private int toDate;
	
	@Column(name="reason", nullable = false)
	private String reason;
	
	@ManyToOne
	@JoinColumn(name="employee_id", nullable= false)
	private Employee employee;
	
	public LeavesReport() {
		
	}

	public LeavesReport(String name,int employee_id,String theMonth, int fromDate, int toDate, String reason, Employee employee) {
		super();
		this.name = name;
		this.employee_id=employee_id;
		this.theMonth=theMonth;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.reason = reason;
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

	public int getFromDate() {
		return fromDate;
	}

	public void setFromDate(int fromDate) {
		this.fromDate = fromDate;
	}

	public int getToDate() {
		return toDate;
	}

	public void setToDate(int toDate) {
		this.toDate = toDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee= employee;
	}

	public String getTheMonth() {
		return theMonth;
	}

	public void setTheMonth(String theMonth) {
		this.theMonth = theMonth;
	}
}
