package retailer_app;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import app_lib.Config;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

/**
 * 
 * @author Dev Shah
 * @description Retailer App Test
 */
public class Recharge {
	
	private WebDriver driver;
	private WebDriverWait w;
	
	Config c=new Config();
	String temp;
	String txn_id;
	
	@BeforeTest
	public  void pretest() throws MalformedURLException{
		
		driver=c.setUp("com.mindsarray.pay1","com.mindsarray.pay1.SplashScreenActivity", "0123456789ABCDEF");
		
		//Wait
		w =new WebDriverWait(driver,180);
		
	}
	
	@Test(priority=0, enabled=false)
	public void login(){
	
		try{
		
		//Navigate to Menu
		driver.findElement(By.id("com.mindsarray.pay1:id/imageView_Menu")).click();

		w.until(ExpectedConditions.presenceOfElementLocated(By.id("com.mindsarray.pay1:id/textView_MenuName")));
		}
		catch(Exception e){
			
		}
		
		
		//GO TO LOGIN 
		try{
		//Click on login option from menu
		driver.findElement(By.name("LOGIN")).click();
		
		//Wait
		w.until(ExpectedConditions.presenceOfElementLocated(By.id("com.mindsarray.pay1:id/editPin")));
		}
		catch(Exception e){
			org.testng.Assert.fail("Error occured while navigating to login activity");	
		}
		
		//LOG IN
		
			
		//Send USER ID
		driver.findElement(By.id("com.mindsarray.pay1:id/editMobileNumber")).clear();
		driver.findElement(By.id("com.mindsarray.pay1:id/editMobileNumber")).sendKeys(Config.userMobile);
			
		//To hide the keyboard
		driver.navigate().back();
	
		//Send password
		driver.findElement(By.id("com.mindsarray.pay1:id/editPin")).sendKeys(Config.password);
		
/*		try {
		     e.click();  
		     new ProcessBuilder(new String[]{"adb", "-s", "03b2902609349ef7", "shell", "input", "text", "12345"})
		       .redirectErrorStream(true)
		       .start();
		} catch (IOException e1) {
		   e1.printStackTrace();
		}
		*/
		//To hide the keyboard
		driver.navigate().back();
				
		//Click on login
		driver.findElement(By.id("com.mindsarray.pay1:id/mBtnLogin")).click();
		
		//Check location services
		if(driver.findElements(By.name("On")).isEmpty()){
			//Wait for logging in
			w.until(ExpectedConditions.presenceOfElementLocated(By.id("com.mindsarray.pay1:id/balance_header")));
		}
		else{
			//Click ON for location services popup
			driver.findElement(By.name("On")).click();
			
			//Wait till toggle switch appears
			w.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.settings:id/switch_widget")));
			
			//Switch on location
			driver.findElement(By.id("com.android.settings:id/switch_widget")).click();
			
			//Go back
			driver.navigate().back();
			
			//Click on login
			w.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.mindsarray.pay1:id/mBtnLogin")));
			driver.findElement(By.id("com.mindsarray.pay1:id/mBtnLogin")).click();
		}
		}
	/*	catch(Exception e){
			org.testng.Assert.fail("Error occured while TRYING to log into the application");	
		}*/
	
	
	@Test(priority=1, enabled=false)
	public void recharge(){
		//
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!driver.findElements(By.name("Cancel")).isEmpty())
		{
		driver.findElement(By.name("Cancel")).click();
		}
		
		/////////////////////////////////////////
		//Display balance
		System.out.println(driver.findElement(By.id("com.mindsarray.pay1:id/textBalance")).getText());
		
		//Or
/*		driver.findElement(By.xpath("//android.widget.LinearLayout[@index='0']/android.widget.RelativeLayout[@index='0']/android.widget.ImageView")).click();
		
		driver.navigate().back();*/
		
		//Verify all recharge options are present
		for(int i=0;i<=9;i++)
		{
			driver.findElement(By.xpath("//android.widget.LinearLayout[@index='"+i+"']/android.widget.RelativeLayout[@index='0']/android.widget.ImageView")).isDisplayed();
		}
		
	
		//Go to recharge
		driver.findElement(By.name("Mobile Recharge")).click();
		
		//Verify all elements exist
		for(int i=0;i<=12;i++)
		{
			driver.findElement(By.xpath("//android.widget.LinearLayout[@index='"+i+"']/android.widget.RelativeLayout[@index='0']/android.widget.ImageView")).isDisplayed();
		}

		//Recharge
		for(int i=0;i<=2;i++)
		{
			driver.findElement(By.xpath("//android.widget.LinearLayout[@index='"+i+"']/android.widget.RelativeLayout[@index='0']/android.widget.ImageView")).click();
			 
			w.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.mindsarray.pay1:id/editMobileNumber")));
			
			//Enter number 
			driver.findElement(By.id("com.mindsarray.pay1:id/editMobileNumber")).sendKeys(Config.recharge_num);
			driver.navigate().back();
			
			//Enter password
			driver.findElement(By.id("com.mindsarray.pay1:id/editRechargeAmount")).sendKeys("6");
			driver.navigate().back();
			
			//Transfer
			driver.findElement(By.id("com.mindsarray.pay1:id/mBtnRchNow")).click();
			
			//Confirm
			driver.findElement(By.name("Yes")).click();
			
			//Wait
			w.until(ExpectedConditions.invisibilityOfElementLocated(By.id("android:id/progress")));
	
			//Reconfirm wrong operator
			if(driver.findElements(By.id("com.mindsarray.pay1:id/textViewOperatorName1")).isEmpty())
			{
				//TO-DO
			}
			else{
				driver.findElement(By.id("com.mindsarray.pay1:id/textViewOperatorName1")).click();
			}
			//Wait
			w.until(ExpectedConditions.invisibilityOfElementLocated(By.id("android:id/progress")));
			
			temp = driver.findElement(By.id("com.mindsarray.pay1:id/textView_Message")).getText();
			
			String txn_id=temp.substring(53);//temp.indexOf(":"+2),temp.indexOf(":"+12)
			
			System.out.println(txn_id);
			
			driver.findElement(By.name("OK")).click();
		}

	}


	@Test(priority=2, enabled=false)
	public void trans_history(){
		//Go to recharge
		driver.findElement(By.name("Mobile Recharge")).click();
				
		driver.findElement(By.name("TRANSACTION HISTORY")).click();
		
		//Wait
		w.until(ExpectedConditions.invisibilityOfElementLocated(By.id("android:id/progress")));
		w.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.mindsarray.pay1:id/editMobileNum")));
		
		//Enter number
		driver.findElement(By.id("com.mindsarray.pay1:id/editMobileNum")).sendKeys(Config.recharge_num);
		
		driver.navigate().back();
		//Search
		driver.findElement(By.id("com.mindsarray.pay1:id/btnSearch")).click();
		
		w.until(ExpectedConditions.invisibilityOfElementLocated(By.id("android:id/progress")));
		
		//REVERT TRANSACTION
		driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'Txn Id: "+txn_id+"')]"));
		
		///following::android.widget.TextView[@id='com.mindsarray.pay1:id/AmountText']")).getText()
		
		driver.findElement(By.id("com.mindsarray.pay1:id/imageComplaint")).click();
		w.until(ExpectedConditions.invisibilityOfElementLocated(By.id("com.mindsarray.pay1:id/complaint_spinner")));
		
		driver.findElement(By.id("com.mindsarray.pay1:id/complaint_spinner")).click();
		
		driver.findElement(By.name("Wrong Operator Recharge")).click();
		
		driver.findElement(By.name("OK"));
		
		w.until(ExpectedConditions.invisibilityOfElementLocated(By.id("android:id/progress")));
		driver.findElement(By.name("OK"));
		///
		
		driver.findElement(By.xpath("//android.widget.LinearLayout[@index='0']")).isDisplayed();
		driver.findElement(By.xpath("//android.widget.LinearLayout[@index='1']")).isDisplayed();
		driver.findElement(By.xpath("//android.widget.LinearLayout[@index='2']")).isDisplayed();
				
	}

	
	@Test(priority=3, enabled=true)
	public void complaint_status(){
		//Navigate to complaint status activity
		driver.findElement(By.name("COMPLAINT STATUS")).click();
		
		//Verify elements exist
		driver.findElement(By.id("com.mindsarray.pay1:id/mDateDisplay"));
		driver.findElement(By.id("com.mindsarray.pay1:id/btnPreWeek")).click();
		
		//Go back to recharge page
		driver.navigate().back();
		
	}
	
	/*	
 //id("android:id/progress"); -> loading indicator
  
	@AfterTest
	public void breakdown(){

		//Logout
		driver.findElement(By.id("com.mindsarray.pay1:id/imageView_Menu")).click();
		driver.findElement(By.name("LOGOUT")).click();
		
		//Confirm
		driver.findElement(By.name("Yes")).click();
		
		//Close app
		((AppiumDriver)driver).closeApp();
		
		System.out.println("TEST CASE RUN ---- Complete");
		
		}*/
}
