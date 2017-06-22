package jio_app;
import org.testng.annotations.Test;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest extends	Constants{
		
	public	static	String	mobileNumber	=	"8369982493"; //"8850055273"
	static Response response;
	static	JsonPath jp; 

	public	static	void	main(String[]	args)
	{
		getNumber();
		getAmount();
	}
	
	public static String	getNumber()
	{
		
		//To specify any params
		response	=given().
					 contentType(ContentType.JSON).
					//GET OR POST Request
					when().
						//Change this request to actual API 
						get("http://staging.pay1.in/recharges/pay1JioMobRecharge/"+mobileNumber+"/"+planAmount).
		  
					//Checks or verifications to be made
					then().
						//Verify status code = OK
						statusCode(200).
		  			extract().
		  				response();
	
		jp = new JsonPath(response.asString());
		String mob = jp.get("mobile").toString();
		return mob;
	}
	
	
	public	static	String	getAmount(){
		
		String amt	=	jp.get("amount").toString();
		return amt;
		
	}
	
}

