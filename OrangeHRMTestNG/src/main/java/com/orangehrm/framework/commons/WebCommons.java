package com.orangehrm.framework.commons;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orangehrm.framework.utils.ReadDataFromPropertyFile;
import com.orangehrm.framework.webdriver.WebDriverClass;

public class WebCommons {
	
	public WebDriver driver = WebDriverClass.getDriver();
	
	//Method to perform the Click
	public void Click(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
		element.click();
	}

	//Method to enter text in text box
	public void EnterInfo(WebElement element, String value) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
		element.clear();
		element.sendKeys(value);
	}
	
	//Method to select option from Dropdown
	public void SelectOption(WebElement element,String option, String selectBy) {
		Select s = new Select(element);
		if (selectBy.equalsIgnoreCase("VisibleText")) {
			s.selectByVisibleText(option);
		} else if (selectBy.equalsIgnoreCase("value")) {
			s.selectByValue(option);
		} else if (selectBy.equalsIgnoreCase("Index")) {
			s.selectByIndex(Integer.parseInt(option));
		} 
	}
	
	//Method to wait - Implicit Wait
	public void Wait() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	//Method to wait for Element- Implicit Wait
	public void WaitForElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	//Method o verify Element is available
	public boolean isElementAvailable(WebElement element) {
		return element.isDisplayed();
	}
	
	//Method to get title 
	public String getTitle() {
		return driver.getTitle();
	}
	
	//Method to get text 
	public String getText(WebElement element) {
		return element.getText();
	}
	
	//Method to take screenshot
		public String TakeScreenshot(String screenshotname) throws IOException {
			TakesScreenshot screen = ((TakesScreenshot)driver); // to take screenshot
			File screenshotFile = screen.getScreenshotAs(OutputType.FILE); //to convert it as File
			String screenshotPath = System.getProperty("user.dir") + "\\FailedScreenshots\\"+screenshotname+generateUniqueID()+".png";
			File screenPath = new File(screenshotPath);
			FileUtils.copyFile(screenshotFile, screenPath);
			return screenshotPath;			
		}
		
		//Method to generate unique random id
		public static String generateUniqueID() {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss"); // we have to specify the format
			Calendar cal = Calendar.getInstance();
			String uniqueid = sdf.format(cal.getTime());
			return uniqueid;		
		}
		
		//Method to generate unique random id
		public static String generateUniqueID2() {
			SimpleDateFormat sdf = new SimpleDateFormat("HHmmss"); // we have to specify the format
			Calendar cal = Calendar.getInstance();
			String uniqueid = sdf.format(cal.getTime());
			return uniqueid;		
		}
		
		//Method to select checkbox
		public void selectCheckbox(WebElement element) {
			if(!element.isSelected())
				element.click();
		}
		
		//Method to perform double Click
		public void DoubleClick(WebElement element) {
			Actions action = new Actions(driver);
			action.doubleClick(element).build().perform();
		}
		
		//Method to perform right Click
		public void RightClick(WebElement element) {
			Actions action = new Actions(driver);
			action.contextClick(element).build().perform();
		}
		
		//Method to read data from Config.properties file
		public static Properties appProperties() {
			Properties prop = ReadDataFromPropertyFile.readProperties(System.getProperty("user.dir") +"\\TestData\\Config.properties");
			return prop;
		}
}
