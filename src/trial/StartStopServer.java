package trial;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class StartStopServer{
		
		AppiumDriverLocalService service;
	
		public AndroidDriver<MobileElement> driver;
		public DesiredCapabilities cap;	
		
		/*public static void main()
		{
			setUp();
			test();
			finish();
			System.out.println("Done!!!!");
			
		}*/
		
		@BeforeTest
		public void setUp(){
			service	=	AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
					.usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
					.withAppiumJS(new File("C:\\Program Files (x86)\\Appium\\node_modules\\appium\\bin\\appium.js"))
					.withIPAddress("127.0.0.1")
					.usingPort(4723));
		}
		
		@Test
		public void test() throws InterruptedException{
			service.start();
			Thread.sleep(5000);
			
	        DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability("platformname", "Android");
			cap.setCapability("deviceName", "192.168.3.117:5555");
			cap.setCapability("platformVersion", "6.0.1"); 
			cap.setCapability("appPackage", "com.whatsapp");
			cap.setCapability("appActivity", "com.whatsapp.HomeActivity");
			
			try {
				driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		@AfterTest
		public void finish()
		{
			service.stop();
		}
		
	}