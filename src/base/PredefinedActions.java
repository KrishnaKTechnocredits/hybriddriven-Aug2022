package base;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import customexceptions.ElementNotEnabledException;

public class PredefinedActions {
	
	protected static WebDriver driver;
	static WebDriverWait wait;
	private static Actions actions;
	
	protected PredefinedActions() {
		
	}
	
	public static void start(String url) {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver_106.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 60);
		actions = new Actions(driver);
	}
	
	protected WebElement getElement(String locatorType, String locatorValue, boolean isWaitRequired) {
		WebElement element = null;
		
		switch(locatorType.toLowerCase()) {
			case "id":
				if(isWaitRequired) {
					element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
				}else
					element = driver.findElement(By.id(locatorValue));
				break;
				
			case "xpath":
				if(isWaitRequired) {
					element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
				}else
					element = driver.findElement(By.xpath(locatorValue));
				break;
				
			case "cssselector":
				if(isWaitRequired) {
					element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
				}else
					element = driver.findElement(By.cssSelector(locatorValue));
				break;
			
			case "name":
				if(isWaitRequired) {
					element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
				}else
					element = driver.findElement(By.name(locatorValue));
				break;	
				
			case "linktext":
				if(isWaitRequired) {
					element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
				}else
					element = driver.findElement(By.linkText(locatorValue));
				break;
				
			case "partiallinktext":
				if(isWaitRequired) {
					element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
				}else
					element = driver.findElement(By.partialLinkText(locatorValue));
				break;
				
			case "classname":
				if(isWaitRequired) {
					element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
				}else
					element = driver.findElement(By.className(locatorValue));
				break;
				
			case "tagname":
				if(isWaitRequired) {
					element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue)));
				}else
					element = driver.findElement(By.tagName(locatorValue));
				break;
		}
		return element;
	}
	
	protected void setText(WebElement e, String text) {
		scrollToElement(e);
		if(e.isEnabled())
			e.sendKeys(text);
		else
			throw new ElementNotEnabledException(text + " can't be entered as ele,ent is not enabled");
	}
	
	protected void setText(String locatorType, String locatorValue, boolean isWaitRequired, String text) {
		
		WebElement e = getElement(locatorType, locatorValue, isWaitRequired);
		if(e.isEnabled())
			e.sendKeys(text);
	}
	
	protected void clickOnElement(WebElement e, boolean isWaitRequiredBeforeClick) {
		scrollToElement(e);
		if(isWaitRequiredBeforeClick) {
			wait.until(ExpectedConditions.elementToBeClickable(e));
			e.click();
		}
		e.click();
	}
	
	protected void scrollToElement(WebElement e) {
		if(!e.isDisplayed()) {
			JavascriptExecutor je = (JavascriptExecutor)driver;
			je.executeScript("arguments[0].scrollIntoView(true)", e);
		}
	}
	
	protected boolean isElementDisplayed(WebElement e) {
		scrollToElement(e);
		return e.isDisplayed();
	}
	
	protected void mouseHoverOnElement(WebElement e) {
		actions.moveToElement(e).build().perform();
	}
	
	protected List<String> getListOfWebElementText(List<WebElement> list) {
		List<String> listOfElementText = new ArrayList<String>();
		for(WebElement e : list) {
			listOfElementText.add(e.getText());
		}
		return listOfElementText;
	}
		
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public String getPageURL() {
		return driver.getCurrentUrl();
	}
	
	public static void closeBrowser() {
		driver.close();
	}
	
}
