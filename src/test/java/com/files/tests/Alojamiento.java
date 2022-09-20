package com.files.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import com.files.driver.Drivers;
import com.files.pages.PageAlojamiento;
import com.files.utils.Helper;
public class Alojamiento {
	  private WebDriver driver;
	  private PageAlojamiento page;
	  private Helper helper;
	  private WebDriverWait wait;
	  private Select select;
	  private Actions action;
	  
  @BeforeMethod(alwaysRun = true)
  public void setUp(ITestContext context) {
	  System.out.println("-------------------");
	  System.out.println("comenzando el test");
	  System.out.println("-------------------");
	  System.out.println(context.getStartDate());
	  this.driver = Drivers.levantarBrowser("CHROME", "https://www.despegar.com.ar/hoteles/");
	  page = new PageAlojamiento(driver, wait, select, action);
	  helper = new Helper(driver);
	}
  
  @DataProvider
  public Object[][] data() {
	return new Object[][] {
		{"men", "3 años"}
	};
	  
  }
  
  @Test(description = "validar happy path de alojamiento", enabled = true, dataProvider = "data")
  public void test(String ciudad, String edad) throws InterruptedException{
	 page.closeModal();
	 Assert.assertFalse(driver.findElement(page.modal()).isDisplayed(),"El modal no desapareció");
	 page.inputDestino(ciudad);
	 Assert.assertEquals("Mendoza, Mendoza, Argentina", page.textInputDestino(), "el texto no coincide");
	 page.calendarioFechas();
	 Assert.assertEquals("Jue, 15 Sep 2022", page.textFechaEntrada(), "el texto no coincide");
	 Assert.assertEquals("Lun, 10 Oct 2022", page.textFechaSalida(), "el texto no coincide");
	 page.inputHabitaciones(edad);
	 Assert.assertEquals("4", page.textInputHabitacion(), "el texto no coincide");
	 page.clickBuscar();
	 Assert.assertTrue(driver.findElement(page.tituloValidar()).isDisplayed(), "El elemento no se encuentra");
	 page.clickImg();
	 helper.handlesFocus(1);
	 Assert.assertTrue(driver.findElement(page.resultTitle()).isDisplayed(), "El elemento no se encuentra");
  }

  @AfterMethod
  public void tearDown(ITestResult result) {
	  helper.takeScreenshot(result);
	  System.out.println(result.getMethod().getDescription());
	  System.out.println(result.getStatus());
	  System.out.println("-------------------");
	  System.out.println("terminando el test");
	  System.out.println("-------------------");
	  driver.quit();
  }

}
