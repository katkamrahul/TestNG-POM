package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;

public class HomePage extends Base{
	public HomePage(){
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	@FindBy (xpath = "//a[normalize-space()='Log in']")
	WebElement lnkLogIn;
		
	public void clnLnkRegister() {
		lnkRegister.click();
	}
	
	public void clnLnkLogIn() {
		lnkLogIn.click();
	}
	
	public String pageTitle() {
		return driver.getTitle();
	}
}