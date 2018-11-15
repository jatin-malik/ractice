package com.faviana.testScripts;

//import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

//import com.faviana.pageHelpers.CommonHelper;
import com.faviana.util.DriverTestCase;


public class Faviana_Registration extends DriverTestCase
{
	
	@Test(description="User is able to Login to Faviana")
	public void ApplicationLandingPage() throws Exception
	{
		//User is able to Login to Faviana
		
		commonHelper.click(homeScreenLocators.loginLink);
		test.info("User should be able to click on login Link");
		
		commonHelper.click(loginPageLocators.usernameField);
		
		commonHelper.EnterText(loginPageLocators.usernameField, userName);
		test.info("User should be able to enter username");
		
		commonHelper.EnterText(loginPageLocators.passwordField, password);
		test.info("User should be able to enter username");
		
		commonHelper.click(loginPageLocators.submitButton);
		test.info("User should be able to sign in");

		
/*		commonHelper.EnterText(loginLogoutLocator.userNameField, username);
		test.info("User should be able to enter username");

		commonHelper.EnterText(loginLogoutLocator.passwordField, password);
		test.info("User should be able to enter password");

		commonHelper.click(loginLogoutLocator.signInButton);
		test.info("User should be able to click on SignIN button");
		// WINDOW COUNT
		System.out.println(driver.getWindowHandles().size());
		commonHelper.click(loginLogoutLocator.logOut);
		test.info("User should be able to click on logout button");

		commonHelper.waitForTitle("Dominion - Web Control");
		test.info("User should be able to logout and title should match Expected Result");
		
		//User is able to reset the password Using Forget Password
		commonHelper.click(loginLogoutLocator.loginButton);
		test.info("User should be able to click on login button");

		commonHelper.click(loginLogoutLocator.forgotUserIDLink);
		test.info("User should be able to click Forget user ID");
		
		commonHelper.sendKeys(loginLogoutLocator.alternateEmailtextfield, alternateEmail);
		test.info("User should be able to enter alternate email address");
		
		commonHelper.click(loginLogoutLocator.getUserId);
		test.info("User should be able to click on Get User id button");
		Thread.sleep(4000);
		commonHelper.click(loginLogoutLocator.close);
		test.info("User should be able to close the popup");
		Thread.sleep(1000);
		//User is able to able to get User ID from forget User ID
		commonHelper.click(loginLogoutLocator.loginButton);
		test.info("User should be able to click on login button");
		
		commonHelper.click(loginLogoutLocator.forgotPwdLockedOutLink);
		test.info("User should be able to click on Forgot Password link");
		
		commonHelper.sendKeys(loginLogoutLocator.UserID, UserId);
		test.info("User should be able to enter user id under corresponding field");
		
		commonHelper.sendKeys(loginLogoutLocator.alternateEmailtextfield_1, alternateEmailtextfield_1);
		test.info("User should be able to enter alternate Email in the corresponding field");
		
		commonHelper.click(loginLogoutLocator.getPassword);
		test.info("User should be able to click on Get Password Buttton");*/
		
		
		System.out.println("Hello Jatin");
	}

	private void click(WebElement loginButton) {
		// TODO Auto-generated method stub
		
	}
		
}