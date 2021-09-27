package com.orangehrm.framework.utils;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Sample {
	
	@Test(dataProvider="testdata")
	public void credentials(String username, String Password , String Userrole) {
		System.out.println(username+"===="+Password+"===="+Userrole);
	}
	
	
	@DataProvider(name="testdata")
	public String[][] testData() {
		String [][] data= {{"admin","admin123","Developer"}, {"ABC","123", "Tester"} , {"XYZ","123", "Tester"}};
		return data;
	}

}
