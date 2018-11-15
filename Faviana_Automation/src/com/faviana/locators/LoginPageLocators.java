
package com.faviana.locators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPageLocators
{
	public LoginPageLocators(WebDriver driver)
	{
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
	}
	
	public @FindBy(xpath="//a[text()='Click Here'])[1]") WebElement registrationLink;
	public @FindBy(id="email") WebElement usernameField;
	public @FindBy(name="login[password]") WebElement passwordField;
	public @FindBy(id="send2") WebElement submitButton;

	
	
	
	

	
		
}

