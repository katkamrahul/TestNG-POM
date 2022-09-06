package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;

public class RegisterAccountSuccessPage extends Base {
	public RegisterAccountSuccessPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[normalize-space()='Your registration completed']")
	WebElement successMsg;
	
	public String successMsg() {
		return successMsg.getText();
	}
	
	public String pageTitle() {
		return driver.getTitle();
	}
}