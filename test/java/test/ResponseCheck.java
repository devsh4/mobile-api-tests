package test;

import org.testng.Assert;
import static com.jayway.restassured.RestAssured.get;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.json.JSONArray;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.*;


public class ResponseCheck {
	public static Response res;
	public static Response response;
	public static String jsonAsString;
	
	@BeforeTest()
	public void setup()
	{
		
		res= 
  		given().
  			parameters("root","jQuery1110006031381744798159_1488979198517").
  			parameters("method","authenticate_new").
  			parameters("uuid","3290411630").
  			parameters("mobile","9819042543").
  			parameters("limit","0").
  			parameters("password","12345").
  			parameters("gcm_reg_id","3290411630").
  			parameters("latitude","0").
  			parameters("longitude","0").
  			parameters("otp_via_call","0").
  			parameters("device_type","web").
  			parameters("mode","test").
  			parameters("_","1488979198519").
  			
  		when().
  			//Call api to request for loan 
  			//params - userid, loanamt & token
  			get("http://staging.pay1.in/apis/receiveWeb/mindsarray/mindsarray"). 
  		then().
  		extract().
  			response();
		
		System.out.println(res.asString());
		System.out.println("wait");
	}
	
	
	@Test
	public static void accHistory()
	{			
		//GET request
			response = 
			  		given().
			  			parameters("root","jQuery1110006031381744798159_1488979198517").
			  			parameters("method","ledgerBalance").
			  			parameters("date","08032017-08032017").
			  			parameters("page","0").
			  			parameters("limit","0").
			  			parameters("is_page_wise","false").
			  			parameters("device_type","web").
			  			parameters("_","1488979198519").
			  			
			  		when().
			  			//Call api to request for loan 
			  			//params - userid, loanamt & token
			  			get("http://staging.pay1.in/apis/receiveWeb/mindsarray/mindsarray"). 
			  		then().
			  		extract().
			  			response();
			
			String response1	=	response.asString();
		
			response = 
			  		given().
			  			parameters("root","jQuery1110006031381744798159_1488979198517").
			  			parameters("method","ledgerBalance").
			  			parameters("date","08032017-08032017").
			  			parameters("page","0").
			  			parameters("limit","0").
			  			parameters("is_page_wise","false").
			  			parameters("device_type","web").
			  			parameters("_","1488979198519").
			  		when().
			  			//Call api to request for loan 
			  			//params - userid, loanamt & token
			  			get("http://staging.pay1.in/apis/receiveWeb/mindsarray/mindsarray"). 
			  		then().
			  		extract().
			  			response();		
			
			String response2	=	response.asString();
			
			
			Assert.assertEquals(response1, response2);
	}

}
