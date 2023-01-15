package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JComponent;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

import datamodel.IResult;
import datamodel.MeasurementRecord;
import mainengine.IMainEngine;
import mainengine.MainEngineFactory;
import mainengine.myclas;
import reporting.ReportProducer;
import test.test1;
import timeaggregation.Calculator;

public class MainApp {

	test1 t = new test1();
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

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    
    
    
    
    public static void main(String[] args) {
    	
    	
    	Scanner input = new Scanner(System.in);
    	myclas my = new myclas();
    	ArrayList<MeasurementRecord> objCollection = new ArrayList<MeasurementRecord>();
    	IResult r = null;
    	String choice="0";
    	int addedLines = 0;
  
    	
    	
    	
   
    	
    	
    	while(Integer.parseInt(choice)<4){
    		
	    	System.out.println("Epelexe kati apo to 1-4");
        	System.out.println("1. Fortwsh arxeiou");
        	System.out.println("2. Kataskeuh sunathroisthkhs sunarthshs");
        	System.out.println("3. Paragwgh anaforas");
        	System.out.println("4. EXIT");
	    	choice = input.next();
	    	
	    
	    	while(!isNumeric(choice) || Integer.parseInt(choice) > 4 || Integer.parseInt(choice) <= 0){
	    		System.out.println("Epelexe kati apo to 1-4");
	        	System.out.println("1. Fortwsh arxeiou");
	        	System.out.println("2. Kataskeuh sunathroisthkhs sunarthshs");
	        	System.out.println("3. Paragwgh anaforas");
	        	System.out.println("4. EXIT");
	        	choice = input.next();
	    	}
	    	
	    	if(Integer.parseInt(choice) == 1){
	    		System.out.println("Dwse to onoma tou arxeiou pou theleis na fortwseis");
	    		String filename = input.next();
	    		System.out.println("Dwse ton tropo pou einai xwrismena ta fields tou arxeiou  [tab|:|;|space|#|$|,|.]");
	    		String del = input.next();
	    		String delimeter = "";
	    		
	    		int flag=0;
	    		while(flag==0){
		    		if(del.equals("tab") || del.equals("TAB") || del.equals("Tab")){
		    			delimeter = "	";
		    			flag=1;
		    		}
		    		else if(del.equals(":")){
		    			delimeter = ":";
		    			flag=1;
		    		}
		    		else if(del.equals(";")){
		    			delimeter = ";";
		    			flag=1;
		    		}
		    		else if(del.equals("space") || del.equals("SPACE") || del.equals("Space")){
		    			delimeter = " ";
		    			flag=1;
		    		}
		    		else if(del.equals("#")){
		    			delimeter = "#";
		    			flag=1;
		    		}
		    		else if(del.equals("$")){
		    			delimeter = "%";
		    			flag=1;
		    		}
		    		else if(del.equals(",")){
		    			delimeter = ",";
		    			flag=1;
		    		}
		    		else if(del.equals(".")){
		    			delimeter = ".";
		    			flag=1;
		    		}
		    		else{
		    			System.out.println("Dose ena apo ta parakatw delimeter [tab|:|;|space|#|$|,|.]");
		    			del = input.next();
		    			flag=0;
		    		}
	    		}
	    		System.out.println("Dwse to plhthos twn fields tou arxeiou");
	    		String numOfFields = input.next();
	    		while(!isNumeric(choice)){
	    			System.out.println("Dwse to plhthos twn fields tou arxeiou");
	        	    numOfFields = input.next();
	    		}
	    		int numFields = Integer.parseInt(numOfFields);
	    		System.out.println("Exei epikefalida to arxeio? (Y/N)");
	    		String Header = input.next();
	    		while(!Header.equals("N") && !Header.equals("Y")){
	    			System.out.println("Exei epikefalida to arxeio??? (Y/N)");
	        		Header = input.next();
	    		}
	    		Boolean hasHeaderLine;
	    		if(Header.equals("N"))
	    			hasHeaderLine = false;
	    		else
	    			hasHeaderLine = true;
	    		addedLines = my.loadData(filename, delimeter, hasHeaderLine, numFields, objCollection);
	    		System.out.println("Ta dedomena apotelountai apo " + addedLines + " grammes");
	    	}
	    	
	    	else if(Integer.parseInt(choice) == 2){
	    		if(addedLines==0){
	    			System.out.println("Den exeis fortwsei arxeio h to arxeio pou fortwses den eixe grammes");
	    		}
	    		else{
		    		System.out.println("Dwse thn sunarthsh SUM/AVG");
		    		String aggFunction = input.next();
		    		while(!aggFunction.equals("SUM") && !aggFunction.equals("AVG")){
		    			System.out.println("Dwse thn sunarthsh SUM/AVG");
		        		aggFunction = input.next();
		    		}
		    		System.out.println("Epele3e ton tropo pou tha einai omadopoihmena ta dedomena [epoxh/mhna/mera/xrono]");
		    		String aggregatorType = input.next();
		    		while(!aggregatorType.equals("epoxh") && !aggregatorType.equals("mhna") && !aggregatorType.equals("mera") && !aggregatorType.equals("xrono")){
		    			System.out.println("Epelekse ton tropo pou tha einai omadopoihmena ta dedomena [epoxh/mhna/mera/xrono]");
		        		aggregatorType = input.next();
		    		}
		    		System.out.println("Dose mia perigrafh pou thes na prostethei sta dedomena sou");
		    		String dd = input.next();
		    		String description = input.nextLine();
		    		my.setTime(aggregatorType);
		    		r = my.aggregateByTimeUnit(objCollection, aggregatorType, aggFunction, description);
	    		}    		
	    	}
	    	else if(Integer.parseInt(choice) == 3){
	    		if(addedLines==0){
	    			System.out.println("Den exeis fortwsei arxeio h to arxeio pou fortwses den eixe grammes");
	    		}
	    		else{
		    		System.out.println("Dose to onoma tou arxeiou pou thes na apothhkeutoun ta dedomena");
		    		String outFile = input.next();
		    		System.out.println("Dose ton tupo tou arxeiou pou thes na apothhkeutoun ta dedomena");
		    		String reportType = input.next();
		    		while(!reportType.equals("txt") && !reportType.equals("md") && !reportType.equals("html")){
		    			System.out.println("Oi apodektoi tupoi arxeion einai *.txt,  *.html kai *.md");
		    			reportType = input.next();
		    		}
		    		int fileLines = my.reportResultInFile(r, reportType, outFile);
		    		if(fileLines==0){
		    			System.out.println("To arxeio " + outFile + "." + reportType + " grafthke kanonika!");
		    		}
		    		else{
		    			System.out.println("To arxeio " +outFile + "." + reportType + " den grafthke swsta!");
		    		}
	    		}	
	    	}
	    	
	    	else if(Integer.parseInt(choice) == 4){
	    		System.out.println("To programma termatisthke!");
	    		System.exit(1);
	    	}
    	}
		
    }
    

    
}