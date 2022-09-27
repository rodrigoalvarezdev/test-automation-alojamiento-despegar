package com.files.pages;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.files.utils.Helper;

public class AlojamientoPage{
	public WebDriver driver;
	private WebDriverWait wait;
	@FindBy(css = "i.login-incentive--close.shifu-3-icon-close.-eva-3-mr-md")
	WebElement modal;
	@FindBy(css = "*.sbox-places-destination--1xd0k input.input-tag")
	WebElement inputDestinoLocator;
	@FindBy(css = "*.ac-container")
	WebElement menu;
	@FindBy(css = "*.ac-group-container:nth-child(1) li:nth-child(1)")
	WebElement menuInputLoc;
	@FindBy(css = "*.sbox5-dates-input1 input.input-tag")
	WebElement inputFechaEntLocator;
	@FindBy(css = "*.sbox5-dates-input2 input.input-tag")
	WebElement inputFechaSalLocator;
	@FindBy(xpath = "//*[@id='component-modals']//*[@class='sbox5-monthgrid'][1]//*[text()='30']")
	WebElement diaEntradaLocator;
	@FindBy(xpath = "//*[@id='component-modals']//*[@class='sbox5-monthgrid'][2]//*[text()='10']")
	WebElement diaSalidaLocator;
	@FindBy(css = "*.calendar-footer-cta-container button[type='button']")
	WebElement btnaplicarCalenLocator;
	@FindBy(css = "*.sbox5-3-double-input input.sbox5-3-second-input")
	WebElement inputHabLocator;
	@FindBy(xpath = "//span[text()='Adultos']//parent::*//following::*//button[@class='steppers-icon-right stepper__icon']")
	WebElement btnMasAdulto;
	@FindBy(xpath = "//span[text()='Adultos']//parent::*//following::*//button[@class='steppers-icon-left stepper__icon']")
	WebElement btnMenosAdulto;
	@FindBy(xpath = "//span[text()='Menores']//parent::*//following::*//button[@class='steppers-icon-right stepper__icon']")
	WebElement btnMasNino;
	@FindBy(xpath = "//span[text()='Menores']//parent::*//following::*//button[@class='steppers-icon-left stepper__icon']")
	WebElement btnMenosNino; 
	@FindBy(css = "*.stepper__distribution_container *.stepper__room__row:nth-child(3) select")
	WebElement selectNino; 
	@FindBy(css = "a.sbox5-3-btn.-md.-primary")
	WebElement btnAplicarHabLocator;
	@FindBy(css = "*.sbox5-box-container button")
	WebElement btnBuscar;
	@FindBy(css = "ul.header-list-products>li>a")
	List<WebElement> linksList;
		
	public AlojamientoPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(6));
		PageFactory.initElements(driver, this);
	}
	
	public WebElement returnInput() {
		return inputDestinoLocator;
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
	
	public HotelesPage clickBuscar() {
		btnBuscar.click();
		return new HotelesPage(this.driver);
	}
	
	public void links() throws MalformedURLException, IOException {
		System.out.println("cantidad de links " + linksList.size());
		Helper.testLinks(linksList);
	}
}
