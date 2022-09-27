package com.files.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResultPage {
	public WebDriver driver;
	private WebDriverWait wait;
	
	@FindBy(css = "h1.accommodation-name.eva-3-h2")
	WebElement resultTitle;
	
	public ResultPage(WebDriver driver) {
		  this.driver = driver;
		  PageFactory.initElements(driver, this);
	}
	
	public void waitResultTitle() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(6));
		wait.until(ExpectedConditions.visibilityOf(resultTitle));
	}
	
	public WebElement resultTitle() {
		return resultTitle;
	}
}
