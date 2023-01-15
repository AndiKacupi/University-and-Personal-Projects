package mainengine;

import java.util.ArrayList;
import java.util.HashMap;

import dataload.LoadFile;
import datamodel.IResult;
import datamodel.IResultFactory;
import datamodel.MeasurementRecord;
import datamodel.database;
import reporting.ReportProducer;
import timeaggregation.Calculator;

public class myclas implements IMainEngine {
	
	private ArrayList<MeasurementRecord> objCollection2 = new ArrayList<MeasurementRecord>();
	private database db = new database();
	private LoadFile<MeasurementRecord> r1 = new LoadFile<MeasurementRecord>();
	private Calculator cal = new Calculator();
	private IResultFactory f = new IResultFactory();
	private String aggFunction;
	private String description;
	private String timeperiod;
	private database result;	
	
	
	
	public myclas(){
		
	}
	
	public void setTime(String time){
		timeperiod = time;
	}

	@Override
	public int loadData(String fileName, String delimeter, Boolean hasHeaderLine, int numFields,
			ArrayList<MeasurementRecord> objCollection) {
		
		
		int lines = r1.load(fileName, delimeter, hasHeaderLine, numFields, objCollection);
    	
		int size=0;
		
		for(int i=0;i<objCollection.size();i++){
			
			size = db.addPureData(objCollection.get(i));
		}
		
		objCollection2 = objCollection;
		return lines;
	}


	
	
	@Override
	public IResult aggregateByTimeUnit(ArrayList<MeasurementRecord> inputMeasurements, String aggregatorType,
			String aggFunction, String description) {

		cal.setTime(timeperiod);
		
		result = (database) cal.aggregateByTimeUnit(objCollection2, aggFunction, description);
		
		result.setAggFunction(aggFunction);
		result.setPeriod(timeperiod);
		result.setAggDescription(description);
		
		HashMap<String, Double> res = new HashMap<String, Double>();
		
		res = result.getAggregateMeterKitchen();
		result.setKitchen(res);
		res = result.getAggregateMeterLaundry();
		result.setLaundry(res);
		res = result.getAggregateMeterAC();
		result.setAC(res);
		
		
		return result;
	}

	@Override
	public int reportResultInFile(IResult result, String reportType, String filename) {
		ReportProducer p1 = new ReportProducer();
		p1.setfileType(reportType);
		int r = p1.reportResultInFile(result, filename);
		return 0;
	}
}
