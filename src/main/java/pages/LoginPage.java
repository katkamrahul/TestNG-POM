package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;

public class LoginPage extends Base{
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//input[@id='Email']")
	WebElement txtEmail;
	
	@FindBy (xpath = "//input[@id='Password']")
	WebElement txtPassword;
	
	@FindBy (xpath = "//button[normalize-space()='Log in']")
	WebElement btnLogin;
	
	public void setTxtEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void setTxtPassword(String password) {
		txtPassword.sendKeys(password);
	}
	
	public void clkBtnLogin() {
		btnLogin.click();
	}
	
	public String pageTitle() {
		return driver.getTitle();
	}
}