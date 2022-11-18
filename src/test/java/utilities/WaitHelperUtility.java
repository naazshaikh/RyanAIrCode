package utilities;

import java.time.Duration;

import org.apache.tools.ant.UnknownElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelperUtility {

	public WebDriver driver ; 
	
	public WaitHelperUtility(WebDriver driver) {
		this.driver=driver;
		
	}
	
	public void WaitForElement(WebElement element,long timeOutInSeconds)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
}
