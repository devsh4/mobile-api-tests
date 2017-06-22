package jio_app;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Jio extends Constants{
	
	//Declaration
	public static AndroidDriver<MobileElement> driver;
	private static	WebDriverWait wait;
	
	//Objects
	static Functions	f	=	new	Functions();
	static GetRequest	g	=	new	GetRequest();
	
	public static void main(String[]	args){
		try{
			setUp();
			login();
			
		}catch(Exception	e)
		{
			System.out.println("Issue occured while setting up!");
			e.printStackTrace();
		}
		
		/*//Instantiate timer task
		TimerTask t	=	new	TimerTask(){
		
		@Override
		public void run(){
			
				System.out.println("Hello!!");	
			
				navigate();
				getRechargeDetails();
				recharge();
				successCheck();
				
			}
		};
		
		Timer timer = new Timer();
	    long delay = 0;
	    
	    //Set interval in milliseconds
	    long intervalPeriod = 1 * 3000; 
	    
	    // schedules the task to be run in an interval 
	    timer.scheduleAtFixedRate(t, delay, intervalPeriod);
				
*/	}
	
	
	public static void setUp(){
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("platformname", "Android");
		cap.setCapability("deviceName", deviceId); 
		cap.setCapability("platformVersion", "6.0.1");
		cap.setCapability("appPackage", "com.ril.rposcentral");
		cap.setCapability("appActivity", "com.ril.rpos.DIBLoginActivity");
		cap.setCapability("newCommandTimeout", false);
		
		try {
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		wait = new WebDriverWait(driver, 180);
	}
	
	
	public	static	void login() {
		
		driver.findElement(By.id("com.ril.rposcentral:id/edtUserName")).clear();

		//Enter login creds
				driver.findElement(By.id("com.ril.rposcentral:id/edtUserName")).sendKeys(username);
				driver.findElement(By.id("com.ril.rposcentral:id/edtPassword")).sendKeys(password);

		driver.hideKeyboard();
		
	//try{
		//Click on login
		driver.findElement(By.id("com.ril.rposcentral:id/btnLogin")).click();

		//Wait
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.ril.rposcentral:id/txtEmpId")));
		
			/*if(!driver.findElements(By.id("com.ril.rposcentral:id/btnRetrieveExistingTxn")).isEmpty()){
			
			driver.findElement(By.id("com.ril.rposcentral:id/btnRetrieveExistingTxn")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("android:id/progress")));
			
			//Select cash
			driver.findElement(By.name("CASH")).click();
		
			//Wait
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.ril.rposcentral:id/btnDone")));
			
			//Select cash
			driver.findElement(By.id("com.ril.rposcentral:id/btnDone")).click();
			
			successCheck();
			}
			
			driver.startActivity("com.android.settings", ".wifi.WifiSettings");
			
			//wait
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.settings:id/switch_widget")));
			
			//Switch it on
			driver.findElement(By.id("com.android.settings:id/switch_widget")).click();
			*/
			driver.startActivity("com.ril.rposcentral", "com.ril.rpos.AccountActivity");
			
			driver.startActivity("com.ril.rposcentral", "com.ril.rpos.GeneralActivity");
			
			driver.startActivity("com.ril.rposcentral", "com.ril.rpos.DashboardActivity");
			
			//client.run("adb shell am start -a android.intent.action.MAIN -n com.android.settings/.wifi.WifiSettings");
			
			System.out.println("DOne");
		/*}
		
		catch(Exception e){
			System.out.println("ISSUE WHILE LOGGING IN!");
		}
		*/
		
	}

	
	public	static	void navigate(){
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.ril.rposcentral:id/btnDrawer1")));
		
		//CLick to open slider
		driver.findElement(By.id("com.ril.rposcentral:id/btnDrawer1")).click();
		
		//Wait
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Jio TopUp/Recharge")));
	
		//Click on topup recharge option
		driver.findElement(By.name("Jio TopUp/Recharge")).click();
		
		//Wait 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.ril.rposcentral:id/edtSearchType")));
	
	}
	
	
	public	static	void	getRechargeDetails(){
		number	=	g.getNumber();
		planAmount	=	g.getAmount();	
	}
	
	
	public	static	void recharge(){
		
		//Wait
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.ril.rposcentral:id/rRecharge")));
		
		//Click on recharge
		driver.findElement(By.id("com.ril.rposcentral:id/rRecharge")).click();
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("android:id/progress")));
		
		//Put number
		driver.findElement(By.id("com.ril.rposcentral:id/edtSearchType")).sendKeys(number);
			
		//Browse plans
		driver.findElement(By.id("com.ril.rposcentral:id/txtBrowsePlan")).click();

		//Wait
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.ril.rposcentral:id/tv_screen_name_plans")));
		
		//Select 1Re
		if(driver.findElements(By.name(planAmount)).isEmpty())
		{
			f.swipeVertically(driver);
			driver.findElement(By.name(planAmount)).click();
		}
		else{
			driver.findElement(By.name(planAmount)).click();
		}
		
		//Call checkBalance method
		f.checkBal(driver);
		
		//CLick on proceed to pay
		driver.findElement(By.id("com.ril.rposcentral:id/btnSubmit")).click();		
		
		//Wait
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("CASH")));
		
		//Select cash
		driver.findElement(By.name("CASH")).click();
	
		//Wait
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.ril.rposcentral:id/btnDone")));
		
		//Select cash
		driver.findElement(By.id("com.ril.rposcentral:id/btnDone")).click();
		
	}

	
	public 	static	void successCheck()
	{
		//Wait until txn is done
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("android:id/progress")));
	
		
		
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Confirmation")));
			
			//Assert receipt
			Assert.assertTrue(!driver.findElements(By.name("Order successfully submitted")).isEmpty());	
			
			//to-do 
			//pASS success status and code to server
			
			//
			String ordNo	=	driver.findElement(By.id("com.ril.rposcentral:id/txtChangeDue")).getText();
			ordNo	=	ordNo.substring(ordNo.indexOf(".")+2);
			
			String msisdn	=	driver.findElement(By.id("com.ril.rposcentral:id/txtCafNo")).getText();
			ordNo	=	ordNo.substring(ordNo.indexOf("N")+2);
			
			System.out.println(ordNo);
			System.out.println(msisdn);   
			driver.findElement(By.id("com.ril.rposcentral:id/btnDone")).click();
			
		}catch(AssertionError e)
		{
			System.out.println("Failure while recharging");
			
			//TO-DO
			//pass failure status and code back to server 
	
		}
		catch(Exception e){
			System.out.println("Failure while recharging");
			//pass failure status and code back to server 
			
		}
	}

}

