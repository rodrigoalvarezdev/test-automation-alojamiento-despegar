package com.files.utils;

import java.io.File;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
public class Helper {
	WebDriver driver;
	
	public Helper(WebDriver driver) {
		this.driver = driver;
	}
	
	public void handlesFocus(int index) {
		ArrayList<String> listHandles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(listHandles.get(index));
	}
	
	public void takeScreenshot(ITestResult result) {
		if (!result.isSuccess()) {
			File myScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(myScreenshot, new File("test-output/"+result.getName()+".png"));
				System.out.println("screenshot obtenida");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
