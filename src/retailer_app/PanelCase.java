package retailer_app;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import app_lib.Config;

public class PanelCase {

	private WebDriverWait w;
	private WebDriver driver;

	Config c=new Config();
	
	@BeforeTest
	public void setUp(){
		System.setProperty("webdriver.chrome.driver", Config.chrome_path);
		driver=new ChromeDriver();
		
		driver.get("http://cc.pay1.in");
		
		//Login
		c.panelLogin(driver);
		
		driver.manage().window().maximize();
		
		//Wait
		w=new WebDriverWait(driver,30);
		w.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Logout")));	
	}
	
	@Test
	public void test(){
		
		//Navigate
		driver.get("http://cc.pay1.in/panels/userInfo/9833887517");

		//Scroll
		WebElement e=driver.findElement(By.xpath("//th[contains(text(),'Transaction Id')]"));
		
		System.out.println("Scroll");
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", e);

		System.out.println("Scroll done");
		try{
		//Take screenshot
		File img=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(img, new File("./Screenshots/"+System.currentTimeMillis()+".png"));
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	@AfterTest
	public void breakdown(){
	
	driver.quit();
	}
}
