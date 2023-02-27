package com.example.demo.hrm.service.statistics;

import org.apache.commons.math3.stat.descriptive.rank.Max;

public class MaxStatisticStrategy extends TemplateStatisticStrategy {
	
	@Override
	public void doActualCalculation() {
		Max max = new Max();
		
		max.incrementAll(getAllSalaries());
		
		super.setStatisticNum(max.getResult());
	}
}
