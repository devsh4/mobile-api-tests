package retailer_app;

import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import app_lib.Utility;
import io.appium.java_client.android.AndroidDriver;

		///@FixMethodOrder(MethodSorters.NAME_ASCENDING)
		public class Testdroid extends BaseTest {
					
			private static final String testdroid_apikey ="RWOxFemlWXGeGo5VTFmSdoBPqmFrR5do";
			private static final String TARGET_APP_PATH = "C:\\Users\\Administrator.pay1lap-71-PC\\Downloads\\app-debug.apk";
		    private static final String TESTDROID_SERVER = "http://appium.testdroid.com";
		    
			private static WebDriver driver;
		    private static WebDriverWait w;

		    @BeforeClass
		    public static void setUp() throws Exception {
		        /*counter = 0;
		        Map<String, String> env = System.getenv();
		        String testdroid_apikey = env.get("TESTDROID_APIKEY");


		        if(StringUtils.isEmpty(testdroid_apikey)) {
		            throw new IllegalArgumentException("Missing TESTDROID_APIKEY environment variable");
		        }*/

		        String fileUUID = uploadFile(TARGET_APP_PATH, TESTDROID_SERVER, testdroid_apikey);

		        DesiredCapabilities capabilities = new DesiredCapabilities();
		        capabilities.setCapability("platformName", "Android");
		        capabilities.setCapability("testdroid_target", "Android");
		        capabilities.setCapability("deviceName", "Android Device");
		        
		        capabilities.setCapability("testdroid_apiKey", testdroid_apikey);
		        
		        capabilities.setCapability("testdroid_project", "Pay1 Test");
		        capabilities.setCapability("testdroid_testrun", "Test Run 1");
		        
		        // See available devices at: https://cloud.testdroid.com/#public/devices
		        capabilities.setCapability("testdroid_device", "LG Google Nexus 5 6.0 -EU"); // Freemium device
		        capabilities.setCapability("testdroid_app", fileUUID); //to use existing app using "latest" as fileUUID

		        // Optional
		        //capabilities.setCapability("testdroid_description", "Appium project description");
		        //capabilities.setCapability("platformVersion", "4.4.2");
		        //capabilities.setCapability("app-activity", ".BitbarSampleApplicationActivity");
		        //capabilities.setCapability("app", "com.bitbar.testdroid");
		        
		        System.out.println("Capabilities:" + capabilities.toString());

		        System.out.println("Creating Appium session, this may take couple minutes..");
		        driver = new AndroidDriver(new URL(TESTDROID_SERVER+"/wd/hub"), capabilities);
		    
		        w = new WebDriverWait(driver,180);
		    
		    }
		
	
		@Test
		public void retailer_Test()
		{
			Utility.captureScreenshot(driver, "One");
			
			//Wait for splash page to go away
			w.until(ExpectedConditions.invisibilityOfElementLocated(By.name("M E R C H A N T")));
			
			System.out.println("Print");
			
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
			
			System.out.println("Print1");
			
			//Navigate to menu 
			driver.findElement(By.id("com.mindsarray.pay1:id/imageView_Menu")).click();
			
			//CLick on notification
			driver.findElement(By.name("NOTIFICATION")).click();
			
			Utility.captureScreenshot(driver, "Two");
					
			//Get all notifications 
			List<WebElement> li=driver.findElements(By.id("com.mindsarray.pay1:id/wrapper"));
			
			System.out.println(li.size());
			
			//Navigate to menu 
			driver.findElement(By.id("com.mindsarray.pay1:id/imageView_Menu")).click();
		
			//GO HOME
			driver.findElement(By.name("HOME")).click();

			Utility.captureScreenshot(driver, "Three");
		}	
		
		
		 @AfterClass
		    public static void tearDown()
		    {
		        if (driver != null) {
		            driver.quit();
		            
		        }
		    }
}
