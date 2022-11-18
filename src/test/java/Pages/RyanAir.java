package Pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.WaitHelperUtility;

public class RyanAir {
	
	public WaitHelperUtility wait;
	public WebDriver ldriver;
	public RyanAir(WebDriver rdriver)
	{
		ldriver =rdriver;
		PageFactory.initElements( rdriver, this);
	}
	
	
	
	@FindBy(xpath="//ry-tooltip[@id='ry-tooltip-6']//div[@data-id='2022-12-01']")
	WebElement dt;
	
	
	public void setDpartureDate()
	{
		wait = new WaitHelperUtility(ldriver);
		wait.WaitForElement(dt, 3000);
		//WebDriverWait w3 = new WebDriverWait(ldriver, Duration.ofSeconds(3000));
		//w3.until(ExpectedConditions.visibilityOf(dt));
		dt.click();
	}

}
