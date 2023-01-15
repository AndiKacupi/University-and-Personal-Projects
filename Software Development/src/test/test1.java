package test;

import java.util.ArrayList;
import java.util.HashMap;

import datamodel.IResult;
import datamodel.MeasurementRecord;
import mainengine.myclas;

public class test1 {
	
	
	//test1 t = new test1();
	//t.testLoadFault();
	//t.testLoadRight();
	//t.testLoadFault_emptyPath();
	//t.testLoadFault_emptyFields();
	//t.returnCorrectResult();
	//t.returnFaultResult();
	//t.checkSUM();
	//t.checkFaultSUM();
	//t.correctReport();
	//t.FaultReport();
	
	
	
	
	public void testLoadFault(){      
		myclas my = new myclas();		//fortwnw lathos arxeio!!!!
		int addedLines;
		String filename = "asdasd";
		String delimeter = "	";
		int numFields = 11;
		boolean hasHeaderLine = false;
		ArrayList<MeasurementRecord> objCollection = new ArrayList<MeasurementRecord>();
		
		addedLines = my.loadData(filename, delimeter, hasHeaderLine, numFields, objCollection);
	}
	
	public void testLoadRight(){      //fortwnetai swsta to arxeio
		myclas my = new myclas();		
		int addedLines;
		String filename = "2007_sample.tsv";
		String delimeter = "	";
		int numFields = 9;
		boolean hasHeaderLine = false;
		ArrayList<MeasurementRecord> objCollection = new ArrayList<MeasurementRecord>();
		
		addedLines = my.loadData(filename, delimeter, hasHeaderLine, numFields, objCollection);
	}
	
	public void testLoadFault_emptyPath(){      
		myclas my = new myclas();		//fortwnw empty path arxeio!!!!
		int addedLines;
		String filename = "";
		String delimeter = "	";
		int numFields = 11;
		boolean hasHeaderLine = false;
		ArrayList<MeasurementRecord> objCollection = new ArrayList<MeasurementRecord>();
		
		addedLines = my.loadData(filename, delimeter, hasHeaderLine, numFields, objCollection);
	}
	
	public void testLoadFault_emptyFields(){      
		myclas my = new myclas();		//fortwnw empty path arxeio!!!!
		int addedLines;
		String filename = "2007_sample.tsv";
		String delimeter = "	";
		int numFields = 0;
		boolean hasHeaderLine = false;
		ArrayList<MeasurementRecord> objCollection = new ArrayList<MeasurementRecord>();
		
		addedLines = my.loadData(filename, delimeter, hasHeaderLine, numFields, objCollection);
	}

	public void returnCorrectResult(){   //exei kanei save swsta
		myclas my = new myclas();		
		int addedLines;
		String filename = "2007_sample.tsv";
		String delimeter = "	";
		int numFields = 9;
		boolean hasHeaderLine = false;
		ArrayList<MeasurementRecord> objCollection = new ArrayList<MeasurementRecord>();
		
		addedLines = my.loadData(filename, delimeter, hasHeaderLine, numFields, objCollection);
		
		if(objCollection!=null){
			System.out.println("To objCollection exei pragmata mesa");
		}
		else{
			System.out.println("To objCollection DEN exei pragmata mesa");
		}
		
	}
	
	public void returnFaultResult(){    //den exei apothhkeusei kati
		myclas my = new myclas();		
		int addedLines;
		String filename = "FaultData.txt";
		String delimeter = " ";
		int numFields = 6;
		boolean hasHeaderLine = false;
		ArrayList<MeasurementRecord> objCollection = new ArrayList<MeasurementRecord>();
		
		addedLines = my.loadData(filename, delimeter, hasHeaderLine, numFields, objCollection);
		
		if(addedLines!=-1){
			System.out.println("To objCollection exei pragmata mesa");
		}
		else{
			System.out.println("To objCollection DEN exei pragmata mesa");
		}
		
	}
	
	
	public void checkSUM(){   ///swsta data
		myclas my = new myclas();		
		int addedLines;
		String filename = "2007_sample.tsv";
		String delimeter = "	";
		int numFields = 9;
		boolean hasHeaderLine = false;
		ArrayList<MeasurementRecord> objCollection = new ArrayList<MeasurementRecord>();
		
		addedLines = my.loadData(filename, delimeter, hasHeaderLine, numFields, objCollection);
		String aggregatorType = "mhna";
		String aggFunction = "SUM";
		String description = "ola kala!";
		my.setTime(aggregatorType);
		IResult r = null;
		r = my.aggregateByTimeUnit(objCollection, aggregatorType, aggFunction, description);
		if(r!=null){
			System.out.println("To Result exei pragmata mesa");
		}
		else{
			System.out.println("To Result den exei pragmata mesa");
		}
	}
	
	public void checkAVG(){           ///swsta data
		myclas my = new myclas();		
		int addedLines;
		String filename = "2007_sample.tsv";
		String delimeter = "	";
		int numFields = 9;
		boolean hasHeaderLine = false;
		ArrayList<MeasurementRecord> objCollection = new ArrayList<MeasurementRecord>();
		
		addedLines = my.loadData(filename, delimeter, hasHeaderLine, numFields, objCollection);
		String aggregatorType = "mhna";
		String aggFunction = "AVG";
		String description = "ola kala!";
		my.setTime(aggregatorType);
		IResult r = null;
		r = my.aggregateByTimeUnit(objCollection, aggregatorType, aggFunction, description);
		if(r!=null){
			System.out.println("To Result exei pragmata mesa");
		}
		else{
			System.out.println("To Result den exei pragmata mesa");
		}
	}
	
	public void checkFaultSUM(){           ///lathos data
		myclas my = new myclas();		
		int addedLines;
		String filename = "2007_sample.tsv";
		String delimeter = "	";
		int numFields = 9;
		boolean hasHeaderLine = false;
		ArrayList<MeasurementRecord> objCollection = new ArrayList<MeasurementRecord>();
		
		addedLines = my.loadData(filename, delimeter, hasHeaderLine, numFields, objCollection);
		String aggregatorType = "emvdomada";
		String aggFunction = "mesoOro";
		String description = "ola kala!";
		my.setTime(aggregatorType);
		IResult r = null;
		r = my.aggregateByTimeUnit(objCollection, aggregatorType, aggFunction, description);
		
		System.out.println("To Result exei " + r + " data");   //epistrefei null data!
		
	}
	
	
	public void correctReport(){   	//correct report
		myclas my = new myclas();		
		int addedLines;
		String filename = "2007_sample.tsv";
		String delimeter = "	";
		int numFields = 9;
		boolean hasHeaderLine = false;
		ArrayList<MeasurementRecord> objCollection = new ArrayList<MeasurementRecord>();
		
		addedLines = my.loadData(filename, delimeter, hasHeaderLine, numFields, objCollection);
		String aggregatorType = "mhna";
		String aggFunction = "SUM";
		String description = "ola kala!";
		my.setTime(aggregatorType);
		IResult r = null;
		r = my.aggregateByTimeUnit(objCollection, aggregatorType, aggFunction, description);
		String reportType = "html";
		String outFile = "out";
		int fileLines = my.reportResultInFile(r, reportType, outFile);
	}
	
	public void FaultReport(){     //faulty report
		myclas my = new myclas();		
		int addedLines;
		String filename = "2007_sample.tsv";
		String delimeter = "	";
		int numFields = 9;
		boolean hasHeaderLine = false;
		ArrayList<MeasurementRecord> objCollection = new ArrayList<MeasurementRecord>();
		
		addedLines = my.loadData(filename, delimeter, hasHeaderLine, numFields, objCollection);
		String aggregatorType = "mhna";
		String aggFunction = "SUM";
		String description = "ola kala!";
		my.setTime(aggregatorType);
		IResult r = null;
		r = my.aggregateByTimeUnit(objCollection, aggregatorType, aggFunction, description);
		String reportType = "tsv";
		String outFile = "out";
		int fileLines = my.reportResultInFile(r, reportType, outFile);
	}
}
