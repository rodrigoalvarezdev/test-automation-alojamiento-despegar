package com.files.pages;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageAlojamiento{
	public WebDriver driver;
	private WebDriverWait wait;
	@FindBy(xpath = "//i[@class='login-incentive--close shifu-3-icon-close -eva-3-mr-md']")
	WebElement modal;
	@FindBy(xpath = "//*[@class='sbox-places-destination--1xd0k']//input[@class='input-tag']")
	WebElement inputDestinoLocator;
	@FindBy(xpath = "//*[@class='ac-container']")
	WebElement menu;
	@FindBy(xpath = "//*[@class='ac-group-container'][1]//li[1]")
	WebElement menuInputLoc;
	@FindBy(xpath = "//*[@class='sbox5-dates-input1']//input[@class='input-tag']")
	WebElement inputFechaEntLocator;
	@FindBy(xpath = "//*[@class='sbox5-dates-input2']//input[@class='input-tag']")
	WebElement inputFechaSalLocator;
	@FindBy(xpath = "//*[@id='component-modals']//*[@class='sbox5-monthgrid'][1]//*[text()='30']")
	WebElement diaEntradaLocator;
	@FindBy(xpath = "//*[@id='component-modals']//*[@class='sbox5-monthgrid'][2]//*[text()='10']")
	WebElement diaSalidaLocator;
	@FindBy(xpath = "//*[@class='calendar-footer-cta-container']//button[@type='button']")
	WebElement btnaplicarCalenLocator;
	@FindBy(xpath = "//*[@class='sbox5-3-double-input']//input[@class='sbox5-3-second-input']")
	WebElement inputHabLocator;
	@FindBy(xpath = "//*[@class='stepper__distribution_container']/*[1]//button[@class='steppers-icon-right stepper__icon']")
	WebElement btnMasAdulto;
	@FindBy(xpath = "//span[text()='Adultos']//parent::*//following::*//button[@class='steppers-icon-right stepper__icon']")
	WebElement btnMenosAdulto;
	@FindBy(xpath = "//span[text()='Menores']//parent::*//following::*//button[@class='steppers-icon-right stepper__icon']")
	WebElement btnMasNino;
	@FindBy(xpath = "//span[text()='Menores']//parent::div//following::div//button[@class='steppers-icon-left stepper__icon']")
	WebElement btnMenosNino; 
	@FindBy(xpath = "//*[@class='stepper__distribution_container']//*[@class='stepper__room__row'][3]//select[@class='select']")
	WebElement selectNino; 
	@FindBy(xpath = "//a[@class='sbox5-3-btn -md -primary']")
	WebElement btnAplicarHabLocator;
	@FindBy(xpath = "//*[@class='sbox5-box-container']//button")
	WebElement btnBuscar; 
		
	public PageAlojamiento(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(6));
		PageFactory.initElements(driver, this);
	}
	
	public void closeModal() {
		wait.until(ExpectedConditions.visibilityOf(modal));
		modal.click();
	}
	
	public WebElement modal() {
		return modal;
	}
	
	public void inputDestino(String text) throws InterruptedException{
		inputDestinoLocator.click();
		Thread.sleep(1000);
		inputDestinoLocator.sendKeys(text);
		wait.until(ExpectedConditions.visibilityOf(menu));
		wait.until(ExpectedConditions.visibilityOf(menuInputLoc));
		menuInputLoc.click();
	}
	
	public String textInputDestino() {
		return inputDestinoLocator.getAttribute("value");
	}
	
	public void calendarioFechas() {
		inputFechaEntLocator.click();
		wait.until(ExpectedConditions.visibilityOf(diaEntradaLocator));
		diaEntradaLocator.click();
		inputFechaSalLocator.click();
		wait.until(ExpectedConditions.visibilityOf(diaSalidaLocator));
		diaSalidaLocator.click();
		btnaplicarCalenLocator.click();
	}
	
	public String textFechaEntrada() {
		return inputFechaEntLocator.getAttribute("value");
	}
	
	public String textFechaSalida() {
		return inputFechaSalLocator.getAttribute("value");
	}
	
	public void inputHabitaciones(String text) {
		inputHabLocator.click();
		wait.until(ExpectedConditions.visibilityOf(btnMasAdulto));
		btnMasAdulto.click();
		btnMasNino.click();
		Select select = new Select(selectNino);
		select.selectByVisibleText(text);
		btnAplicarHabLocator.click();
	}
	
	public String textInputHabitacion() {
		return inputHabLocator.getAttribute("value");
	}
	
	public PageHoteles clickBuscar() {
		btnBuscar.click();
		return new PageHoteles(this.driver);
	}
}
