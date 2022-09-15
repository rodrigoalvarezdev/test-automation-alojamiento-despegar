package com.files.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseAlojamiento {
	WebDriver driver;
	WebDriverWait wait;
	Select select;
	Actions action;
	
	public BaseAlojamiento(WebDriver driver, WebDriverWait wait, Select select, Actions action) {
		this.driver = driver;
		this.wait = wait;
		this.select = select;
		this.action = action;
	}
	
	public WebDriver connection() {
		System.setProperty("webdriver.chrome.driver", "./src/test/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;
	}
	
	public void visit(String url) {
		driver.get(url);
	}
	
	public Select selectElement(By locator) {
		return select = new Select(driver.findElement(locator));
	}
	
	public WebElement waiting(By locator) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(6));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void click(By locator) {
		driver.findElement(locator).click();
	}
	
	public void sendText(By locator, String text) {
		driver.findElement(locator).sendKeys(text);
	}
	
	public void pressKey(By locator, Keys key) {
		driver.findElement(locator).sendKeys(key);
	}
	
	public void doubleClick(By locator) {
		action = new Actions(driver);
		action.doubleClick(driver.findElement(locator)).build().perform();
	}
}
