package testscripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.DashboardPage;

public class DashboardTest extends TestBase{
	
	@Test
	public void verifyWidgetsCountAndText() throws IOException {
		DashboardPage dashboardPage = new DashboardPage();
		
		System.out.println("VERIFY - Number of widgets on dashboard page");
		int totalWidgets = dashboardPage.getNumberOfWidgets();
		Assert.assertEquals(totalWidgets, 9, "totalWidegets was not displayed as expected, expected was 9, actual widgets displayed "+ totalWidgets);
		
		List<String> listOfExpectedWidgetsText = new ArrayList<String>(
				Arrays.asList("Buzz Latest Posts","Quick Access", "My Actions",
						"Latest Documents", "Latest News", "Employees on Leave Today",
						"Time At Work", "Headcount by Location", "COVID-19 Report"));
		
		System.out.println("STEP - Get list of all widgets text");
		List<String> listOfActualWidgetsText = dashboardPage.getAllWidgetsText();
	
		System.out.println("VERIFY - text of all widgets");
		Assert.assertEquals(listOfActualWidgetsText, listOfExpectedWidgetsText);
	}
	
}
