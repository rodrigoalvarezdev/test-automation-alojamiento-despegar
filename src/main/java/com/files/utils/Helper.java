package com.files.utils;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;

public class Helper {
	
	public static void handlesFocus(WebDriver driver, int index) {
		ArrayList<String> listHandles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(listHandles.get(index));
	}
	
	public static void testLinks(List<WebElement> list) throws MalformedURLException, IOException {
		for (WebElement i : list) {
			  if (i.getAttribute("href")==null || i.getAttribute("href").isEmpty()) {
				  System.out.println(i.getText() + " link vacio o nulo");
				  System.out.println("sin codigo");
			  }else {
			  
			  HttpURLConnection httpConection = (HttpURLConnection)(new URL(i.getAttribute("href")).openConnection());
			  httpConection.setRequestMethod("HEAD");
			  httpConection.connect();
			  int responseCode = httpConection.getResponseCode();
			  System.out.println(i.getText() + " " + i.getAttribute("href"));
			  System.out.println(responseCode);
			  Assert.assertEquals(200, responseCode, "el link está roto");
			  }
		}
	}
	
	
	public static void takeScreenshot(WebDriver driver, ITestResult result) {
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
	
	
	public static void openReport(String path) {
		 File objetofile = new File (path);
         try {
			Desktop.getDesktop().open(objetofile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
