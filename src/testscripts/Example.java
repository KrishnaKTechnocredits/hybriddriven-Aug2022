package testscripts;

public class Example {

	String locator = "//div[@id='%s']//a[text()='%s']";
	
	void m1(String id,String name) {
		String output = String.format(locator, id, name);
		System.out.println(output);
	}
	
	
	public static void main(String[] args) {
		new Example().m1("id1","Cancel");
	}
}
