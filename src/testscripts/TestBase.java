package testscripts;

import java.io.IOException;

import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import base.PredefinedActions;
import constant.ConstantValue;
import pages.LoginPage;
import utility.PropertyFileOperations;

public class TestBase {

	@BeforeMethod
	public void setUp() throws IOException {
		PropertyFileOperations fileOperations = new PropertyFileOperations(ConstantValue.CONFIGFILEPATH);
		String url = fileOperations.getValue("url");
		PredefinedActions.start(url);
		
		LoginPage loginPage = LoginPage.getObject();
		loginPage.login(fileOperations.getValue("username"),fileOperations.getValue("password"));
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		if(result.getStatus() == ITestResult.FAILURE) {
			PredefinedActions.takeScreenShot(result.getMethod().getMethodName());
		}
			
		PredefinedActions.closeBrowser();
	}
}
