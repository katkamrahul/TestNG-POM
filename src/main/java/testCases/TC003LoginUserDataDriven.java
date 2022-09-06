package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.Base;
import utilities.XLUtility;

public class TC003LoginUserDataDriven extends Base {
	@Test(dataProvider = "Data", groups = {"sanity", "regression"})
	public void loginUserDataDriven(String username, String password, String result) throws IOException {
		try {
			hp.clnLnkLogIn();
			log.info("Clicked on link 'Log in'");							//Write this line in log file
			lp.setTxtEmail(username);
			log.info("Entered text in 'Email address'");					//Write this line in log file
			lp.setTxtPassword(password);
			log.info("Entered text in 'Password'");							//Write this line in log file
			lp.clkBtnLogin();
			log.info("Clicked on button 'Login'");							//Write this line in log file
			
			if(result.equals("Valid")) {
				if(ulp.checkLnkLogout() == true) {
					log.info("User has successfully logged with valid credentials");	//Write this line in log file
					ulp.clkLnkLogout();
					Assert.assertTrue(true);
				}
				else {
					log.error("User was unsuccessful to login with valid credentials");		//Write this line in log file
					captureScreen(driver, "loginUserDataDriven");
					Assert.assertTrue(false);
				}
			}
		
			if(result.equals("InValid")) {
				if(ulp.checkLnkLogout() == true) {
					log.info("User has successfully logged with valid credentials");	//Write this line in log file
					ulp.clkLnkLogout();
					Assert.assertTrue(false);
				}
				else {
					log.error("User log in failed");									//Write this line in log file
					captureScreen(driver, "loginUserDataDriven");
					Assert.assertTrue(true);
				}
			}
		}
		catch(Exception e) {
			log.fatal("User log in failed");											//Write this line in log file
			captureScreen(driver, "loginUserDataDriven");
			Assert.fail();
		}
	}
	
	@DataProvider(name="Data")
	public String [][] getData() throws IOException {
		String path=".\\testData\\nopCommerce.xlsx";
		
		XLUtility xlutil=new XLUtility(path);
		
		int totalrows=xlutil.getRowCount("Sheet1");	
		int totalcols=xlutil.getCellCount("Sheet1",1);
				
		String data[][]=new String[totalrows][totalcols];
		
		for(int i=1;i<=totalrows;i++) {		 	//1
			for(int j=0;j<totalcols;j++) {		//0
				data[i-1][j]= xlutil.getCellData("Sheet1",i, j);  		//1,0
			}
		}
		return data;
	}
}