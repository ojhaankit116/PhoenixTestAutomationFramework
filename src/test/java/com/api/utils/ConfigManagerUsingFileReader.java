package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManagerUsingFileReader {
//WAP to read the properties file from /PhoenixTestAutomationFramework src/test/resources/config/config.properties

	// Using Properties class to read the properties file
	private static Properties prop = new Properties(); // Object of Properties Class

	private ConfigManagerUsingFileReader() {
		// this will restrict object creation on ConfigManager
	}

	static {
		// Operation of loading the properties file in the memory
		// static block will be executed only once during Class loading time
		// File.separator for platform independence file handling
		File configFile = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "resources" + File.separator + "config" + File.separator + "config.properties");
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(configFile);
			prop.load(fileReader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {

		return prop.getProperty(key);
	}
}
