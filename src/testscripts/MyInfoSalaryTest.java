package testscripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.DashboardPage.Menu;
import pages.MyInfoPage;
import pages.MyInfo_SalaryPage;
import pages.MyInfoPage.MyInfoMenu;

public class MyInfoSalaryTest extends TestBase{
	
	@Test
	public void verifyCtc() {
		DashboardPage dashboardPage = DashboardPage.getObject();
		dashboardPage.gotoMenu(Menu.MYINFO);
		MyInfoPage myInfoPage = MyInfoPage.getObject();
		myInfoPage.gotoMenu(MyInfoMenu.SALARY);
		MyInfo_SalaryPage salaryPage = MyInfo_SalaryPage.getObject();
		String ctc = salaryPage.getCostToCompany();
		Assert.assertTrue(ctc.startsWith("$"), "Actual ctc displayed as : " + ctc);
		ctc = ctc.replace("$", "").replace(",", "");
		System.out.println(ctc);
		double d = Double.parseDouble(ctc);
		Assert.assertTrue(d>0, "ctc value was : " + d);
	}
}
