package test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Microfinance {
	
	//Static lender created for now
	public static String lenderId	=	"73";
	public	static	String	lenderToken;
	
	//Hardcoded responses
	public	static	String	loanRequestRecdMsg	=	"Loan request received";
	public	static	String	lenderBidMsg	=	"Lender loan offer successfull";
	public	static	String	offerAcceptMsg	=	"Borrower successfully accepted the offer";
	
	//Dynamic borrower Token
	//public	static	String	borrowerToken	=	"xxxxxxxxxxxxxxxx";
	//public	static	String	user_id	=	"40649519";

	//Declare variables
    private int loanId;
	String today;
    Response response;
    Response response1;
    
    
    //Array
    public static String[] userId	=	{"47007315","47007593"};
    public static String[] tokens	=	new String[5];	
  
   //SET PRIORITIES for all tests
	
  @BeforeTest
  public	void	setup(){
	  
	  //Get local date
	  LocalDate localDate = LocalDate.now();
	  today	=	DateTimeFormatter.ofPattern("yyy/MM/dd").format(localDate);
	  today	=	today.replace("/", "-");
	  
	  //Set baseUri
	  RestAssured.baseURI	=	"http://stagingmicrofinance.pay1.in";
	  lenderToken	=	getToken("9820817908", "1010");
	  
  }
  
  //Generate multiple requests for loan 
  //HOW? -> Store user-id & tokens in a hashmap and run a for loop to generate multiple loan requests for different users
  //CHECK? -> Verify response status and print, proceed to next api to get all loan requests and verify all the requests are obtained from the next api 
  
 // @Test(priority=1)
  public void loanRequest() {
	  
	  for(int i	=	0;i	<	2;i++)
	  {
		  response = 
				  		given().
				  		when().
				  			//Call api to request for loan 
				  			//params - userid, loanamt & token
				  			get("/requestLoan?loan_amount=15000&token="+tokens[i]+"&user_id="+userId[i]). 
				  		then().
				  			//Verify content type is JSON and extract
				  			contentType(ContentType.JSON).
				  			body("description.msg", equalTo(loanRequestRecdMsg)).
				  		extract().
				  			response();
	  	
	  convertToString(response);
	  }
  }
  
  //TO-DO
  //check all requests hit are recieved from this api
  
  @Test(priority=2)
  public void getRequests()
  {	  		
	  		response	=	given().
			 			when().
			 				//Call api to fetch all existing loan requests : (from - lender side)
			 			//Params - lender's userid & token
			 				get("/fetchLoanRequests?user_id="+lenderId+"&token="+lenderToken).
	 
			  			then().	
			  				//Verify the status is success
			  				body("status",	equalTo("success")).
			  				//Verify date
			  				//body("description[2].request_date",	equalTo(today)).			
			  				//Verify offered field is 0
			  				//body("description[2].Offered",	equalTo(0)).
			  			extract().
			  				response();
			  
	  		//Store all loan ids in the list
	  		List<Integer>	list	=	response.path("description.loan_id");
	  		
	  		//get last i.e. latest loan_id
	  		int lastIndex	=	list.size();
	  		loanId	=	list.get(lastIndex-1);
	  		
	  		//TO-DO
	  		//**************** Now pass the loan_id to the next api for continuous flow
	  		
	  		//Print an entire response
	  		//System.out.println(response.asString());
	  		
	  }
  
  
  //@Test(priority=4)
  public	void	offeredLoans()
  {
	  	
	 response1 =  	given().
			 		when().
			 			//call api to get all the open offered loans
			 			//params- userid (borrower) and token
			 			get("/offeredLoans?user_id="+userId[0]+"&token="+tokens[0]).
			 		then().
			 			//Check whether repsonse is null or offered loans exist or not
			 			body("description[0]", is(not(equalTo("null")))).
			 		extract().
			 			response();
	 //Call method
	 convertToString(response1);
  }
  
  
  //@Test(priority=3, dependsOnMethods	=	"getRequests") 
  public	void	lenderBid(){
	  
	  response	=	given().
			  			
			  		when().
			  		//Api for lender to make a bid to offer loans to borrower
			  		//params - user_Id(lender), loan_id, interest, tenure, token(lender)
			  			get("/lenderBid?user_id="+lenderId+"&loan_id="+loanId+"&interest_offered=20&tenure=1&token="+lenderToken).	  			
			  		then().
			  		//Should successfully post the offer and response should be validated
			  			//body("description.msg", equalTo(lenderBidMsg)).
			  		extract().
			  			response();
	  
	  //Call conversion method
	  convertToString(response);
  }

 // @Test(priority=5)
  public	void	offerAccept(){
	  
	  response	=	given().
		  		when().
		  		//get offer_id from offered loans
		  		//params- offer_id,token(borrower), uer_id,pwd
		  			get("/offerAccept?offer_id=13&token="+tokens[0]+"&user_id="+userId[0]+"&pwd=xxxxxxxx").
		  		then().
		  			body("status", equalTo("success")).
		  			body("description.msg", equalTo(offerAcceptMsg)).
		  		extract().
		  			response();
	  
	  //Call conversion method
	  convertToString(response);
  }
  
 //Hardcoded response for now
 //@Test(priority=6)
 public	void	pointsInfo(){
	  
	  response	=	given().
		  		when().
		  		//params- offer_id,token(borrower), uer_id,pwd
		  			get("/pointsInfo?token="+tokens[0]+"&user_id="+userId[0]).
		  		then().
		  			body("description.points", equalTo("100 Points = X Rupee")).
		  			body("status", equalTo("success")).
		  		extract().
		  			response();
	
	  //Call conversion method
	  convertToString(response);
	  
	  System.out.println("FIRST request done!!");
	  
  }
 
 //Hardcoded response for now
 //@Test(priority=7)
 public	void	reports(){
	  
	  response	=	given().
		  		when().
		  			
		  			get("/getReports?token=k324j2p7d0klehnoj2890ighh1&user_id=49&loan_id=14").
		  		then().
		  			body("status", equalTo("success")).
		  		extract().
		  			response();
		  			//to get just some parts of the response as path 
		  			//path("status");

	  convertToString(response);
	  
 }
 
  
  public void convertToString(Response response){
	  String res = response.asString();
	  System.out.println(res);
  }
  
 
  public String getToken(String number, String pwd){
	  
	  String s	=	given().
			  		when().
			  			get("/getUserDetails?phone_number="+number+"&pwd="+pwd).
			  		then().
			  		//	time(lessThan(2000L)).
			  		extract().
			  			path("description.token");
	  
	  System.out.println("token - "+s);
	  return s;
  }
 
}
