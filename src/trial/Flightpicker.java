package trial;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import app_lib.Config;
import io.appium.java_client.android.AndroidDriver;

public class Flightpicker {

	Config c = new Config();
	private AndroidDriver driver;
	private WebDriverWait w;
	
	Dimension size;
	String fare;
	
	public String data[] = {"Delhi"};
	
	@BeforeTest
	public void before()
	{
		driver = c.setUp("com.makemytrip", "com.mmt.travel.app.home.ui.HomeActivity", "03b2902609349ef7");//intex-0123456789ABCDEF,
		w = new WebDriverWait(driver, 120);
	}

	
	@Test(priority=1)
	public void mmt_selectflight() throws InterruptedException
	{
		
		//Wait
		w.until(ExpectedConditions.visibilityOfElementLocated(By.name("MakeMyTrip")));
		
		//Click on flights
		driver.findElement(By.id("com.makemytrip:id/activity_home_launcher_flights_imgvw")).click();
		
		//Wait
		w.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.makemytrip:id/depCity")));
		
		//Click on arrival 
		driver.findElement(By.id("com.makemytrip:id/arrCity")).click();
		
		//Wait
		w.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.makemytrip:id/city_edit_text")));
		
		//Type arr city
		driver.findElement(By.id("com.makemytrip:id/city_edit_text")).sendKeys(data[0]);
		
		//Wait
		w.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.makemytrip:id/cityName")));
		
		//Select airport0
		driver.findElement(By.id("com.makemytrip:id/cityName")).click();
		
		//driver.findElementByAndroidUIAutomator("new UiSelector().index[0]").click();
		if(!driver.findElements(By.id("com.makemytrip:id/crossRoundTrip")).isEmpty())
			{
				//Cancel round trip option
				driver.findElement(By.id("com.makemytrip:id/crossRoundTrip")).click();
			}
		
		//Select date
		driver.findElement(By.id("com.makemytrip:id/depdateID")).click();
		
		//Wait
		w.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.makemytrip:id/calDepartText")));
		
		/*List <WebElement>dates = driver.findElements(By.xpath("//android.widget.TextView[@text='February 2017']/following::android.widget.LinearLayout[@id='com.makemytrip:id/calendar_day_row']/android.widget.CheckedTextView)"));
		
		for(WebElement x:dates)
		{
			System.out.println(x.getText());
		}*/
		//System.out.println("test starts");
		 
		//call swipe function
		swipe();
	
		//list all dates visible
		List <WebElement> l=driver.findElements(By.className("android.widget.CheckedTextView"));
	
		//Select date from list 
		l.get(5).click();
		
		//click ok
		driver.findElement(By.id("com.makemytrip:id/calOK")).click();

		//Wait
		w.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.makemytrip:id/searchFlights")));
		
		//Search flights
		driver.findElement(By.id("com.makemytrip:id/searchFlights")).click();
	
		//Wait
		w.until(ExpectedConditions.invisibilityOfElementLocated(By.id("com.makemytrip:id/progressBar")));
		
		try{
			Assert.assertTrue(driver.findElements(By.id("com.makemytrip:id/flightNotFoundMessage")).isEmpty());
		}
		catch(Exception e)
		{
			org.testng.Assert.fail("No flights found!");
		}
		
		//Set filter
		setFilters();
		
		//Select flight
		driver.findElement(By.xpath("//android.widget.LinearLayout[@index='0']")).click();
			
		//Print flight info
		flightInfo();
		
		driver.quit();
	}

	@Test(priority=2)
	public void goibibo_selectflight() throws InterruptedException
	{
		driver = c.setUp("com.goibibo", "com.goibibo.common.HomeActivity", "03b2902609349ef7");			
		
		//Fetch cheapest fare from goibibo
	}
	
	public void swipe() throws InterruptedException{
		
		//Element
			Dimension dimensions = driver.manage().window().getSize();
			
			Double screenHeightStart = dimensions.getHeight() * 0.8;
			int scrollStart = screenHeightStart.intValue();
			
			Double screenHeightEnd = dimensions.getHeight() * 0.2;
			int scrollEnd = screenHeightEnd.intValue();
			
		/*	System.out.println("s="+scrollStart);
			System.out.println("s="+scrollEnd);*/

			for (int i = 0; i < 1; i++) {
				
				//Scroll down
				driver.swipe(0,scrollStart,0,scrollEnd,1000);
			}
	}

	
	public void setFilters(){
		//Click on filter
		driver.findElement(By.id("com.makemytrip:id/flt_listing_filter")).click();
		
		//Wait
		w.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.makemytrip:id/tabContainer")));
		
		WebElement zerostop = driver.findElement(By.id("com.makemytrip:id/filter_layover_zero_stop"));
		WebElement onestop = driver.findElement(By.id("com.makemytrip:id/filter_layover_one_stop"));
		WebElement twoplusstops = driver.findElement(By.id("com.makemytrip:id/filter_layover_twoplus_stop"));

		//
		if(zerostop.getAttribute("clickable").equals("true"))
		{
			zerostop.click();
		}
		else if(onestop.getAttribute("clickable").equals("true")){
			onestop.click();
		}
		else{
			twoplusstops.click();
		}
		
		//WAIT
		w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("com.makemytrip:id/filter_clear_all")));
		
		//Apply
		driver.findElement(By.id("com.makemytrip:id/rlListingFooter")).click();
		
	}
	
	
	public void flightInfo(){
		
		//wait for flight details to load
		w.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.makemytrip:id/review_fare_footer_image")));
		
		//Initialize arr
		String[] info= new String[20];
		
		//com.makemytrip:id/depday
		
		//Store info in array
		//Fare
		info[0] =	driver.findElement(By.id("com.makemytrip:id/fare_text")).getText();
		fare = info[0].substring(info[0].indexOf("?")+5);
		//Airline name
		info[1] =	driver.findElement(By.id("com.makemytrip:id/airlineName")).getText();
		//Airline number
		info[2] =	driver.findElement(By.id("com.makemytrip:id/airlineCode")).getText();
		//duration
		info[3] =	driver.findElement(By.id("com.makemytrip:id/header_duration")).getText();
		//Stops
		info[4] =	driver.findElement(By.id("com.makemytrip:id/stop")).getText();
		
		//Write info 
		System.out.println(info[1]+" Airlines ** Flight Number: "+info[2]+" "+info[4]);
		System.out.println("Flight Time: "+info[3]+" ** Fare -> Rs. "+fare);
		System.out.println(" ");
	}
	
	
	@AfterTest
	public void after(){
	    
			driver.quit();
	}
	
}
	