package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Base;

public class TC001RegisterNewUser extends Base{
	@Test (groups = {"regression"})
	public void registerNewUser() throws IOException {
		try {
			hp.clnLnkRegister();
			log.info("Clicked on link 'Register'");							//Write this line in log file
			rap.clkRadBtnGender();
			log.info("Selected the gender radio button");					//Write this line in log file
			rap.setTxtFirstName(randomString());
			log.info("Entered text in first name");							//Write this line in log file
			rap.setTxtLastName(randomString());
			log.info("Entered text in last name");							//Write this line in log file
			//rap.setTxtEmail(randomString() + "@gmail.com");
			rap.setTxtEmail("sourabhsonawane@gmail.com");
			log.info("Entered text in email address");						//Write this line in log file
			rap.setTxtPassword("admin1234");
			log.info("Entered text in password");							//Write this line in log file
			rap.setTxtConfirmPassword("admin1234");
			log.info("Entered text in confirm password");					//Write this line in log file
			rap.clkBtnRegister();
			log.info("Clicked on button 'Register'");						//Write this line in log file
			
			if(rasp.successMsg().equals("Your registration completed")) {
				log.info("User has successfully created his acount");		//Write this line in log file
				Assert.assertTrue(true);
			}
			else {
				log.error("User was unsuccessful in creating the account");	//Write this line in log file
				captureScreen(driver, "registerNewUser");
				Assert.assertTrue(false);
			}
		}
		catch(Exception e) {
			log.fatal("Exception occured :" +e);						//Write this line in log file
			captureScreen(driver, "registerNewUser");
			Assert.fail();
		}
	}
}