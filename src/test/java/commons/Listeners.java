package commons;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import com.files.utils.Helper;

public class Listeners implements ITestListener {
	
	 	 public void onTestStart(ITestResult result) {
	 		 System.out.println("probando " +result.getName()+" "+result.getMethod().getDescription());
		 }
		 
		 public void onTestSuccess(ITestResult result) {
			 System.out.println("finalizó correctamente " +result.getName()+" "+result.getMethod().getDescription());
			 Reporter.log("finalizó correctamente " +result.getName()+" "+result.getMethod().getDescription());
			 Reporter.log(result.getTestContext().getCurrentXmlTest().getParameter("browser"));
		 }
		 
		 public void onTestFailure(ITestResult result) {
			 System.out.println("ha fallado " +result.getName()+" "+result.getMethod().getDescription());
			 Reporter.log("ha fallado " +result.getName()+" "+result.getMethod().getDescription());
			 Reporter.log("<br><img src='C:\\Users\\rodri\\eclipse-workspace\\Test_automation\\test-output\\"+result.getName()+".png' height='400' width='400'/><br>");
			 System.out.println(result.getTestContext().getCurrentXmlTest().getParameter("browser"));
			 Reporter.log(result.getTestContext().getCurrentXmlTest().getParameter("browser"));
		 }
		 
		 public void onTestSkipped(ITestResult result) {
			 System.out.println("Test Skipped " +result.getName());
			 Reporter.log("Test Skipped " +result.getName());
		 }
		 
		 public void onStart(ITestContext context) {
			 System.out.println(context.getOutputDirectory());
		 }
		 
		 public void onFinish(ITestContext context) {
			 System.out.println(context.getCurrentXmlTest().getParameter("browser"));
			 System.out.println("resumen" +context.getPassedTests());
			 System.out.println("resumen" +context.getFailedTests());
			 Helper.openReport("C:\\Users\\rodri\\eclipse-workspace\\Test_automation\\test-output\\index.html");
		 }
}
