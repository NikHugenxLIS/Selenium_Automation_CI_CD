package com.web.commonUtils;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;

import static java.lang.System.getProperty;

public class BaseClass
{
	public WebDriver driver ;
    public  String BROWSER;
   
	FileUtility fLib = new FileUtility();
	JavaUtility jLib = new JavaUtility();
	ExcelUtility eLib = new ExcelUtility();
	
	
	@BeforeClass
	public void configBC() throws IOException
	{
		
		System.out.println("============Lauch the browser==========");
         // Setting up the Global Parameter to take/Pass the Browser Name from Command Prompt
		String browser = getProperty("BROWSER")!=null ? getProperty("BROWSER") : fLib.getPropertyKeyValue("BROWSER");

		//String browser = fLib.getPropertyKeyValue("BROWSER");
		
		if(browser.contains("chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browser.contains("headless"))
			{
				options.addArguments("headless");
			}
			//options.addArguments("headless");
			driver =new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver =new FirefoxDriver();
			driver.manage().window().maximize();
		}
		else if(browser.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			driver.manage().window().maximize();
		}
		else
		{
			System.out.println("Browser not found");
		}
	}
	
	
	
	@BeforeMethod
	public void configBM() throws IOException
	{
		
		//String BROWSER = fLib.getPropertyKeyValue("browser");
		//String BROWSER = "edge";
		//this.BROWSER=BROWSER;
		String URL = fLib.getPropertyKeyValue("url");
		String USERNAME = fLib.getPropertyKeyValue("username");
		String PASSWORD = fLib.getPropertyKeyValue("password");
	
		driver.get(URL);
		
		//LOGIN lp = new LOGIN(driver);
		//lp.loginToApp(USERNAME, password);
		

	}
	@AfterMethod
	public void configAM() throws InterruptedException
	{     
		
		//HomePage HomePage = new HomePage(driver);
		//Thread.sleep(60000);
		//HomePage.logoutAction();
	}
	
	@AfterClass
	public void configAC()
	{
		//driver.close();
	}
}

