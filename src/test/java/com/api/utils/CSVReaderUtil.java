package com.api.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import com.dataproviders.api.bean.UserBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVReaderUtil {
	/* IN UTIL CLASS
	   1.Constructor is private
	   2.static - static methods, 
	   job of CSVReaderUtil : help read the csv file and map it to bean
	*/
	private CSVReaderUtil() {
		//Created so no one can create Object of CSVReaderUtil outside the class
	}
	
	public static Iterator<UserBean> loadCSV(String pathOfCSVFile) {
		
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOfCSVFile);
		InputStreamReader isr = new InputStreamReader(is);
		CSVReader csvReader = new CSVReader(isr);
		
		CsvToBean<UserBean> csvToBean = new CsvToBeanBuilder(csvReader)
				.withType(UserBean.class)
				.withIgnoreEmptyLine(true)
				.build();
		
		List<UserBean> userList = csvToBean.parse();
		return userList.iterator();
	}
}
