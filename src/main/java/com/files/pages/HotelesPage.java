package com.files.pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HotelesPage {
	public WebDriver driver;
	private WebDriverWait wait;
	
	@FindBy(xpath = "//h1[text()='Alojamientos']")
	WebElement tituloValidar;
	@FindBy(css = "*#slider_id_2>swiper")
	WebElement imgCard;
	
	public HotelesPage(WebDriver driver) {
	  this.driver = driver;
	  PageFactory.initElements(driver, this);
	}
	
	public void waitTitulo() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(6));
		wait.until(ExpectedConditions.visibilityOf(tituloValidar));
	}
	
	public WebElement tituloValidar() {
	return tituloValidar;
	}

	public ResultPage clickImg() {
	wait = new WebDriverWait(driver, Duration.ofSeconds(6));
    wait.until(ExpectedConditions.visibilityOf(imgCard));
	imgCard.click();
	return new ResultPage(driver);
	}
}
