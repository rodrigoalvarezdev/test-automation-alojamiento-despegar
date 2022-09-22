package com.files.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageResult {
	public WebDriver driver;
	private WebDriverWait wait;
	
	@FindBy(xpath = "//h1[@class='accommodation-name eva-3-h2']")
	WebElement resultTitle;
	
	public PageResult(WebDriver driver) {
		  this.driver = driver;
		  this.wait = new WebDriverWait(driver, Duration.ofSeconds(6));
		  PageFactory.initElements(driver, this);
	}
	
	public void waitResultTitle() {
		wait.until(ExpectedConditions.visibilityOf(resultTitle));
	}
	
	public WebElement resultTitle() {
		return resultTitle;
	}
}
