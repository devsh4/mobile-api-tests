package test;

import static com.jayway.restassured.RestAssured.given;

import java.io.File;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;
import org.testng.annotations.Test;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class PlatformApis {

	//Declare global variables
	static Response resp;	
	public	static	String	encrypted;
	public	static	String	token	=	"2kc1pimmashf4jb8s1odkns7g0";
	public	static	String	userId	=	"11470265";
	
	//Test cases for platform apis
	public	static	void	main(String[] args) throws Exception{

		//APIS
		
		//authenticate();
		//verifyOtpAuthenticate();
		//profileApi();
		//balanceApi();
		//walletHistory();
		//changePin();
		//verifyOTPChangePin();
		//deviceInfoUpdate();
		//uploadDoc();
	
	}
	
	public	static	String	convertAndEncrypt(Map	data){
		JSONObject	jsonObj	=	new	JSONObject(data);
		String	params	=	jsonObj.toString();
		
		try {
			AES 	a	=	new	AES();
			encrypted = a.encrypt(params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(encrypted);
		return encrypted;
	}
	
	
	public static String platformApiTest(String encoded){
		 
		  //To specify any params
		  resp	=	given().
				  		params("req", encoded).
				  	when().
				  		post("http://uatpanel.pay1.in/platform/apis").
				  	then().
				  		//contentType(ContentType.JSON).
				  		assertThat().
				  		statusCode(200).
				  		and().
				  		contentType(ContentType.JSON).		
				  	extract().
				  		response();
		
		 String respString = resp.asString(); 
		 System.out.println(respString);
		 return respString;
	}

	
	public static	void	uploadDocTest(String encoded){
		 
		  File	f	=	new File("C:\\Users\\Administrator.pay1lap-71-PC\\Desktop\\Desert.jpg");
				
		  //To specify any params
		  resp	=	given().
				  		params("req", encoded).
				  		//params("document[]", f).
				  	when().
				  		post("http://uatpanel.pay1.in/platform/apis").
				  	then().
				  		//contentType(ContentType.JSON).
				  		statusCode(200).
				  	extract().
				  		response();
		
		 System.out.println(resp.asString());
		  
	}
	
	
	public static void changePin() throws Exception{
	
				Map<String,String>	data	= new	HashMap<String,String>();
				//PARAMETERS
				data.put("method","changePin"); 
				data.put("app_name","smartpay");
				data.put("mobile","7101000522");
		
				platformApiTest(convertAndEncrypt(data));
	}
	
	
	public static	void	balanceApi() throws Exception
	{
				Map<String,String>	data	= new	HashMap<String,String>();
				//BALANCE
				data.put("method","balance");
				data.put("app_name","smartpay");
				data.put("token", token); 
				data.put("user_id", userId);
				
				//Call Api
				platformApiTest(convertAndEncrypt(data));

	}
	
	
	public static	void	walletHistory(){
				
				Map<String,String>	data	= new	HashMap<String,String>();
				data.put("method","walletHistory");
				data.put("app_name","smartpay");
				data.put("date_from", "2017-06-21");
				data.put("date_to", "2017-06-21");
				data.put("token", token);
				data.put("user_id", userId);
				
				platformApiTest(convertAndEncrypt(data));
				
				
	}
	
	
	public static	void	authenticate(){
		
				Map<String,String>	data	= new	HashMap<String,String>();
				data.put("method","authenticate");
				data.put("app_name","dmt");
				data.put("app_type","dmt");
				data.put("mobile","7101000522");
				data.put("password","12345");
				data.put("version_code","68");
				data.put("uuid","2701070873"); //2701070875
		
				platformApiTest(convertAndEncrypt(data));

	}
	
	
	public static	void	profileApi(){
		
				Map<String,String>	data	= new	HashMap<String,String>();
				data.put("method","profileApi");
				data.put("app_name","smartpay");
				data.put("token",token);
				data.put("user_id", userId);

				platformApiTest(convertAndEncrypt(data));
				
	}
	
    
	public static	void	verifyOTPChangePin(){
		
				Map<String,String>	data	= new	HashMap<String,String>();				
				data.put("method","verifyOTPChangePIN");
				data.put("app_name","smartpay");
				data.put("mobile", "7101000522");
				data.put("otp", "771555");
				data.put("pin", "11111");
				
				platformApiTest(convertAndEncrypt(data));

	}
	
	
	public static	void	deviceInfoUpdate(){
		
				Map<String,String>	data	= new	HashMap<String,String>();
				data.put("method","deviceInfoUpdate");
				data.put("app_name","smartpay");
				data.put("app_type","smartpay");
				data.put("user_id", userId);
				data.put("token", token);
				data.put("gcm_reg_id", "111111111");
				data.put("device_type", "web");
				data.put("latitude", "15");
				data.put("longitude", "90");
				data.put("version_code", "65");
				data.put("uuid", "2701070875");
				data.put("version", "6.1");
				data.put("manufacturer", "dev");
				

				platformApiTest(convertAndEncrypt(data));
				
	}	
	
	
	public static	void	uploadDoc(){
		
				Map<String,String>	data	= new	HashMap<String,String>();
				data.put("method","uploadDocs");
				data.put("app_name","smartpay");
				data.put("user_id", userId);
				data.put("token", token);
				data.put("label_id", "kyc");
				data.put("service_id", "9");
				//data.put("document[]=", "C:\\Users\\Administrator.pay1lap-71-PC\\Desktop\\a.txt");
				//data.put("document[]=", "");
			
				uploadDocTest(convertAndEncrypt(data));
		
	}	

	
	public static	void	verifyOtpAuthenticate(){
		
		Map<String,String>	data	= new	HashMap<String,String>();
		data.put("method","verifyOTPAuthenticate");
		data.put("app_name","dmt");
		data.put("app_type","dmt");
		data.put("user_id", "47007593");
		data.put("mobile", "7101000522");
		data.put("otp", "227793");
		data.put("uuid", "2701070873");
	
		uploadDocTest(convertAndEncrypt(data));

	}	

	
}
