package com.orangehrm.tests;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.orangehrm.framework.utils.ReadDataFromExcelFile;
import com.orangehrm.framework.webdriver.WebDriverClass;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;

public class ApplicationTest extends WebDriverClass {

	@Test (priority=1,enabled=true)
	public void VerifyApplicationTitle() throws IOException {
		initiateTestCaseReporting("Verify Application Title");
		LoginPage loginPage = LoginPage.getLoginPage();
		loginPage.launchApplication();
		loginPage.verifyTitle();
	}

	@Test(dataProvider = "LoginData",priority=2,dependsOnMethods= {"VerifyApplicationTitle"})
	public void VerifyApplicationLogin(String Username, String Password) throws IOException {
		initiateTestCaseReporting("Verify Application Login");
		LoginPage loginPage = LoginPage.getLoginPage();
		loginPage.launchApplication();
		loginPage.loginIntoApplication(Username, Password);
	}

	@Test(dataProvider = "LogoutData",priority=3)
	public void VerifyApplicationLogout(String Username, String Password) throws IOException {
		initiateTestCaseReporting("Verify Application Logout");
		LoginPage loginPage = LoginPage.getLoginPage();
		HomePage homePage = HomePage.getHomePage();
		loginPage.launchApplication();
		loginPage.loginIntoApplication(Username, Password);
		homePage.logoutFromApplication();
	}

	@DataProvider(name = "LoginData")
	public String[][] testData() {
		String[][] data = new ReadDataFromExcelFile()
				.getExcelData(System.getProperty("user.dir") + "\\TestData\\TestData.xlsx", "Sheet1");
		return data;
	}

	@DataProvider(name = "LogoutData")
	public String[][] logOutData() {
		String[][] data = new ReadDataFromExcelFile()
				.getExcelData(System.getProperty("user.dir") + "\\TestData\\TestData.xlsx", "Sheet2");
		return data;
	}
}
