package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Base;

public class TC002LoginUser extends Base {
	@Test (groups = {"sanity"})
	public void loginUser () throws IOException {
		try {
			hp.clnLnkLogIn();
			log.info("Clicked on link 'Log in'");							//Write this line in log file
			lp.setTxtEmail(rb.getString("username"));
			log.info("Entered text in 'Email address'");					//Write this line in log file
			lp.setTxtPassword(rb.getString("password"));
			log.info("Entered text in 'Password'");							//Write this line in log file
			lp.clkBtnLogin();
			log.info("Clicked on button 'Login'");							//Write this line in log file
			
			if(ulp.checkLnkLogout()) {
				log.info("User has successfully logged into his acount");	//Write this line in log file
				Assert.assertTrue(true);
			}
			else {
				log.error("User was unsuccessful in logging to his account");	//Write this line in log file
				captureScreen(driver, "loginUser");
				Assert.assertTrue(false);
			}
		}
		catch(Exception e) {
			log.fatal("Exception occured :" +e);							//Write this line in log file
			captureScreen(driver, "loginUser");
			Assert.fail();
		}
	}
}