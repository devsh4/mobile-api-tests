package distributor_app;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import app_lib.Config;
import distributor_app_pageobjects.BalanceTransferPage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class BalanceTransferTest	extends	BalanceTransferPage	{

	public static AndroidDriver<MobileElement> driver;
	private static	WebDriverWait wait;
	Config	c	=	new	Config();
	
	String balance;
	String txnId;
	
	@BeforeTest
	public  void setUp(){
		driver	=	c.getDriver();
		wait = new WebDriverWait(driver, 60);

	}
	
	
	@Test(priority=0)
	public void getBalance(){
		
		//Click on side drawer
		driver.findElement(By.id(sidepanelId)).click();
		
		//Get balance and store in global varibale
		balance	=	driver.findElement(By.id(balanceId)).getText();
		balance	=	balance.substring(balance.indexOf("Rs.")+4);
		
		//
		System.out.println(balance);
		
	}
	
	
	@Test(priority=1)
	public void retailerTransfer(){
		
		//Close side menu
		driver.findElement(By.id(sidepanelId)).click();
		
		//Wait
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(balanceButtonXpath)));
		
		//Click on transfer button
		driver.findElement(By.xpath(balanceButtonXpath)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(mobileId)));
		
		//Enter mobile, amount and note
		driver.findElement(By.id(mobileId)).sendKeys("000");
		
		driver.findElement(By.id("com.mindsarray.pay1distributor:id/balance_layout")).click();
		
		/*driver.findElement(By.id(amountId)).sendKeys("1");
		
		//Hide keyboard
		driver.hideKeyboard();

		//Click on confirm
		driver.findElement(By.id("com.mindsarray.pay1distributor:id/transfer_confirm_button")).click();

		//wait
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(transactionIdMessage)));
		getTransactionId();*/
		
		System.out.println("DONE!");
	}
	

	
	@Test(priority=2)
	public void salesmanTransfer(){
		
	}
	
	
	public void getTransactionId(){
		
		//transaction id
		txnId	=	driver.findElement(By.id(transactionIdMessage)).getText();
		txnId	=	txnId.substring(txnId.indexOf("Id is"+6));
		
		//Click on ok
		driver.findElement(By.name("OK")).click();
	}
	
}
