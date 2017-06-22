package jio_app;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Functions	extends	Constants{
	
	//VARIABLES
	public	static	Double	balance;
	
	
	
		public	static	void	checkBal(AndroidDriver<MobileElement>	driver){
		
		//Get current balance and convert to double
		String	temp	=	driver.findElement(By.id("com.ril.rposcentral:id/txtViewBalanceAmt")).getText();
		temp	=	temp.replace(",", "");
		balance	=	Double.parseDouble(temp);

		//Converting plan amt to double
		double	plan_amt1	=	Double.parseDouble(planAmount);
		
		//Balance check condition
		if(plan_amt1	<=	balance)
		{
			//System.out.println("Go ahead");
		}
		else{
				System.out.println("Insufficient balance");
				System.exit(0);
			}
			
		}
	
	
			//Swipe vertically function
		public static void swipeVertically(AndroidDriver<MobileElement>	driver) {
				
			Dimension screenSize = driver.manage().window().getSize();
				
			int screenWidth = screenSize.getWidth() / 2;
			int screenHeight = screenSize.getHeight();
			screenHeight = screenHeight * 70 / 100;

				//TRY & CATCH
				try{
					for(int i=0;i<=2;i++)
						{
							driver.swipe(screenWidth, screenHeight, screenWidth, 0, 1000);
						}}
				catch(Exception e)
				{
					e.printStackTrace();
					
					System.out.println("Try swiping again");
					
					for(int i=0;i<=2;i++)
						{
							driver.swipe(screenWidth, screenHeight, screenWidth, 0, 1000);
						}
				}
			}	
}
