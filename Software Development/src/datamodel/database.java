package datamodel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class database implements IResult{

	private HashMap<String, ArrayList<MeasurementRecord>> AllData = new HashMap<String, ArrayList<MeasurementRecord>>();
	private String Description;
	private String AggFunction;
	private String period;
	private ArrayList<MeasurementRecord> pureData = new ArrayList<MeasurementRecord>();
	private HashMap<String, Double> Kitchen = new HashMap<String, Double>();
	private HashMap<String, Double> Laundry = new HashMap<String, Double>();
	private HashMap<String, Double> AC = new HashMap<String, Double>();
	private int numOfField;
	
	
	public void setKitchen(HashMap<String, Double> temp){
		Kitchen = temp;
	}
	
	public void setLaundry(HashMap<String, Double> temp){
		Laundry = temp;
	}
	
	public void setAC(HashMap<String, Double> temp){
		AC = temp;
	}
	
	public void setPeriod(String per){
		period = per;
	}
	
	public void setAggFunction(String fun){
		AggFunction = fun;
	}
	
	public void setAggDescription(String des){
		Description = des;
	}
	
	public void setField(int field){
		numOfField = field;
	}
	@Override
	public int add(String timeUnit, MeasurementRecord record) {
		ArrayList<MeasurementRecord> newdata = new ArrayList<MeasurementRecord>();
		
		
		if(!AllData.containsKey(timeUnit)){
			newdata.add(record);
			AllData.put(timeUnit, newdata);	
		}
		else{
			newdata = AllData.get(timeUnit);
			newdata.add(record);
			AllData.put(timeUnit, newdata);
		}
		return AllData.size();
	}
	
	public int getSizePureData(){
		return pureData.size();
	}
	
	public int getSizeAllData(){
		return AllData.size();
	}
	
	public int addPureData( MeasurementRecord record) {
		pureData.add(record);
		
		return pureData.size();
	}
	
	public HashMap<String, ArrayList<MeasurementRecord>> getDatabase(){
		return AllData;
	}
	
	
	
	@Override
	public String getDescription() {		
		return Description;
		
	}
	
	@Override
	public HashMap<String, ArrayList<MeasurementRecord>> getDetailedResults() {
		return AllData;
	}
		
	
	
	public HashMap<String, Double> getKitchenData(){
		return Kitchen;
	}
	
	public HashMap<String, Double> getLaundryData(){
		return Laundry;
	}
	
	public HashMap<String, Double> getACData(){
		return AC;
	}
		
	
	

	@Override
	public String getAggregateFunction() {
		
		return AggFunction;
	}

	public HashMap<String, Double> computations(String type){
		HashMap<String, Double> ret = new HashMap<String, Double>();
		ArrayList<String> count = new ArrayList<String>();
		int pos = 0;
		String s = null;
		int whatData = 0;  //ti data xrhsimopoiw
		count.clear();
		
		if(type.equals("Kitchen")){
			whatData = 3;   
		}
		else if(type.equals("Laundry")){
			whatData = 2;   
		}
		else if(type.equals("AC")){
			whatData = 1;   
		}
		
			
		
		if(period.equals("mera")){
				for(int i=0;i<pureData.size();i++){
				
					String[] hmeromhnia = pureData.get(i).toString().split(" "); 
					String [] year = hmeromhnia[1].split("/");  //vriskw th xronia
				
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", java.util.Locale.ENGLISH);
					Date myDate = null;
					try {
						myDate = sdf.parse(hmeromhnia[1]);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					sdf.applyPattern("EEE");
					String sMyDate = sdf.format(myDate);
				
					
				
					String[] split= pureData.get(i).toString().split(" ");
					
					if(AggFunction.equals("SUM")){
						
						if(!ret.containsKey(sMyDate)){
							ret.put(sMyDate, Double.valueOf(split[split.length-whatData]));
						}
						else{
							ret.put(sMyDate, ret.get(sMyDate) + Double.valueOf(split[split.length-whatData]));
						}	
					}
					else if(AggFunction.equals("AVG")){
						
						if(!ret.containsKey(sMyDate)){
							ret.put(sMyDate, Double.valueOf(split[split.length-whatData]));
							count.add(sMyDate);
						}
						else{
							ret.put(sMyDate, ret.get(sMyDate) + Double.valueOf(split[split.length-whatData]));
							count.add(sMyDate);
						}							
					}				
			}
				
			if(AggFunction.equals("AVG")){
				int n;
				for (Map.Entry<String, Double> entry : ret.entrySet()) {
					String k = entry.getKey();
					String finalCount = count.toString();
					n = finalCount.split(k).length-1;
					ret.put(k, entry.getValue()/n);
				}
				
			}
		}
		
		else if(period.equals("xrono") ){
				for(int i=0;i<pureData.size();i++){
				
					String[] split= pureData.get(i).toString().split(" ");  // h wra oloklhrh
					String[] H = split[2].split(":"); //h wra
					String hour = H[0].trim();
					
					
					
					String day="NIGHT";
					
					if(Integer.parseInt(hour)>=21  && Integer.parseInt(hour)< 05){
						day = "NIGHT";
					}
					else if(Integer.parseInt(hour)>=5  && Integer.parseInt(hour)< 9){
						day = "EARLY_MORNING";
					}
					else if(Integer.parseInt(hour)>=9  && Integer.parseInt(hour)< 13){
						day = "MORNING";
					}
					else if(Integer.parseInt(hour)>=13  && Integer.parseInt(hour)< 17){
						day = "AFTERNOON";
					}
					else if(Integer.parseInt(hour)>=17  && Integer.parseInt(hour)< 21){
						day = "EVENING";
					}
					
					if(AggFunction.equals("SUM")){
						if(!ret.containsKey(day)){
							ret.put(day, Double.valueOf(split[split.length-whatData]));
						}
						else{
							ret.put(day, ret.get(day) + Double.valueOf(split[split.length-whatData]));
						}
					}
					else if(AggFunction.equals("AVG")){
						
						if(!ret.containsKey(day)){
							ret.put(day, Double.valueOf(split[split.length-whatData]));
							count.add(day);
						}
						else{
							ret.put(day, ret.get(day) + Double.valueOf(split[split.length-whatData]));
							count.add(day);
						}							
					}		
					
					
				
			}
			if(AggFunction.equals("AVG")){
				int n;
				for (Map.Entry<String, Double> entry : ret.entrySet()) {
					String k = entry.getKey();
					String finalCount = count.toString();
					n = finalCount.split(k).length-1;
					ret.put(k, entry.getValue()/n);
				}
					
			}
		}
		
		else if(period.equals("mhna") ){
			for(int i=0;i<pureData.size();i++){
			
				String[] split= pureData.get(i).toString().split("/"); //gia ton mhna
				String[] split2= pureData.get(i).toString().split(" "); //gia tis metrhseis
				
				String mhna="JAN";
				
				if(Integer.parseInt(split[1])==1){
					mhna = "JAN";
				}
				else if(Integer.parseInt(split[1])==2){
					mhna = "FEB";
				}
				else if(Integer.parseInt(split[1])==3){
					mhna = "MAR";
				}
				else if(Integer.parseInt(split[1])==4){
					mhna = "APR";
				}
				else if(Integer.parseInt(split[1])==5){
					mhna = "MAY";
				}
				else if(Integer.parseInt(split[1])==6){
					mhna = "JUN";
				}
				else if(Integer.parseInt(split[1])==7){
					mhna = "JUL";
				}
				else if(Integer.parseInt(split[1])==8){
					mhna = "AUG";
				}
				else if(Integer.parseInt(split[1])==9){
					mhna = "SEP";
				}
				else if(Integer.parseInt(split[1])==10){
					mhna = "OCT";
				}
				else if(Integer.parseInt(split[1])==11){
					mhna = "NOV";
				}
				else if(Integer.parseInt(split[1])==12){
					mhna = "DEC";
				}
				
				if(AggFunction.equals("SUM")){
					if(!ret.containsKey(mhna)){
						ret.put(mhna, Double.valueOf(split2[split2.length-whatData]));
					}
					else{
						ret.put(mhna, ret.get(mhna) + Double.valueOf(split2[split2.length-whatData]));
					}
				}
				else if(AggFunction.equals("AVG")){
					
					if(!ret.containsKey(mhna)){
						ret.put(mhna, Double.valueOf(split2[split2.length-whatData]));
						count.add(mhna);
					}
					else{
						ret.put(mhna, ret.get(mhna) + Double.valueOf(split2[split2.length-whatData]));
						count.add(mhna);
					}							
				}	
			
			}
			if(AggFunction.equals("AVG")){
				int n;
				for (Map.Entry<String, Double> entry : ret.entrySet()) {
					String k = entry.getKey();
					String finalCount = count.toString();
					n = finalCount.split(k).length-1;
					ret.put(k, entry.getValue()/n);
				}
					
			}
		}
	
		
		else if(period.equals("epoxh")){ 
			for(int i=0;i<pureData.size();i++){
				
					s = pureData.get(i).toString();
					
					StringTokenizer tokenizer = new StringTokenizer(s, "/");
					String[] tokens =  new String[3];
					
					for (int k=0; k< 3;k++){
						tokens[k] = tokenizer.nextToken();
					}
					
					
					if( (Integer.valueOf(tokens[1]) == 12 || Integer.valueOf(tokens[1]) == 1 || Integer.valueOf(tokens[1]) == 2) ){
						
						String[] split= pureData.get(i).toString().split(" ");
						
						if(AggFunction.equals("SUM")){
							if(!ret.containsKey("WINTER")){
								ret.put("WINTER", Double.valueOf(split[split.length-whatData]));
							}
							else{
								ret.put("WINTER", ret.get("WINTER") + Double.valueOf(split[split.length-whatData]));
							}
						}
						else if(AggFunction.equals("AVG")){
							
							if(!ret.containsKey("WINTER")){
								ret.put("WINTER", Double.valueOf(split[split.length-whatData]));
								count.add("WINTER");
							}
							else{
								ret.put("WINTER", ret.get("WINTER") + Double.valueOf(split[split.length-whatData]));
								count.add("WINTER");
							}							
						}	
					}
					else if((Integer.valueOf(tokens[1]) == 3 || Integer.valueOf(tokens[1]) == 4 || Integer.valueOf(tokens[1]) == 5) ){
						
						String[] split= pureData.get(i).toString().split(" ");
						
						if(AggFunction.equals("SUM")){
							if(!ret.containsKey("SPRING")){
								ret.put("SPRING", Double.valueOf(split[split.length-whatData]));
							}
							else{
								ret.put("SPRING", ret.get("SPRING") + Double.valueOf(split[split.length-whatData]));
							}
						}
						else if(AggFunction.equals("AVG")){
							
							if(!ret.containsKey("SPRING")){
								ret.put("SPRING", Double.valueOf(split[split.length-whatData]));
								count.add("SPRING");
							}
							else{
								ret.put("SPRING", ret.get("SPRING") + Double.valueOf(split[split.length-whatData]));
								count.add("SPRING");
							}							
						}	
					}
					else if( (Integer.valueOf(tokens[1]) == 6 || Integer.valueOf(tokens[1]) == 7 || Integer.valueOf(tokens[1]) == 8) ){
						
						String[] split= pureData.get(i).toString().split(" ");
						
						if(AggFunction.equals("SUM")){
							if(!ret.containsKey("SUMMER")){
								ret.put("SUMMER", Double.valueOf(split[split.length-whatData]));
							}
							else{
								ret.put("SUMMER", ret.get("SUMMER") + Double.valueOf(split[split.length-3]));
							}
						}
						else if(AggFunction.equals("AVG")){
							
							if(!ret.containsKey("SUMMER")){
								ret.put("SUMMER", Double.valueOf(split[split.length-whatData]));
								count.add("SUMMER");
							}
							else{
								ret.put("SUMMER", ret.get("SUMMER") + Double.valueOf(split[split.length-whatData]));
								count.add("SUMMER");
							}							
						}	
					}
					else if((Integer.valueOf(tokens[1]) == 9 || Integer.valueOf(tokens[1]) == 10 || Integer.valueOf(tokens[1]) == 11) ){
						
						String[] split= pureData.get(i).toString().split(" ");
						
						if(AggFunction.equals("SUM")){
							if(!ret.containsKey("AUTUMN")){
								ret.put("AUTUMN", Double.valueOf(split[split.length-whatData]));
							}
							else{
								ret.put("AUTUMN", ret.get("AUTUMN") + Double.valueOf(split[split.length-whatData]));
							}
						}
						else if(AggFunction.equals("AVG")){
							
							if(!ret.containsKey("AUTUMN")){
								ret.put("AUTUMN", Double.valueOf(split[split.length-whatData]));
								count.add("AUTUMN");
							}
							else{
								ret.put("AUTUMN", ret.get("AUTUMN") + Double.valueOf(split[split.length-whatData]));
								count.add("AUTUMN");
							}							
						}	
					}
					
			//}
		}
			if(AggFunction.equals("AVG")){
				int n;
				for (Map.Entry<String, Double> entry : ret.entrySet()) {
					String k = entry.getKey();
					String finalCount = count.toString();
					n = finalCount.split(k).length-1;
					ret.put(k, entry.getValue()/n);
				}
					
			}
		}
		if(type.equals("Kitchen")){
			Kitchen = ret;    //data stoys swstous pinakes
		}
		else if(type.equals("Laundry")){
			Laundry = ret;
		}
		else if(type.equals("AC")){
			AC = ret;
		}
		
		return ret;
	}


	@Override
	public HashMap<String, Double> getAggregateMeterAC() {
		HashMap<String, Double> h1 = new HashMap<String, Double>();
		h1 = computations("AC");
		return(h1);
	}
	@Override	
	public HashMap<String, Double> getAggregateMeterKitchen() {
		HashMap<String, Double> h1 = new HashMap<String, Double>();
		h1 = computations("Kitchen");
		return(h1);
	}
	
	@Override
	public HashMap<String, Double> getAggregateMeterLaundry() {
		HashMap<String, Double> h1 = new HashMap<String, Double>();
		h1 = computations("Laundry");
		return(h1);
	}
}