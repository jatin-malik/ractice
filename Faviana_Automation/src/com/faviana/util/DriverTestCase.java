package com.faviana.util;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.faviana.locators.HomeScreenLocators;
import com.faviana.locators.LoginPageLocators;
import com.faviana.pageHelpers.CommonHelper;
import com.faviana.pageHelpers.RegistrationHelper;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class DriverTestCase
{
	public static WebDriver driver;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static PropertyReader propertyReader;
	public static String applicationUrl = null;
	public static String browsername = null;			
	public static String usernameClient =  null;
	public static String passwordClient= null;
	public static CommonHelper commonHelper;
	public String loginButton="";
	public String userName="";
	public String password="";
	public String signInButton="";
	public String exception="";
	public String alternateUserId="";
	public String sendUserId="";
	public String forgotPwdLockedOutLink="";
	public String UserId="";
	public String alternateEmail="";
	public String sendPasswordbutton="";
	public String cancelbutton="";
	public String alternateEmailtextfield_1="";

	public HomeScreenLocators homeScreenLocators;
	public LoginPageLocators loginPageLocators;
	public RegistrationHelper registrationHelper;
	
	enum DriverType
	{
		Firefox, IE, Chrome
	}

	@BeforeSuite
	public void deleteFolder()
	{
		try 
		{
			FileUtils.deleteDirectory(new File("./Screenshots"));
			FileUtils.forceMkdir(new File("./Screenshots"));

			extent = new ExtentReports();
			htmlReporter = new ExtentHtmlReporter("./Screenshots/extent.html");
			htmlReporter.setAppendExisting(true);
			htmlReporter.config().setChartVisibilityOnOpen(true);
			htmlReporter.config().setDocumentTitle("WebControlAutomation - Execution Report");
			htmlReporter.config().setEncoding("UTF-8");
			htmlReporter.config().setReportName("Execution Report");
			htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
			htmlReporter.config().setTheme(Theme.STANDARD);
			htmlReporter.config().setTimeStampFormat("mm/dd/yyyy hh:mm:ss a");
			extent.attachReporter(htmlReporter);

		} catch (Exception e) {}
	}

	@BeforeClass
	public void setUp() throws MalformedURLException, InterruptedException
	{

		propertyReader = new PropertyReader();
		applicationUrl = propertyReader.readApplicationFile("WebURL");
		browsername = propertyReader.readApplicationFile("BROWSER");	
		userName = propertyReader.readApplicationFile("userName");
		password = propertyReader.readApplicationFile("password");
		alternateUserId = propertyReader.readApplicationFile("alternateUserId");
		sendUserId= propertyReader.readApplicationFile("sendUserId");
		UserId=propertyReader.readApplicationFile("UserId");
		alternateEmail= propertyReader.readApplicationFile("AlternateEmail");
		alternateEmailtextfield_1=propertyReader.readApplicationFile("alternateEmailtextfield_1");

		if (DriverType.Firefox.toString().equals(browsername))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} 
		else if (DriverType.IE.toString().equals(browsername))
		{
			WebDriverManager.iedriver().arch32().setup();
			driver = new InternetExplorerDriver();
		}
		else 
		{
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-infobars");
			options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
			options.addArguments("--disable-popup-blocking");
			driver = new ChromeDriver(options);
		}

		commonHelper=new CommonHelper(driver);
		registrationHelper = new RegistrationHelper(driver); 
		
		homeScreenLocators=new HomeScreenLocators(driver);
		loginPageLocators = new LoginPageLocators(driver);
		openURL();
	}

	@BeforeMethod
	public void startTest(Method method)
	{
		test = extent.createTest(method.getName());
		test.assignAuthor("360Logica");
		test.assignCategory(this.getClass().getCanonicalName());
	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws Exception
	{
		if (result.getStatus() == ITestResult.FAILURE)
		{
			test.error(result.getThrowable());
			test.addScreenCaptureFromPath(captureScreenshot(result.getName()));
			test.fail("Failed...");
		}
		else
			if(result.getStatus()==ITestResult.SUCCESS)
			{
				test.pass("Passed...");
			}
			else
			{
				test.skip("Skipped...");
			}
	}

	@AfterClass
	public void quitDriver()
	{
		driver.quit();
	}

	@AfterSuite
	public void generateReport()
	{
		extent.flush();
	}

	public WebDriver getWebDriver()
	{
		return driver;
	}

	public String getPath()
	{		
		String path =" ";		
		File file = new File("");
		String absolutePathOfFirstFile = file.getAbsolutePath();
		path = absolutePathOfFirstFile.replaceAll("\\\\+", "/");		
		return path;
	} 

	public String captureScreenshot(String result) throws Exception 
	{
		String location="";
		try
		{
			DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
			Date date = new Date();
			location= getPath() +File.separator+ "Screenshots"+File.separator  + result + "_"  + dateFormat.format(date) +".jpeg" ;
			File SrcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			File DestFile=new File(location);
			FileUtils.copyFile(SrcFile, DestFile);
			System.out.println("Screenshot location : "+location);
		}
		catch(Exception e){}
		return location;
	}	

	public void openURL()
	{
		getWebDriver().navigate().to(applicationUrl);
		getWebDriver().manage().window().maximize();
		commonHelper.waitForTitle("Designer Dresses by Faviana New York | Faviana");
	}

	public void loginToApplication()
	{
		commonHelper.click(homeScreenLocators.loginLink);
		test.info("User should be able to click on login button");

		/*commonHelper.EnterText(homeScreenLocators.userNameField, username);
		test.info("User should be able to enter username");

		commonHelper.EnterText(homeScreenLocators.passwordField, password);
		test.info("User should be able to enter password");

		commonHelper.click(homeScreenLocators.signInButton);
		test.info("User should be able to click on SignIN button");*/
	}
}