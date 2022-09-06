package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;

public class RegisterAccountPage extends Base{
	public RegisterAccountPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//input[@id='gender-male']")
	WebElement radBtnGender;
	
	@FindBy (xpath = "//input[@id='FirstName']")
	WebElement txtFirstName;
	
	@FindBy (xpath = "//input[@id='LastName']")
	WebElement txtLastName;
	
	@FindBy (xpath = "//input[@id='Email']")
	WebElement txtEmail;
	
	@FindBy (xpath = "//input[@id='Password']")
	WebElement txtPassword;
	
	@FindBy (xpath = "//input[@id='ConfirmPassword']")
	WebElement txtConfirmPassword;
	
	@FindBy (xpath = "//button[@id='register-button']")
	WebElement btnRegister;
	
	public void clkRadBtnGender() {
		radBtnGender.click();
	}
	
	public void setTxtFirstName(String firstName) {
		txtFirstName.sendKeys(firstName);
	}
	
	public void setTxtLastName(String lastName) {
		txtLastName.sendKeys(lastName);
	}
	
	public void setTxtEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void setTxtPassword(String password) {
		txtPassword.sendKeys(password);
	}
	
	public void setTxtConfirmPassword(String confirmPassword) {
		txtConfirmPassword.sendKeys(confirmPassword);
	}
	
	public void clkBtnRegister() {
		btnRegister.click();
	}
	
	public String pageTitle() {
		return driver.getTitle();
	}
}