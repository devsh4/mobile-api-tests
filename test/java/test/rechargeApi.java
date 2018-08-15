package test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

import lib.ExcelConfig;
import lib.WriteExcel;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.util.HashMap;
import java.util.List;

public class rechargeApi {
	
		public	static	String num	=	"9833887517";
		public	static	int[] recharge_amt	=	{10,11,12,13,14};
		public	static	String	path	=	"C:\\Users\\Administrator.pay1lap-71-PC\\Desktop\\Retailer.xlsx";
		public	static	String	sheetName	=	"Sheet1";
		ExcelConfig e	=	new	ExcelConfig(path);  

		String	sessionId;
		Response response;
		
		
	  @Test(priority=1)
	  public void setUp(){
		  
		  RestAssured.baseURI	=	"http://xxxxxxxxxxxxxxxx";
		 
		 //Method to get and store sessionId 
		  getCookies();
		 
	  }
		
	 //@Test(priority=2)
	  public void recharge() {
		  
		 System.out.println("*****RECHARGE*****");
		  //Send recharge requests from all vmn's 
		 	for(int i=1;i<=5;i++)
		 	{
			 Response	resp	= given().
			  	header("Cookie","CAKEPHP="+e.getData(sheetName, i, 1)+"; _gat=1; _ga=GA1.2.1761097415.1494246426; _gid=GA1.2.1343008270.1494254695").
	  			parameters("method","mobRecharge").
	  			parameters("mobileNumber","9833887515").
	  			parameters("amount","6").
	  			parameters("operator","15").
	  			parameters("subId","9833887515").
	  			parameters("special","0").
	  			parameters("type","flexi").
	  			parameters("latitude","0").
	  			parameters("longitude","0").
	  			parameters("device_type","web").
	  			parameters("mode","test").
			  when().
			  	get("/apis/receiveWeb/mindsarray/mindsarray/json").
			  then().
			  	extract().response();
			  
			 System.out.println(e.getData(sheetName, i, 1));
			 //Print response
			  String	respAsString	=	resp.asString();
			  System.out.println(respAsString);
		 	}
	 }
	 
	 
	  
	  
	 public	void	getCookies(){

		 for(int	i=0;i<3;i++)
		  {
			  try{
				  //LOGIN
				  response	= given().
					  			parameters("method","authenticate_New").
					  			parameters("uuid","2701070875").
					  			parameters("mobile",e.getrawData(sheetName, i, 0)).
					  			parameters("password","xxxxx").
					  			parameters("latitude","19.1842585").
					  			parameters("longitude","72.8319192").
					  			parameters("device_type","web").
					  			parameters("mode","test").
					  when().
					  			get("/apis/receiveWeb/mindsarray/mindsarray/json").
					  then().
					  			extract().response();

				  //Print response
				  String	resp	=	response.asString();
				  System.out.println(resp);
		  	 
				  //Print sessionId
				  sessionId	=	resp.substring(resp.indexOf("CAKEPHP") + 10, resp.length()-5);
				  System.out.println(sessionId);
		  	
				  //Write sessionId to excel
				  WriteExcel ew	=	new	WriteExcel(path,sheetName);
				  ew.writeData(path, sheetName, i, 1, sessionId);
			  }
			  catch(Exception ee)
			  {
				  ee.printStackTrace();
				  System.out.println("UUID doesn't match!");
			  }
		  	
		  }
	
	 }
	 
}
