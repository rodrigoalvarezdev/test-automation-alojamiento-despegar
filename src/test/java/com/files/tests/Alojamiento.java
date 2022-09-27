package com.files.tests;

import org.testng.annotations.Test;
import com.files.driver.Drivers;
import com.files.pages.AlojamientoPage;
import com.files.pages.HotelesPage;
import com.files.pages.ResultPage;
import com.files.utils.Helper;
import commons.DataProviderTest;
import org.testng.annotations.BeforeMethod;
import java.io.IOException;
import java.net.MalformedURLException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
public class Alojamiento {
	  private WebDriver driver;
	  private AlojamientoPage pageHome;

  @BeforeMethod(alwaysRun = true)
  public void setUp(ITestContext context) {
	  System.out.println("-------------------");
	  System.out.println("comenzando el test");
	  System.out.println("-------------------");
	  System.out.println(context.getStartDate());
	  String navSuite = context.getCurrentXmlTest().getParameter("browser");
	  String nav = navSuite != null ? navSuite : "CHROME";
	  this.driver = Drivers.levantarBrowser(nav, "https://www.despegar.com.ar/hoteles/");
	  pageHome = new AlojamientoPage(driver);
	}
  
  @Test(priority =  1, description = "validar happy path de alojamiento", enabled = true, dataProvider = "data",
		  groups = "test", dataProviderClass = DataProviderTest.class)
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
	
	 HotelesPage pageHoteles = pageHome.clickBuscar();
	 
	 pageHoteles.waitTitulo();
	 Assert.assertTrue(pageHoteles.tituloValidar().isDisplayed(), "El elemento no se encuentra");
	 
	 ResultPage pageResult =  pageHoteles.clickImg();
	
	 Helper.handlesFocus(driver, 1);
	 
	 pageResult.waitResultTitle();
	 Assert.assertTrue(pageResult.resultTitle().isDisplayed(), "El elemento no se encuentra");
  }
  
  
  
  @Test(description = "validar url de links del header de la pagina", priority = 2, enabled = true,
		  groups = "links")
  public void printLinks() throws MalformedURLException, IOException {
	  pageHome.links();
  }
  
  
  
  @Test(description = "validar que la pagina este online", priority = 3, enabled = true, groups = "sanity")
  public void getBrowser() {
	  Assert.assertTrue(pageHome.returnInput().isDisplayed(), "la pagina no esta disponible");
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown(ITestResult result) {
	  Helper.takeScreenshot(driver, result);
	  System.out.println(result.getThrowable());
	  System.out.println("-------------------");
	  System.out.println("terminando el test");
	  System.out.println("-------------------");
	  driver.quit();
  }
}
