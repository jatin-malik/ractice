package com.faviana.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class DriverHelper
{
	protected WebDriver driver;
	protected WebDriverWait wait;

	public DriverHelper(WebDriver driver)
	{
		this.driver = driver;	
		PageFactory.initElements(driver, this);
	}

	public WebDriver getWebDriver()
	{
		return driver;
	}

	public WebDriverWait getWait(int timeOut)
	{
		wait = new WebDriverWait(driver, timeOut);
		wait.ignoring(StaleElementReferenceException.class);
		return wait;
	}

	public Boolean isElementPresent(WebElement locator) 
	{
		Boolean result = false;
		try 
		{
			locator.isDisplayed();
			result = true;
		} catch (Exception ex){}
		return result;
	}

	public Boolean isElementNotPresent(WebElement locator) 
	{
		Boolean result = true;
		try 
		{
			locator.isDisplayed();
			result = false;
		} catch (Exception ex){}
		return result;
	}

	public void WaitForElementSelected(WebElement locator, int timeout)
	{
		getWait(timeout).until(ExpectedConditions.elementToBeSelected(locator));
	}

	public void WaitForElementNotVisible(WebElement locator, int timeout)
	{
		getWait(timeout).until(ExpectedConditions.invisibilityOf(locator));
	}

	public void WaitForElementVisible(WebElement locator, int timeout)
	{
		getWait(timeout).until(ExpectedConditions.visibilityOf(locator));
	}

	public void WaitForElementClickable(WebElement locator, int timeout) 
	{
		getWait(timeout).until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void waitForTitle(String title)
	{
		getWait(20).until(ExpectedConditions.titleContains(title));
	}

	public void mouseOver(WebElement locator)
	{		
		this.WaitForElementVisible(locator, 20);		
		WebElement el = locator;
		Actions builder = new Actions(getWebDriver());    
		builder.moveToElement(el).build().perform();		
	}

	public void mouseDoubleClick(WebElement locator)
	{
		this.WaitForElementVisible(locator, 20);		
		WebElement el = locator;
		Actions builder = new Actions(getWebDriver());    
		builder.doubleClick(el).perform();
	}

	public void dragAndDrop(WebElement draggable, WebElement to)
	{
		WebElement elDraggable = draggable;
		WebElement todrag = to;
		Actions builder = new Actions(getWebDriver());   
		builder.dragAndDrop(elDraggable, todrag).perform();		
	}

	public void clickOn(WebElement locator) 
	{	
		WaitForElementVisible(locator, 20);
		WaitForElementClickable(locator, 20);
		Assert.assertTrue(isElementPresent(locator),"Element Locator :"+locator+" Not found");
		locator.click();
	}

	public void clearTextField(WebElement locator)
	{		
		this.WaitForElementVisible(locator, 20);
		Assert.assertTrue(isElementPresent(locator),"Element Locator :"+locator+" Not found");
		locator.clear();
	}

	public void sendKeys(WebElement locator, String text) 
	{	
		WaitForElementVisible(locator, 20);
		Assert.assertTrue(isElementPresent(locator), "Element Locator :"+locator+" Not found");
		locator.clear();
		locator.sendKeys(text);
	}

	public void selectDropDownByVisibleText(WebElement locator, String targetValue)
	{ 
		Assert.assertTrue(isElementPresent(locator),"Element Locator :"+locator+" Not found");
		this.WaitForElementVisible(locator, 20);
		new Select(locator).selectByVisibleText(targetValue);		
	}

	public void selectDropDownByValue(WebElement locator, String targetValue)
	{ 
		Assert.assertTrue(isElementPresent(locator),"Element Locator :"+locator+" Not found");
		this.WaitForElementVisible(locator, 20);
		new Select(locator).selectByValue(targetValue);
	}

	public boolean isTextPresent(WebElement locator, String str)
	{
		WaitForElementVisible(locator, 20);
		Assert.assertTrue(isElementPresent(locator) ,"Element Locator :"+locator+" Not found");
		String message = locator.getText();		
		System.out.println("VALUE : " + message);
		if(message.contains(str)){return true;}
		else {	return false; }
	}

	public String getText(WebElement locator)
	{
		WaitForElementVisible(locator, 20);
		Assert.assertTrue(isElementPresent(locator),"Element Locator :"+locator+" Not found");
		String text = locator.getText();			
		return text;
	}

	public String getTitle()
	{
		String text = getWebDriver().getTitle();		
		return text;
	}

	public String getPageURL()
	{
		String text = getWebDriver().getCurrentUrl();		
		return text;
	}

	public void switchToFrame(WebElement locator)
	{
		WaitForElementVisible(locator, 20);
		driver.switchTo().frame(locator);
	}

	public boolean isChecked(WebElement locator)
	{
		boolean checkStatus = false;
		WaitForElementVisible(locator, 20);
		Assert.assertTrue(isElementPresent(locator),"Element Locator :"+locator+" Not found");
		WebElement el = locator;	    
		checkStatus = el.isSelected();	    
		return checkStatus;
	}

	public String getAttribute(WebElement locator, String attribute)
	{
		WaitForElementVisible(locator, 20);
		Assert.assertTrue(isElementPresent(locator),"Element Locator :"+locator+" Not found");
		String text = locator.getAttribute(attribute);	
		return text;
	}

	public void javaScriptExecute(String javascrpt)
	{
		JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
		js.executeScript(javascrpt);
	}

	public void scrollingElement(WebElement locator)
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();", locator);
	}

	public void clickByActionClass(WebElement locator)
	{
		Actions action = new Actions(driver);
		WaitForElementVisible(locator, 20);
		action.moveToElement(locator).click().build().perform();
	}

	public void scrollingDown()
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,150)", "");
		//((JavascriptExecutor) d).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void scrollingup()
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
	}

	public void switchtoTab() throws InterruptedException
	{
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(tabs2.size()-1));
	}    

	public void switchBacktoMainWindow()
	{
		driver.close();
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(0));
	}

	public String getCSSValue(WebElement locator,String attribute)
	{
		WaitForElementVisible(locator, 20);
		Assert.assertTrue(isElementPresent(locator),"Element Locator :"+locator+" Not found");
		return locator.getCssValue(attribute);
	}

	public void javascriptSendKeys(WebElement locator, String text) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value='" + text + "'", locator);
	}

	public String getCurrentDate()
	{
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		return dateFormat.format(date).toString();
	}

	public String getFutureDate()
	{
		Date date = new Date();
		Calendar cal = Calendar.getInstance(); 
		cal.add(Calendar.DATE, 7);
		date = cal.getTime();
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		return dateFormat.format(date).toString();
	}

	public String getPrevDate()
	{
		Date date = new Date();
		Calendar cal = Calendar.getInstance(); 
		cal.add(Calendar.DATE, -7);
		date = cal.getTime();
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		return dateFormat.format(date).toString();
	}

	public int randomNumberInfinite()
	{
		return (int) System.currentTimeMillis();
	}

	public void waitForPageLoad()
	{
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}

	public int tableRowCount(String tblLocator)
	{
		int rowNumber =driver.findElements(By.xpath(tblLocator)).size();
		return rowNumber;				
	}

	public void acceptPopup()
	{
		Alert alert =driver.switchTo().alert();
		alert.accept();
	}

	public void setAttributeVisible(WebElement locator)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].removeAttribute('style', 'visibility: visible;');", locator);

	}

	public void waitForPageLoadAjax()
	{
		while(true)
		{
			String value = ((JavascriptExecutor) driver).executeScript("return document.readyState").toString();
			if(value.equals("complete"))
			{
				break;
			}
		}
	}
	
	public Boolean DropDownexist(WebElement locator) 
	{	
		boolean result=false;
		boolean dropdownPresence,dropdownEnabled = false;
		dropdownPresence = locator.isDisplayed();
        dropdownEnabled = locator.isEnabled();   
		try 
		{	if (dropdownPresence==true && dropdownEnabled==true)
		{
			result = true;
		}
		} 
		catch (Exception ex){}
		return result;	
	}
	
}