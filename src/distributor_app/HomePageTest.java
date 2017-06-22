package distributor_app;

import java.beans.Visibility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import app_lib.Config;
import distributor_app_pageobjects.HomePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class HomePageTest	extends	HomePage {
	
	public static AndroidDriver<MobileElement> driver;
	private static	WebDriverWait wait;
	Config	c	=	new	Config();

	@BeforeTest
	public  void setUp(){
		driver	=	c.getDriver();
		wait = new WebDriverWait(driver, 180);
		
	}
	
	@Test(priority=1)
	public void verifyHomePage() throws InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(balanceButtonXpath)));
		
		driver.findElement(By.xpath(balanceButtonXpath)).isDisplayed();
		driver.findElement(By.xpath(createRetailerButtonXpath)).isDisplayed();
		driver.findElement(By.xpath(retailerListButtonXpath)).isDisplayed();
		driver.findElement(By.xpath(reportButtonXpath)).isDisplayed();
	}

}
