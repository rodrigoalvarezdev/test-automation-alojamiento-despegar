package com.files.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
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
import com.files.pages.PageHoteles;
import com.files.pages.PageResult;
import com.files.utils.Helper;
public class Alojamiento {
	  private WebDriver driver;
	  private PageAlojamiento pageHome;
	  private PageHoteles pageHoteles;
	  private PageResult pageResult;
	  private Helper helper;
	  
  @BeforeMethod(alwaysRun = true)
  public void setUp(ITestContext context) {
	  System.out.println("-------------------");
	  System.out.println("comenzando el test");
	  System.out.println("-------------------");
	  System.out.println(context.getStartDate());
	  this.driver = Drivers.levantarBrowser("CHROME", "https://www.despegar.com.ar/hoteles/");
	  pageHome = new PageAlojamiento(driver);
	  pageHoteles = new PageHoteles(driver);
	  pageResult = new PageResult(driver);
	  helper = new Helper(driver);
	}
  
  @DataProvider
  public Object[][] data() {
	return new Object[][] {
		{"men", "3 años"}
	};
	  
  }
  
  @Test(priority =  1, description = "validar happy path de alojamiento", enabled = true, dataProvider = "data")
  public void test(String ciudad, String edad) throws InterruptedException{
	 pageHome.closeModal();
	 Assert.assertFalse(pageHome.modal().isDisplayed(),"El modal no desapareció");
	 
	 pageHome.inputDestino(ciudad);
	 Assert.assertEquals("Mendoza, Mendoza, Argentina", pageHome.textInputDestino(), "el texto no coincide");
	 
	 pageHome.calendarioFechas();
	 Assert.assertEquals("Vie, 30 Sep 2022", pageHome.textFechaEntrada(), "el texto no coincide");
	 Assert.assertEquals("Lun, 10 Oct 2022", pageHome.textFechaSalida(), "el texto no coincide");
	 
	 pageHome.inputHabitaciones(edad);
	 Assert.assertEquals("4", pageHome.textInputHabitacion(), "el texto no coincide");
	
	 pageHome.clickBuscar();
	
	 pageHoteles.waitTitulo();
	 Assert.assertTrue(pageHoteles.tituloValidar().isDisplayed(), "El elemento no se encuentra");
	 
	 pageHoteles.clickImg();
	
	 helper.handlesFocus(1);
	 
	 pageResult.waitResultTitle();
	 Assert.assertTrue(pageResult.resultTitle().isDisplayed(), "El elemento no se encuentra");
  }

  @AfterMethod(alwaysRun = true)
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
