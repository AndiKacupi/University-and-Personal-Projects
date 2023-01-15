package timeaggregation;

import java.util.ArrayList;

import datamodel.IResult;
import datamodel.MeasurementRecord;
import datamodel.database;

public class Calculator implements IAggregator{

	private String time;
	
	@Override
	public IResult aggregateByTimeUnit(ArrayList<MeasurementRecord> inputMeasurements, String aggFunction, String description) {
		
		database db = new database();
		for(int i=0;i<inputMeasurements.size();i++){
			String s = inputMeasurements.get(i).toString();
			db.add(s, inputMeasurements.get(i));
			db.addPureData(inputMeasurements.get(i));
		}
		return db;
	}

	@Override
	public String getTimeUnitType() {
		return time;
	}

	public void setTime(String time){
		this.time = time;
	}
}
