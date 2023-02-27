package com.example.demo.hrm.service.statistics;

import org.apache.commons.math3.stat.descriptive.moment.Mean;

public class MeanStatisticStrategy extends TemplateStatisticStrategy {

	@Override
	public void doActualCalculation() {
		Mean mean = new Mean();
		
		mean.incrementAll(getAllSalaries());
		
		super.setStatisticNum(mean.getResult());
	}
}
