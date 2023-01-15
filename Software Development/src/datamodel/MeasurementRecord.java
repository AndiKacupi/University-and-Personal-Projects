package datamodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import client.MainApp;
import dataload.LoadFile;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class MeasurementRecord implements IResult{
	
	private String Description;
	private String timeperiod;
	private String AggFunction;
	private ArrayList<String[]> data = new ArrayList<String[]>();
	private String[] line;
	
	public String getToken(int i){
		return (line[i]);
	}
	
	public MeasurementRecord(){
		line = null;
	}
	
	public void setMeasurementRecord(String[] newdata){
		line = newdata;
	}
	
	@Override
	public int add(String timeUnit, MeasurementRecord record) {		
		return 0;
	}

	
	
	@Override
	public String getDescription() {
		
		
		return Description;
		
		
	}

	public String getTimeUnit(){
		return line[0] + line[1];
	}
	
	public void settimeperiodt(String time){		
		timeperiod = time;
	}
	
	public void setDescription(String des){		
		Description = des;
	}
	
	public void setAggFunction(String agg){		
		AggFunction = agg;
	}
	
	@Override
	public HashMap<String, ArrayList<MeasurementRecord>> getDetailedResults() {
		return null;
	}
		
		
	
	public String toString(){
		String ret = "";
		for(int i=0;i<line.length;i++){
			ret = ret +" " + line[i];
		}
		return (ret);
	}

	
	
	@Override
	public HashMap<String, Double> getAggregateMeterKitchen() {
		
		return null;
	}
		
	@Override
	public HashMap<String, Double> getAggregateMeterLaundry() {
		return null;
	
	}

	@Override
	public String getAggregateFunction() {
		

		return AggFunction;
	}



	@Override
	public HashMap<String, Double> getAggregateMeterAC() {
		// TODO Auto-generated method stub
		return null;
	}

}