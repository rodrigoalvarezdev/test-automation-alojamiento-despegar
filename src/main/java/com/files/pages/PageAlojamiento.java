package com.files.pages;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageAlojamiento extends BaseAlojamiento {
	By cerrarModal = By.xpath("//i[@class='login-incentive--close shifu-3-icon-close -eva-3-mr-md']");
	By inputDestinoLocator = By.xpath("//*[@class='sbox-places-destination--1xd0k']//input[@class='input-tag']");
	By inputFechaEntLocator = By.xpath("//*[@class='sbox5-dates-input1']//input[@class='input-tag']");
	By inputFechaSalLocator = By.xpath("//*[@class='sbox5-dates-input2']//input[@class='input-tag']");
	By diaEntradaLocator = By.xpath("//*[@id='component-modals']//*[@class='sbox5-monthgrid'][1]//*[text()='15']");
	By diaSalidaLocator = By.xpath ("//*[@id='component-modals']//*[@class='sbox5-monthgrid'][2]//*[text()='10']");
	By btnaplicarCalenLocator = By.xpath("//*[@class='calendar-footer-cta-container']//button[@type='button']");
	By inputHabLocator = By.xpath("//*[@class='sbox5-3-double-input']//input[@class='sbox5-3-second-input']");
	By btnMasAdulto = By.xpath("//*[@class='stepper__distribution_container']/*[1]//button[@class='steppers-icon-right stepper__icon']");
	By btnMenosAdulto = By.xpath("//span[text()='Adultos']//parent::*//following::*//button[@class='steppers-icon-right stepper__icon']");
	By btnMasNino = By.xpath("//span[text()='Menores']//parent::*//following::*//button[@class='steppers-icon-right stepper__icon']");
	By btnMenosNino = By.xpath("//span[text()='Menores']//parent::div//following::div//button[@class='steppers-icon-left stepper__icon']");
	By selectNino = By.xpath("//*[@class='stepper__distribution_container']//*[@class='stepper__room__row'][3]//select[@class='select']");
	By btnAplicarHabLocator = By.xpath("//a[@class='sbox5-3-btn -md -primary']");
	By btnBuscar = By.xpath("//*[@class='sbox5-box-container']//button");
	By tituloValidar = By.xpath("//h1[@class='sbox5-title']");
	By imgCard = By.xpath("//*[@id='slider_id_2']/swiper");
	By resultTitle = By.xpath("//h1[@class='accommodation-name eva-3-h2']");
	By menuInputLoc = By.xpath("//*[@class='ac-group-container'][1]//li[1]");
	By menu = By.xpath("//*[@class='ac-container']");
	
	public PageAlojamiento(WebDriver driver, WebDriverWait wait, Select select, Actions action) {
		super(driver, wait, select, action);
	}
	
	public void closeModal() {
		waiting(cerrarModal);
		click(cerrarModal);
	}
	
	public By modal() {
		return cerrarModal;
	}
	
	public void inputDestino(String text){
		doubleClick(inputDestinoLocator);
		doubleClick(inputDestinoLocator);
		click(inputDestinoLocator);
		pressKey(inputDestinoLocator, Keys.CONTROL);
		sendText(inputDestinoLocator, text);
		waiting(menu);
		waiting(menuInputLoc);
		click(menuInputLoc);
	}
	
	public String textInputDestino() {
		return driver.findElement(inputDestinoLocator).getAttribute("value");
	}
	
	public void calendarioFechas() {
		click(inputFechaEntLocator);
		waiting(diaEntradaLocator);
		click(diaEntradaLocator);
		click(inputFechaSalLocator);
		waiting(diaSalidaLocator);
		click(diaSalidaLocator);
		click(btnaplicarCalenLocator);
	}
	
	public String textFechaEntrada() {
		return driver.findElement(inputFechaEntLocator).getAttribute("value");
	}
	
	public String textFechaSalida() {
		return driver.findElement(inputFechaSalLocator).getAttribute("value");
	}
	
	public void inputHabitaciones(String text) {
		click(inputHabLocator);
		waiting(btnMasAdulto);
		click(btnMasAdulto);
		click(btnMasNino);
		selectElement(selectNino).selectByVisibleText(text);
		click(btnAplicarHabLocator);
	}
	
	public String textInputHabitacion() {
		return driver.findElement(inputHabLocator).getAttribute("value");
	}
	
	public void clickBuscar() {
		click(btnBuscar);
	}
	
	public By tituloValidar() {
		return tituloValidar;
	}
	
	public void clickImg() {
		click(imgCard);
	}
	
	public void switchHandles() {
		Set <String> handles = driver.getWindowHandles();
		for (String actual : handles) {
			if(!actual.equalsIgnoreCase(driver.getWindowHandle())) {
				driver.switchTo().window(actual);
				break;
			}
			
		}
	}
	
	public By resultTitle() {
		return resultTitle;
	}

}
