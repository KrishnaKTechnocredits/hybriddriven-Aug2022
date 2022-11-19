package testscripts;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RetryDemo {

	static int a = 0;

	@Test
	public void m1() {
		if (a == 1)
			System.out.println("Hi....");
		else {
			System.out.println(a);
			a++;
			Assert.fail();
		}
	}
}
