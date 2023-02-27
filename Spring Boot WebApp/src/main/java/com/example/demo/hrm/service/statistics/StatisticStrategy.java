package com.example.demo.hrm.service.statistics;

import com.example.demo.hrm.entity.Employee;

public interface StatisticStrategy {

	double calculateStatistic(Employee employee);
	
}
