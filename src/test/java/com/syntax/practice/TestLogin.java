package com.syntax.practice;

import com.syntax.pages.LoginPageWebElements;
import com.syntax.testBase.BaseClass;
import com.syntax.utils.ConfigUtility;

import org.testng.Assert;
import org.testng.annotations.*;

public class TestLogin extends BaseClass{

	@Test
	public void loginTest() throws InterruptedException {
		LoginPageWebElements login=new LoginPageWebElements();
		driver.getTitle();
		login.username.sendKeys(ConfigUtility.getProperty("username"));
		login.password.sendKeys(ConfigUtility.getProperty("password"));
		login.loginBtn.click();
		
		Thread.sleep(3000);
		
	}
	
	@Test (dataProvider = "data")
	public static void negativeLoginTest(String username, String password, String expErrMsg) {
		LoginPageWebElements login=new LoginPageWebElements();
		
		login.username.sendKeys(username);
		login.password.sendKeys(password);
		login.loginBtn.click();
		
		String actualErrMsg=login.errMsg.getText();
		
		Assert.assertEquals(actualErrMsg, expErrMsg);
	}
	
	@DataProvider
	public Object[][] data(){
		Object[][] data={ 
			{"Admin", "admin12", "Invalid credentials"},
			{"Admi", "admin123", "Invalid credentials"},
			{"Admin", "", "Password cannot be empty"}
		};
		
		return data;
	}
	
	
}
