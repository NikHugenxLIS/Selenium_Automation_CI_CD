package com.web.commonUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

public class FileUtility 
{
	private WebDriver driver;
    static Properties properties;
   private final String propertyFilePath = "D:/Selenium Project/Selenium_Automation/src/main/resources/CommonData/CommonData.properties";

//         public FileUtility (WebDriver driver)
//
//
//        {
//	        this.driver = driver;
//         }
	
	public String getPropertyKeyValue(String Key) throws IOException
	{
		FileInputStream fis =new FileInputStream(propertyFilePath);
		Properties pobj= new Properties();
		pobj.load(fis);
		String value =pobj.getProperty(Key);
		return value;
	}

	public String getProperty(String keyvalue)
	{
		String key = properties.getProperty(keyvalue);
		return key;
	}
	
	public String getUserNameFromProperty()

	{
		String username = properties.getProperty("username");

		if (username != null)
			return username;
		else
			throw new RuntimeException("Text not specified in the Configuration.properties file.");

	}
	
	public String getPasswordFromProperty()

	{
		String password = properties.getProperty("password");

		if (password != null)
			return password;
		else
			throw new RuntimeException("Text not specified in the Configuration.properties file.");

	}
	
	public String getUrlFromProperty()

	{
		String url = properties.getProperty("url");

		if (url != null)
			return url;
		else
			throw new RuntimeException("url not specified in the Configuration.properties file.");

	}
	
	public String getBrowserfromproperty() throws IOException
	{
		String BROWSER = getPropertyKeyValue("BROWSER");

		if (BROWSER != null)
			return BROWSER;
		else
			throw new RuntimeException("url not specified in the Configuration.properties file.");
	}

	
	

}
