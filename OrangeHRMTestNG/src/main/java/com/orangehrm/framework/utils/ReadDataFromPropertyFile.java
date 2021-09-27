package com.orangehrm.framework.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadDataFromPropertyFile {

	public static Properties readProperties(String filepath) {
		FileInputStream fis = null;
		Properties prop = null;
		try {
			fis = new FileInputStream(filepath); // to read the property file
			prop = new Properties(); // initiate properties
			prop.load(fis); // load all properties from property file
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
}
