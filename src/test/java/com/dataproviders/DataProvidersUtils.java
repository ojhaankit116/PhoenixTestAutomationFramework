package com.dataproviders;

import java.util.Iterator;

import org.testng.annotations.DataProvider;

import com.api.utils.CSVReaderUtil;
import com.dataproviders.api.bean.UserBean;

public class DataProvidersUtils {
	
	//If I am not giving a name to the dataprovider, then the name of the dataprovider becomes the Methodname  
	@DataProvider(name = "LoginAPIDataProvider", parallel = true)
	public static Iterator<UserBean> loginAPIDataProvider() {
		return CSVReaderUtil.loadCSV("testData/LoginCreds.csv");
		
	}
	
	//Data Provider needs to return something!!
	//And return type can take 3 formats
	//[] - Single Dim Array
	//[] [] - 2D Array
	// Iterator
}
