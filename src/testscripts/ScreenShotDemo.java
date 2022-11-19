package testscripts;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.PredefinedActions;
import pages.LoginPage;
import utility.PropertyFileOperations;

public class ScreenShotDemo {

	WebDriver driver;

	@BeforeMethod
	public void setUp() throws IOException {
		PropertyFileOperations fileOperations = new PropertyFileOperations(".//config//settings.properties");
		String url = fileOperations.getValue("url");
		driver = PredefinedActions.startTemp(url);

		LoginPage loginPage = LoginPage.getObject();
		loginPage.login(fileOperations.getValue("username"), fileOperations.getValue("password"));
	}

	@Test
	public void m1() throws IOException {
		
	}

	@AfterMethod
	public void tearDown() {
		PredefinedActions.closeBrowser();
	}

}
