package test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

public class SmsTadka {
  
	public	static	String num	=	"9833887517";
	
	//All vmn's
	public	static	String[] vmn	=	{"918652225225", "919717594594", "919223178889", "919289229929", "919004350350"};
	public	static	int[] recharge_amt	=	{10,11,12,13,14};
	Response response;
	
	
  @BeforeTest
  public void setUp(){
	  
	  RestAssured.baseURI	=	"http://smstadka.com:81";
  }
	
	
  @Test
  public void insertInQueue() {
	  
	  given().
	  when().
	  		get("/redis/insertInQsms?sender=&mobile="+num+"&sms=testing&root=payone&app_name=upiuat").
	  then().
	  		body("status", equalTo("success"));	
	  
  }
  
  @Test
  public void sendMsg() {
	  
	  given().
	  when().
	  		get("/users/sendMsgMails?mobile="+num+"&sender=&root=payone&sms=You%20are%20trying%20to%20login%20through%20a%20new%20device%20or%20browser.").
	  then().
	  		body("status", equalTo("success"));	
	  
  }
  
  @Test
  public void sendMails() {
	  
	  given().
	  when().
	  		get("/users/sendMsgMails?mail_subject=MailTestcc.pay1.me&mail_body=Testing&emails=dev.shah@mindsarray.com").
	  then().
	  		body("status", equalTo("success"));	
	  
  }
  
  @Test
  public void recharge() {
	  
	  //Send recharge requests from all vmn's 
	  for(int i=0;i<=4;i++)
	  {
		  given().when().get("/apis/sms.php?vnumber="+vmn[i]+"&sender=918237235748&stime=12%2F08%2F16+09%3A11%3A06+AM&operator=TTSL&msg=*15*"+num+"*"+recharge_amt[i]+"&circle=Maharashtra").then().body("status", equalTo("success"));	
	  }
  }
  
  @Test
  public void outgoingMonitor(){
	  
	  response	=	given().when().get("/promotions/smsOutgoingMonitoring").then().extract().response();
  
	  //Print entire response
	  System.out.println(response.asString());

	  //Get sent and delivered % for tata
	  Float delivered_tata	=	response.path("SMSTata.DELIVERED.per");
	  Float	sent_tata	=	response.path("SMSTata.SENT.per");
	  //HashMap<String,String>
	  
	  //Get sent and delivered % for sms247
	  Float delivered_sms247	=	response.path("SMS24x7.DELIVERED.per");
	  Float	sent_sms247	=	response.path("SMS24x7.UNRESOLVED.per");
	  
	  //Print
	  System.out.println("Tata delivered % :"+delivered_tata);
	  System.out.println("Tata sent % :"+sent_tata);
	  System.out.println("sms247 delivered % :"+delivered_sms247);
	  System.out.println("sms247 sent % :"+sent_sms247);
	  
	  //Create checks on sent and delivered figures
	  if(sent_tata	<=	6.00 && sent_sms247	<=	6.00)
	  {
		  System.out.println("Delivery to sent ratio is normal");
	  }
	  else{
		  System.out.println("**** ALERT - Delivery to sent ratio is abnormal!!!! ****");
		  System.out.println(" ");
	  }
  }

}
