package com.orangehrm.pages;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.orangehrm.framework.commons.WebCommons;
import com.orangehrm.framework.webdriver.WebDriverClass;

public class LoginPage extends WebCommons {

	ExtentTest Logger = WebDriverClass.getLogger();

	@FindBy(xpath = "//input[@id='txtUsername']")
	private WebElement usernameTxtb;

	@FindBy(xpath = "//input[@id='txtPassword']")
	private WebElement passwordTxtb;

	@FindBy(xpath = "//input[@value='LOGIN']")
	private WebElement loginBtn;

	@FindBy(xpath = "//a[@id='welcome']")
	private WebElement welcomeLabel;

	public void launchApplication() throws IOException {
		try {
			driver.get(appProperties().getProperty("Url"));
			Logger.pass(appProperties().getProperty("Url"));
			Logger.pass("Application Launched Successfully");
		} catch (Exception e) {
			Logger.addScreenCaptureFromPath(TakeScreenshot("ApplicationLaunch"));
			Logger.fail("Error while Launching the Application");
			Assert.fail("Error while Launching the Application");
		}
	}

	public void verifyTitle() throws IOException {
		if (getTitle().equals(appProperties().getProperty("title"))) {
			Logger.pass("Application title is as Expected");
		} else {
			Logger.addScreenCaptureFromPath(TakeScreenshot("ApplicationTitle"));
			Logger.fail("Application title is Not Correct and current title ===" + getTitle());
		}
	}

	public void loginIntoApplication(String Username, String Password) throws IOException {
		Logger.info("Test Data : " + Username + " , " + Password);
		try {
			EnterInfo(usernameTxtb, Username);
			EnterInfo(passwordTxtb, Password);
			Click(loginBtn);
			WaitForElement(welcomeLabel);
			Logger.pass("Login is Successful");
		} catch (Exception e) {
			Logger.addScreenCaptureFromPath(TakeScreenshot("ApplicationLogin"));
			Logger.fail("Error while Login Into the Application");
			Assert.fail("Error while Login Into the Application");
		}
	}

	public static LoginPage getLoginPage() {
		return PageFactory.initElements(new WebDriverClass().getDriver(), LoginPage.class);
	}

}
