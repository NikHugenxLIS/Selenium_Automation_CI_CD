package com.web.hugenx.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.web.commonUtils.WebDriverUtility;


public class LoginPage 
{

	public WebDriver driver;
	public WebDriverUtility utility;


	private By usernametb = By.xpath("//input[@formcontrolname='Username']");
	private By passwordtb = By.xpath("//input[@formcontrolname='Password']");
	private By loginBtn = By.xpath("//button[@type='submit']");


	public LoginPage(WebDriver driver) {


		this.driver = driver;
		this.utility = new WebDriverUtility(driver);
	}
	

	
	public void enterUsername(String username) throws InterruptedException 
	{

		utility.sendKeys(usernametb, username);
	}

	public void enterPassword(String password) 
	{
		driver.findElement(passwordtb).sendKeys(password);
	}

	public void clickLoginButton() 
	{
		driver.findElement(loginBtn).click();
	}

	public String getLoginBtnText()
	{
		String text = driver.findElement(loginBtn).getText();
		return text;
	}





}
