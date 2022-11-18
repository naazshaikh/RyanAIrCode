package Test;

import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Pages.RyanAir;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.WaitHelperUtility;

public class RyanAirPay {
	public static void main(String args[]) throws Exception {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		driver.get("https://www.ryanair.com/ie/en");

		driver.manage().window().maximize();
		Thread.sleep(1000);

		System.out.println(driver.getTitle());

		// ####################### privacy window accept
		// cookies#####################################################
		WebElement privacywin = driver.findElement(By.xpath("//button[contains(text(),'Yes, I agree')]"));
		if (privacywin.isDisplayed()) {
			privacywin.click();
		}
		// Selecting oneway option
		driver.findElement(By.cssSelector("[aria-label='One way']")).click();

		// clicking on From/departure textbox //fsw-input-button[@uniqueid='departure']
		WebElement departure = driver.findElement(By.id("input-button__departure"));
		Actions act = new Actions(driver);
		act.moveToElement(departure).click().perform();

		// Wait for countries
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(3000));
		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ry-tooltip[@id='ry-tooltip-1']")));
		// clear selection link
		WebElement lnklearSelection = driver.findElement(By.xpath("//button[contains(text(),'Clear selection')]"));
		lnklearSelection.click();

		WebElement originCountry = driver.findElement(By.xpath("//span[normalize-space()='Ireland']"));
		originCountry.click();

		WebElement originAirport = driver.findElement(By.xpath("//span[contains(text(),'Dublin')]"));
		originAirport.click();

		// ###################################################################################################

		// WebElement destination =
		// driver.findElement(By.id("input-button__destination"));
		// Actions act1 = new Actions(driver);
		// act1.moveToElement(destination).click().perform();
		Actions act1 = new Actions(driver);

		WebElement destination = driver.findElement(By.xpath("//ry-tooltip[@id='ry-tooltip-3']"));
		act1.moveToElement(destination).perform();

		WebElement destCountry = driver.findElement(By.xpath("//span[normalize-space()='Germany']"));
		// destCountry.click();
		act1.moveToElement(destCountry).click().perform();

		WebElement destAirport = driver.findElement(By.xpath("//span[contains(text(),'Berlin Brandenburg')]"));
		// destAirport.click();
		act1.moveToElement(destAirport).click().perform();

		// WebDriverWait w2 = new WebDriverWait(driver, Duration.ofSeconds(3000));
		// w2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ry-tooltip[@id='ry-tooltip-3']")));

		// #######################################################################################
		WebElement dateFrom = driver.findElement(By.xpath("//fsw-input-button[@uniqueid='dates-from']"));

		Actions act2 = new Actions(driver);
		act2.moveToElement(dateFrom).click().perform();

		// WebElement date = driver.findElement(By.id("ry-tooltip-6"));
		// WebElement dt =
		// driver.findElement(By.xpath("//ry-tooltip[@id='ry-tooltip-6']//div[@data-id='2022-12-01']"));
		// WebDriverWait w3 = new WebDriverWait(driver, Duration.ofSeconds(3000));
		// w3.until(ExpectedConditions.visibilityOf(dt));
		// dt.click();

		RyanAir ra = new RyanAir(driver);
		ra.setDpartureDate();

		// driver.findElement(By.xpath("//div[@data-id='2022-12-01']"));
//#############################################################################################################

		WebElement noOfPeople = driver.findElement(By.xpath("//div[contains(text(),'1 Adult')]"));
		WebDriverWait w4 = new WebDriverWait(driver, Duration.ofSeconds(3000));
		w4.until(ExpectedConditions.visibilityOf(noOfPeople));
		Actions act3 = new Actions(driver);

		act3.moveToElement(noOfPeople).perform();

		WebElement adults = driver.findElement(By.xpath("//ry-counter-button[@aria-label='1Adults+1']"));
		act3.moveToElement(adults).click().perform();
		WebElement child = driver.findElement(By.xpath("//ry-counter-button[@aria-label='0Children+1']"));
		act3.moveToElement(child).click().perform();

		driver.findElement(By.cssSelector("button[aria-label='Done']")).click();

//####################################################################################################################

		driver.findElement(By.cssSelector("button[aria-label='Search']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));

		// Assert.assertTrue(driver.getPageSource().contains(" Dublin to Berlin
		// Brandenburg "));

		WebElement txt = driver.findElement(By.tagName("h3"));
		WaitHelperUtility wait = new WaitHelperUtility(driver);
		wait.WaitForElement(txt, 500);
		System.out.println("FLIGHT DETAILS :" + txt.getText());

		driver.findElement(By.xpath("(//button[normalize-space()='Select'])[1]")).click();

		WebElement flightNo = driver.findElement(By.xpath("//div[@class='card-flight-num__content ng-tns-c163-11']"));
		System.out.println("FLIGHT NUMBER" + flightNo.getText());
		WebElement regular = driver.findElement(By.xpath("(//div[@class='fare-card__footer'])[2]"));

		wait.WaitForElement(regular, 500);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", regular);
		regular.click();

//######################################################################
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));

		System.out.println(driver.getTitle());
		driver.findElement(By.xpath("//span[@class='login-touchpoint__login-later h3']")).click();

		WebElement btnContinue = driver.findElement(By.xpath("(//button[normalize-space()='Continue'])"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", btnContinue);

		// FIRST PASSEMGER ADULT

		WebElement titleDrpDn = driver.findElement(By.xpath("(//div[@class='dropdown b2'])[1]"));
		titleDrpDn.click();
		driver.findElement(By.xpath("(//ry-dropdown-item[@class='ng-star-inserted'])[2]")).click();
		driver.findElement(By.id("form.passengers.ADT-0.name")).sendKeys("Jennifer");
		driver.findElement(By.id("form.passengers.ADT-0.surname")).sendKeys("Smith");

		// SECOND PASSENGER ADULT
		WebElement titleDrpDn1 = driver.findElement(By.xpath("(//div[@class='dropdown b2'])[2]"));
		titleDrpDn1.click();
		driver.findElement(By.xpath("(//ry-dropdown-item[@class='ng-star-inserted'])[1]")).click();
		driver.findElement(By.id("form.passengers.ADT-1.name")).sendKeys("Jone");
		driver.findElement(By.id("form.passengers.ADT-1.surname")).sendKeys("Smith");

		// THIRD PASSENGER CHILD
		driver.findElement(By.id("form.passengers.CHD-0.name")).sendKeys("Jenny");
		driver.findElement(By.id("form.passengers.CHD-0.surname")).sendKeys("Smith");
		btnContinue.click();

		// ###################################################################
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(500));
		driver.findElement(By.xpath("//button[normalize-space()='Okay, got it.']")).click();

		List<WebElement> seats = driver.findElements(By.xpath("//button[@class='ng-star-inserted seatmap__seat seatmap__seat--standard']"));
		System.out.println("SEATS NUMBER "+seats.size());
		int count = 0;
		//int seatmap [][] = new seatmap [50][50]


		for (int i = 0; i < seats.size(); i++) 
		{
			String seatNum = seats.get(i).getAttribute("id");
			System.out.println("SEATS " +seatNum);
			System.out.println(seatNum.length());
			System.out.println(seatNum.substring(5, seatNum.length()-1));
			
			if(seatNum.contains("A"))
			{
				
				
			}
				
			

		}

		/*
		 * driver.findElement(By.xpath("//button[@id='seat-10A']")).click();
		 * driver.findElement(By.xpath("//button[@id='seat-10B']")).click();
		 * driver.findElement(By.xpath("//button[@id='seat-10C']")).click(); WebElement
		 * btnContinue1 =
		 * driver.findElement(By.xpath("(//button[normalize-space()='Continue'])"));
		 * 
		 * btnContinue1.click(); WebElement lnk =
		 * driver.findElement(By.xpath("//button[normalize-space()='No, thanks']"));
		 * lnk.click();
		 */

	}

}
