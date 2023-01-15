package reporting;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import datamodel.IResult;

public class ReportProducer implements IResultReporter {
	
	private String filetype;
	
	public void setfileType(String type){
		filetype = type;
	}
	
	@Override
	public int reportResultInFile(IResult result, String filename)  {
		
			if(filetype.equals("html")){
				
			    PrintWriter printWriter = null;
				try {
					printWriter = new PrintWriter(filename + ".html");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    printWriter.println("<!doctype html>");
			    printWriter.println("<html>");
			    printWriter.println("<head>");
			    printWriter.println("<title>" + result.getDescription() + "</title>");
			    printWriter.println("</head>");
			    printWriter.println("<body>"); 
			    printWriter.println("<h1>" + result.getDescription() + "</h1>");
			    printWriter.println("<p>" + result.getAggregateFunction() + " consumption (watt-hours) over (a) Kitchen, (b) Laundry, (c) A/C </p>");
			    
			    
			    //KITCHEN
			    printWriter.println("<h2> Kitchen </h2>");
			    printWriter.println("<ul>");
			    for(Map.Entry kv : result.getAggregateMeterKitchen().entrySet()) {
			    	
			    	printWriter.println("<li> " + kv);
			    	
			    }
			    printWriter.println("</ul>");
			    
			    
			    //LAUNDRY
			    printWriter.println("<h2> Laundry </h2>");
			    printWriter.println("<ul>");
			    for(Map.Entry kv : result.getAggregateMeterLaundry().entrySet()) {
			    	
			    	printWriter.println("<li> " + kv);
			    	
			    }
			    printWriter.println("</ul>");
			    
			    //AC
			    printWriter.println("<h2>  A/C </h2>");
			    printWriter.println("<ul>");
			    for(Map.Entry kv : result.getAggregateMeterAC().entrySet()) {
			    	
			    	printWriter.println("<li> " + kv);
			    	
			    }
			    printWriter.println("</ul>");
			    
			    
			    
			    
			    printWriter.close();
			    
			    return 0;
			}
			
			
			
			
			else if(filetype.equals("txt")){
				
				//creating txt file
				File file = new File(filename);
				
				
				PrintWriter printWriter = null;
				try {
					printWriter = new PrintWriter(filename + ".txt");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    printWriter.println(result.getDescription());
			    printWriter.println("=======================================");
			    printWriter.println(result.getAggregateFunction() + " consumption (watt-hours) over (a) Kitchen, (b) Laundry, (c) A/C");
			    
			    //KITCHEN
			    printWriter.println("");
			    printWriter.println("Kitchen");
			    printWriter.println("--------------");

			    for(Map.Entry kv : result.getAggregateMeterKitchen().entrySet()) {
			    	
			    	printWriter.println("* " + kv);
			    	
			    }
			    
			    //LAUNDRY
			    printWriter.println("");
			    printWriter.println("Laundry");
			    printWriter.println("--------------");
			    for(Map.Entry kv : result.getAggregateMeterLaundry().entrySet()) {
			    	
			    	printWriter.println("* " + kv);
			    	
			    }
			    
			    
			    //A/C
			    printWriter.println("");
			    printWriter.println("A/C");
			    printWriter.println("--------------");
			    for(Map.Entry kv : result.getAggregateMeterAC().entrySet()) {
			    	
			    	printWriter.println("* " + kv);
			    	
			    }
			    

			    printWriter.close();
			    return 0;
			}
			
			else if(filetype.equals("md")){
				
				
				//creating markdown file
				File file = new File(filename);
				PrintWriter printWriter = null;
				try {
					printWriter = new PrintWriter(filename + ".md");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    printWriter.println("#" + result.getDescription());
			    printWriter.println(" ");
			    printWriter.println(result.getAggregateFunction() + " consumption (watt-hours) over (a) Kitchen, (b) Laundry, (c) A/C");
			    
			    //KITCHEN
			    printWriter.println(" ");
			    printWriter.println("## Kitchen");
			    printWriter.println(" ");
			    for(Map.Entry kv : result.getAggregateMeterKitchen().entrySet()) {
			    	
			    	printWriter.println("* " + kv);
			    	
			    }
			    
			    
			    //LAUNDRY
			    printWriter.println(" ");
			    printWriter.println("## Laundry");
			    printWriter.println(" ");
			    for(Map.Entry kv : result.getAggregateMeterLaundry().entrySet()) {
			    	
			    	printWriter.println("* " + kv);
			    	
			    }
			    
			    
			    //A/C
			    printWriter.println(" ");
			    printWriter.println("## A/C");
			    printWriter.println(" ");
			    for(Map.Entry kv : result.getAggregateMeterAC().entrySet()) {
			    	
			    	printWriter.println("* " + kv);
			    	
			    }
			    
			    printWriter.close();
				return 0;
				
			
				
			
			}
			else{
				System.out.println("ERROR");
				System.exit(0);
			}
			return -1;
	}
	
}