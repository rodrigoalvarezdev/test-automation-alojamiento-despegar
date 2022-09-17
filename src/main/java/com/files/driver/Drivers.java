package com.files.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Drivers {
	private enum browsers{
		CHROME, FIREFOX, EDGE
	}
	
	public static WebDriver levantarBrowser(String browserName, String url) {
		WebDriver driver = null;
		switch(browsers.valueOf(browserName)) {
			case CHROME:{
				System.setProperty("webdriver.chrome.driver", "./src/test/drivers/chromedriver.exe");
				System.out.println("Se inicia Chrome");
				driver = new ChromeDriver();
				break;
			}
			case FIREFOX:{
				System.setProperty("webdriver.gecko.driver", "./src/test/drivers/geckodriver.exe");
				System.out.println("Se inicia firefox");
				driver = new FirefoxDriver();
				break;
			}
			case EDGE:{
				System.setProperty("webdriver.edge.driver", "./src/test/drivers/msedgedriver.exe");
				System.out.println("se inicia edge");
				driver = new EdgeDriver();
				break;
			}
			default:{
				System.out.println("No se elige nigun navegador");
				System.setProperty("webdriver.chrome.driver", "./src/test/drivers/chromedriver.exe");
				System.out.println("Se inicia chrome");
				driver = new ChromeDriver();
				break;
			}
		}
		driver.manage().window().maximize();
		driver.get(url);
		return driver;
	}
	
	/*public static void cerrarBrowser(WebDriver driver) {
		driver.quit();
	}*/
}
