package com.faviana.pageHelpers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.faviana.util.DriverHelper;


public class CommonHelper extends DriverHelper
{

	public CommonHelper(WebDriver driver) 
	{
		super(driver);	
	}

	public void verifyText(WebElement field, String text)
	{
		System.out.println("VALUE : "+getText(field));
		Assert.assertTrue(getText(field).contains(text));
	}

	public void click(WebElement button)
	{
		clickOn(button);
	}

	public void EnterText(WebElement field, String text) 
	{
		sendKeys(field, text);
	}

	public void getCss(WebElement locator, String attr)
	{
		getCSSValue(locator, attr);
	}

	public void VerifyFieldPresent(WebElement field)
	{
		WaitForElementVisible(field, 30);
		isElementPresent(field);
	}	

	public void VerifyFieldNotPresent(WebElement field)
	{
		isElementNotPresent(field);
	}

	public void switchToTab() throws Exception 
	{
		switchtoTab();
	}

	public void switchToMainWindow() 
	{
		switchBacktoMainWindow();
	}

	public void getPageTitle(String text) 
	{
		Assert.assertTrue(getTitle().contains(text));
	}

	public void getCurrentURL(String text) 
	{
		Assert.assertTrue(getPageURL().contains(text));
	}

	public void scrollDown()
	{
		scrollingDown();
	}

	public void scrollUp()
	{
		scrollingup();
	}

	public void scrollToElement(WebElement locator)
	{
		scrollingElement(locator);
	}

	public String getElementText(WebElement locator)
	{
		return getText(locator);
	}

	public void selectDropDownByText(WebElement locator, String targetValue)
	{ 
		selectDropDownByVisibleText(locator, targetValue);
	}

	public void selectDropDownByVal(WebElement locator, String targetValue)
	{ 
		selectDropDownByValue(locator, targetValue);
	}

	public void WaitForElementToBeClickable(WebElement locator, int timeout) 
	{
		WaitForElementClickable(locator, timeout);
	}

	public void WaitForElementToBeSelected(WebElement locator, int timeout) 
	{
		WaitForElementSelected(locator, timeout);
	}



	public void WaitForElementToBeVisible(WebElement locator, int timeout) 
	{
		WaitForElementVisible(locator, timeout);
	}

	public void waittoPageLoad()
	{
		waitForPageLoad();
	}

	public void currentDate()
	{
		getCurrentDate();
	}

	public void previousDate()
	{
		getPrevDate();
	}

	public void waitToPageLoadAjax()
	{
		waitForPageLoadAjax();
	}

	public void closeOtherWindows()
	{
		String mainWindow = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		for(String handle : handles)
		{
			if(handle !=mainWindow)
			{
				driver.switchTo().window(handle);
				driver.close();
			}
		}
	}

	public void selectUser(String userName) 
	{
		WebElement el = driver.findElement(By.xpath("//li/a[contains(text(),'"+userName+"')]"));
		WaitForElementClickable(el, 10);
		clickOn(el);		
	}
	
	public String getCurrentDate()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(new Date()).toString();
	}
	
	public String randomNumber()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddmmss");
		return sdf.format(new Date()).toString();
	}
}
