package commons;

import org.testng.annotations.DataProvider;

public class DataProviderTest {
	@DataProvider
	  public Object[][] data() {
		return new Object[][] {
			{"men", "3 años"}
			//,{"cor", "2 años"}
			//,{"bue", "3años"}
		};
		  
	  }
}
