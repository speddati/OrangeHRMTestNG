package com.orangehrm.framework.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class WebDriverClass {
	protected static WebDriver driver;
	public static ExtentTest logger;
	public static ExtentReports extent;
	
	@BeforeSuite
	public void setupReport() {		
		extent = new ExtentReports();// this line will initialize the report
		ExtentHtmlReporter htmlreport =new ExtentHtmlReporter(System.getProperty("user.dir")+"\\Reports\\AutomationTestReport.html");
		extent.attachReporter(htmlreport);
	}
	
	//Method to start printing the results of test case
	public static void initiateTestCaseReporting(String testcasename) {
		logger=extent.createTest(testcasename);
	}
	
	public static ExtentTest getLogger() {
		return logger;
	}	
	
	@BeforeMethod
	@Parameters("browser")
	public synchronized void SetupBrowser(String browser) {		
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if(browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if(browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"\\Drivers\\msedgedriver.exe");
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();		
	}
		
	public synchronized static WebDriver getDriver() {
		return driver;
	}
	
	@AfterMethod
	public synchronized void tearDown() {
		driver.quit();
		extent.flush();
	}

}
