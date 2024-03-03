package com.hugenx.Login;

import com.web.commonUtils.ExcelDataDriven;
import com.web.commonUtils.FileUtility;
import com.web.hugenx.pages.LoginPage;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginScript  {

	LoginPage loginpage;
	FileUtility fileutility;
	ExcelDataDriven excelDataDriven;
	public LoginScript(WebDriver driver) {
		loginpage = new LoginPage(driver);
		fileutility = new FileUtility();
		excelDataDriven = new ExcelDataDriven();
	}


	public void clickOnLoginBtn() throws IOException, InterruptedException
	{
		//String username = fileutility.getPropertyKeyValue("username");
		//String password = fileutility.getPropertyKeyValue("password");
		ArrayList<String> data = excelDataDriven.getData("Login");

		Assert.assertEquals(loginpage.getLoginBtnText(), "LOGIN","Login Page is not Loaded as LOGIN Btn is not displayed");
		loginpage.enterUsername(data.get(1));
		loginpage.enterPassword(data.get(2));
     	loginpage.clickLoginButton();
		
	}

}