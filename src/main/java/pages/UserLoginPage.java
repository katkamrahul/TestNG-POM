package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;

public class UserLoginPage extends Base{
	public UserLoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//a[normalize-space()='Log out']")
	WebElement lnkLogout;
	
	public boolean checkLnkLogout() {
		return lnkLogout.isDisplayed();
	}
	
	public void clkLnkLogout() {
		 lnkLogout.click();
	}
	
	public String pageTitle() {
		return driver.getTitle();
	}
}