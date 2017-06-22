package retailer_app;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import app_lib.Config;


public class MenuOptions {
	private WebDriver driver;
	private WebDriverWait w;
	
	Config c=new Config();
	
	@BeforeTest
	public void beforeTest()
	{
		driver=c.setUp("com.mindsarray.pay1","com.mindsarray.pay1.SplashScreenActivity", "0123456789ABCDEF");
		  
		//Wait
		w =new WebDriverWait(driver,180);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!driver.findElements(By.name("Cancel")).isEmpty())
		{
		driver.findElement(By.name("Cancel")).click();
		}
	}
	
	@Test(priority=1, enabled=true)
	public void Notification(){		
				
		//Navigate to menu 
		driver.findElement(By.id("com.mindsarray.pay1:id/imageView_Menu")).click();
		
		//CLick on notification
		driver.findElement(By.name("NOTIFICATION")).click();
		
		//Get time of latest notification
		//System.out.println(driver.findElement(By.xpath("//android.widget.LinearLayout[@id='com.mindsarray.pay1:id/wrapper']/android.widget.TextView[@id='com.mindsarray.pay1:id/textViewTime']")).getLocation());
	
		//Get all notifications 
		List<WebElement> li=driver.findElements(By.id("com.mindsarray.pay1:id/wrapper"));
		
		System.out.println(li.size());
		
		//Navigate to menu 
		driver.findElement(By.id("com.mindsarray.pay1:id/imageView_Menu")).click();
	
		//GO HOME
		driver.findElement(By.name("HOME")).click();

	}
	
	@Test(priority=2, enabled=true) 
	public void Tollfree(){
		
		//CLick on toll free option
		driver.findElement(By.name("TOLL-FREE CALL")).click();

		//WAIT
		w.until(ExpectedConditions.visibilityOfElementLocated(By.name("CANCEL")));
		
		//CLICK
		driver.findElement(By.name("CANCEL")).click();
		
		//ANOTHER WAY
		driver.findElement(By.id("com.mindsarray.pay1:id/iconCall")).click();
	
		driver.findElement(By.name("CANCEL")).click();
	}

	@Test(priority=3, enabled=true) 
	public void Support(){
		
		//Navigate to menu 
		driver.findElement(By.id("com.mindsarray.pay1:id/imageView_Menu")).click();
	
		//GO HOME
		driver.findElement(By.name("SUPPORT")).click();
		
		//DISPLAYED verification
		driver.findElement(By.id("com.mindsarray.pay1:id/supportText"));
		
		//Navigate to chat
		driver.findElement(By.name("Chat Support")).click();
		
		//Wait
		w.until(ExpectedConditions.invisibilityOfElementLocated(By.id("android:id/progress")));

		//SendChat
		driver.findElement(By.id("com.mindsarray.pay1:id/message")).sendKeys("Hello");
		
		//driver.findElement(By.id("com.mindsarray.pay1:id/send")).click();
		
		//Back
		driver.navigate().back();
		driver.navigate().back();
		
		//Navigate to email 
		driver.findElement(By.name("Simply write to us on help@pay1.in"));
		
		//Back
		driver.navigate().back();
		
		//Call support
		//driver.findElement(By.xpath("//android.widget.LinearLayout[@index='2']/android.widget.ImageView[@id='com.mindsarray.pay1:id/supportImage']"));
	}
	
}

