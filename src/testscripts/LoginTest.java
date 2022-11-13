package testscripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.PredefinedActions;
import pages.LoginPage;
import utility.ExcelOperations;

public class LoginTest {

	@Test(dataProvider = "LoginDataProvider")
	public void tc1(String url, String uname, String password, boolean isLoginSuccessful) {
		System.out.println("STEP - Launch Chrome Browser & Hit url");
		PredefinedActions.start(url);
		
		System.out.println("STEP - Enter valid login credentials");
		LoginPage loginPage = new LoginPage();
		loginPage.login(uname, password);
		
		if(isLoginSuccessful) {
			System.out.println("VERIFY - home page is displayed");
			String expetedTitle = "Employee Management";
			String actualTitle = loginPage.getPageTitle();
			Assert.assertEquals(actualTitle, expetedTitle, "Expected title was " + expetedTitle + " but actual title was " + actualTitle);
		}else {
			System.out.println("VERIFY - home page is displayed");
			String expetedTitle = "OrangeHRM";
			String actualTitle = loginPage.getPageTitle();
			
			Assert.assertEquals(actualTitle, expetedTitle, "Expected title was " + expetedTitle + " but actual title was " + actualTitle);
			
			System.out.println("VERIFY - Retry login page is loaded");
			String expectedUrlContent = "retryLogin";
			String actualCurrentURL = loginPage.getPageURL();
			Assert.assertTrue(actualCurrentURL.endsWith(expectedUrlContent));
		}
		
		PredefinedActions.closeBrowser();
	}
	
	@DataProvider(name = "LoginDataProvider")
	public Object[][] getLoginData() throws IOException {
		Object[][] data;
		String fileName = ".//testdata//LoginData.xlsx";
		try {
			data = ExcelOperations.readExcelData(fileName, "Data");
		} catch (IOException e) {
			data = ExcelOperations.readExcelData(".//testdata1//LoginData.xlsx", "Data");
		}		
		return data;	
	}
	
	@DataProvider(name = "LoginDataProvider1")
	public Object[][] getLoginData1(){
		
		Object[][] data = new Object[2][4];
		data[0][0] = "https://harshaug2022-trials76.orangehrmlive.com";
		data[0][1] = "admin";
		data[0][2] = "MPCs0K@ce1";
		data[0][3] = true;
		
		data[1][0] = "https://harshaug2022-trials76.orangehrmlive.com";
		data[1][1] = "admin";
		data[1][2] = "MPCs0K@ce1234";
		data[1][3] = false;
				
		return data;	
	}
	
	
	
	@Test
	public void tc1_Negative() {
		System.out.println("STEP - Launch Chrome Browser & Hit url");
		PredefinedActions.start("https://harshaug2022-trials76.orangehrmlive.com");
		
		System.out.println("STEP - Enter valid login credentials");
		LoginPage loginPage = new LoginPage();
		loginPage.login("admin", "MPCs0K@ce111");
		
		System.out.println("VERIFY - home page is displayed");
		String expetedTitle = "OrangeHRM";
		String actualTitle = loginPage.getPageTitle();
		
		Assert.assertEquals(actualTitle, expetedTitle, "Expected title was " + expetedTitle + " but actual title was " + actualTitle);
		
		System.out.println("VERIFY - Retry login page is loaded");
		String expectedUrlContent = "retryLogin";
		String actualCurrentURL = loginPage.getPageURL();
		Assert.assertTrue(actualCurrentURL.endsWith(expectedUrlContent));
		
		System.out.println("Clean up - Close browser");
		PredefinedActions.closeBrowser();
	}
}
