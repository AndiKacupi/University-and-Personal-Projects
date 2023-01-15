package dataload;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import datamodel.MeasurementRecord;

public class LoadFile<E> implements ILoader<E>{


	
	@Override
	public int load(String fileName, String delimeter, boolean hasHeaderLine, int numFields, ArrayList<E> objCollection) {
		
		//Wrong num of fields < 1
		if (numFields < 1){
			System.out.println("Wrong number of fields, less than 1!");
			System.exit(0);		
		}
		
		//Opening files for read and write, checking exception
		Scanner inputStream = null;
		
			try {
				
				inputStream = new Scanner(new FileInputStream(fileName));

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("ERROR: Wrong file path");
				e.printStackTrace();
			}
			
		
		
		//process the title
		int count = 0;
		if(hasHeaderLine){
			String titleLine = inputStream.nextLine();
			count++;
		}
		
		
		//process the actual rows one by one
		String line = "";

		while (inputStream.hasNextLine()) {
			MeasurementRecord r1 = new MeasurementRecord();
			line = inputStream.nextLine();
			count++;
			
			StringTokenizer tokenizer = new StringTokenizer(line, delimeter);
			if(tokenizer.countTokens() != numFields){
				System.out.println("Wrong Input format in file " + fileName +". I found " + tokenizer.countTokens() + " values, but I expect " + numFields + " values per row!");
				System.exit(0);				
			}
			
			String[] tokens =  new String[numFields];
			for (int i=0; i< numFields;i++){
				tokens[i] = tokenizer.nextToken();
			}
			r1.setMeasurementRecord(tokens);
			if(tokens.length<numFields)
				continue;
			objCollection.add((E) r1);
		}
		inputStream.close();
		
		return count;
	}

	

}