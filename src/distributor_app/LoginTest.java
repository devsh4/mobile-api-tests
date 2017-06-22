package distributor_app;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import distributor_app_pageobjects.LoginPage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import app_lib.Config;

public class LoginTest extends LoginPage{
	public static AndroidDriver<MobileElement> driver;
	private static	WebDriverWait wait;
	Config	c	=	new	Config();
	
	@BeforeTest
	public  void setUp(){
		driver	=	c.getDriver();
		wait = new WebDriverWait(driver, 180);
		
	}
	
	
	@Test(priority=0)
	public void Login(){
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(menuId)));
		
		//Click on drawer
		driver.findElement(By.id(menuId)).click();
		
		if(!driver.findElements(By.name(loginTextView)).isEmpty())
			{
				//Click on login
				driver.findElement(By.name(loginTextView)).click();
		
				//Wait
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(usernameId)));
		
				//Login with credentials
				driver.findElement(By.id(usernameId)).sendKeys("7101000501");
				driver.findElement(By.id(passwordId)).sendKeys("asdf1234");
		
				driver.hideKeyboard();
		
				//Click on login
				driver.findElement(By.id(signInButtonId)).click();
		
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(imageButton)));
			}
		else{
				driver.findElement(By.id(menuId)).click();
				System.out.println("Already Logged In");
			}
	}	
	
	
}
