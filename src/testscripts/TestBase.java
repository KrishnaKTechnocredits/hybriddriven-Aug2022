package testscripts;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import base.PredefinedActions;
import pages.LoginPage;
import utility.PropertyFileOperations;

public class TestBase {

	@BeforeMethod
	public void setUp() throws IOException {
		PropertyFileOperations fileOperations = new PropertyFileOperations(".//config//settings.properties");
		String url = fileOperations.getValue("url");
		PredefinedActions.start(url);
		
		LoginPage loginPage = new LoginPage();
		loginPage.login(fileOperations.getValue("username"),fileOperations.getValue("password"));
	}
	
	@AfterMethod
	public void tearDown() {
		PredefinedActions.closeBrowser();
	}
}
