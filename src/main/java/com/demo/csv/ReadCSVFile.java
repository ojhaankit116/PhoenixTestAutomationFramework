package com.demo.csv;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class ReadCSVFile {

	public static void main(String[] args) throws IOException, CsvException{
		//Code to read csv file in java[imp for interview]
		
		/*
		 * File csvFile = new File("C:\\Selenium_WorkSpace\\PhoenixTestAutomationFramework\\src\\main\\resources\\testData\\LoginCreds.csv");
		   FileReader fr = new FileReader(csvFile);
		 * 
		*/
		
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/LoginCreds.csv");
		InputStreamReader isr = new InputStreamReader(is);
		CSVReader csvReader = new CSVReader(isr); //CSVReader Constructor requires a reader.
		
		List<String[]> dataList = csvReader.readAll();
		
		for (String[] dataArray : dataList) {
			for(String data : dataArray) {
				System.out.print(data+" ");
		}
			System.out.println("");
		}
	}

}
