package app_lib;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
/**
 * @author Dev Shah
 */

public class Config {
	
	public static AndroidDriver<MobileElement> driver;
	private WebDriverWait w;
	
	public static String chrome_path = "./Dependencies/chromedriver.exe";
	public static String ff_path = "./Dependencies/geckodriver.exe";
	public static String ie_path = "./Dependencies/IEdriverServer.exe";
	
	//Excel Path
	public static String excelPath="./Dependencies/Test_Data.xlsx";
	
	
	//Pick data from excel - VARIABLES
		public static ExcelConfig excel=new ExcelConfig(Config.excelPath);
	
		public final static String userMobile=excel.getData("App", 1, 1);
		public final static String password=excel.getData("App", 3, 1);
		public final static String recharge_num=excel.getData("App", 8, 1);
		
		//PANEL LOGIN Parameters
		public final static String panel_id=excel.getData("TestData", 7, 1);
		public final static String panel_pwd=excel.getData("TestData", 8, 1);
			
	///////////REUSABLE METHODS	
	
	// LOGIN 
	public void retailerLogin(WebDriver driver, String userMobile, String password){
		
		driver.findElement(By.id("mobile_no")).sendKeys(userMobile);
		driver.findElement(By.id("pin")).sendKeys(password);
		driver.findElement(By.id("login_modal_submit")).click();
	}
	
	//Panel Case
		public void panelLogin(WebDriver driver){
			
			driver.findElement(By.id("userMobile")).sendKeys(panel_id);
			driver.findElement(By.id("userPassword")).sendKeys(panel_pwd);
			driver.findElement(By.xpath(".//*[@id='loginSignIn']/input")).click();
		}

	
		public AndroidDriver<MobileElement> getDriver() {
			
		if(driver	==	null)
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability("platformname", "Android");
			cap.setCapability("deviceName", "LS5002"); 
			cap.setCapability("platformVersion", "6.0.1");
			cap.setCapability("appPackage", "com.mindsarray.pay1distributor");
			cap.setCapability("appActivity", "com.mindsarray.pay1distributor.SplashScreenActivity");
			cap.setCapability("newCommandTimeout", false);
			
			try {
				driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
			return driver;

		}

		
		public static  AndroidDriver<MobileElement> setUp(String packageName,	String activityName,	String deviceId)
		{
				DesiredCapabilities capabilities = new DesiredCapabilities();
				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setCapability("platformname", "Android");
				cap.setCapability("deviceName", deviceId); 
				cap.setCapability("platformVersion", "6.0.1");
				cap.setCapability("appPackage", packageName);
				cap.setCapability("appActivity", activityName);
				cap.setCapability("newCommandTimeout", false);
				
				try {
					driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				return driver;
				
			}
		
	}
		
	
