package test;

import org.testng.annotations.Test;
import com.jayway.restassured.http.ContentType;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.util.List;
/*import com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.matcher.ResponseAwareMatcher;
import com.jayway.restassured.matcher.RestAssuredMatchers.*;*/


public class FirstAPITest {
	
	//List<String> l = {Afghanistan, Åland Islands, Albania, Algeria, American Samoa, Andorra, Angola, Anguilla, Antarctica, Antigua and Barbuda, Argentina, Armenia, Aruba, Australia, Austria, Azerbaijan, The Bahamas, Bahrain, Bangladesh, Barbados, Belarus, Belgium, Belize, Benin, Bermuda, Bhutan, Bolivia, Bonaire, Bosnia and Herzegovina, Botswana, Bouvet Island, Brazil, British Indian Ocean Territory, United States Minor Outlying Islands, Virgin Islands (British), Virgin Islands (U.S.), Brunei, Bulgaria, Burkina Faso, Burundi, Cambodia, Cameroon, Canada, Cape Verde, Cayman Islands, Central African Republic, Chad, Chile, China, Christmas Island, Cocos (Keeling) Islands, Colombia, Comoros, Republic of the Congo, Democratic Republic of the Congo, Cook Islands, Costa Rica, Croatia, Cuba, Curaçao, Cyprus, Czech Republic, Denmark, Djibouti, Dominica, Dominican Republic, Ecuador, Egypt, El Salvador, Equatorial Guinea, Eritrea, Estonia, Ethiopia, Falkland Islands, Faroe Islands, Fiji, Finland, France, French Guiana, French Polynesia, French Southern and Antarctic Lands, Gabon, The Gambia, Georgia, Germany, Ghana, Gibraltar, Greece, Greenland, Grenada, Guadeloupe, Guam, Guatemala, Guernsey, Guinea, Guinea-Bissau, Guyana, Haiti, Heard Island and McDonald Islands, Holy See, Honduras, Hong Kong, Hungary, Iceland, India, Indonesia, Ivory Coast, Iran, Iraq, Republic of Ireland, Isle of Man, Israel, Italy, Jamaica, Japan, Jersey, Jordan, Kazakhstan, Kenya, Kiribati, Kuwait, Kyrgyzstan, Laos, Latvia, Lebanon, Lesotho, Liberia, Libya, Liechtenstein, Lithuania, Luxembourg, Macau, Republic of Macedonia, Madagascar, Malawi, Malaysia, Maldives, Mali, Malta, Marshall Islands, Martinique, Mauritania, Mauritius, Mayotte, Mexico, Federated States of Micronesia, Moldova, Monaco, Mongolia, Montenegro, Montserrat, Morocco, Mozambique, Myanmar, Namibia, Nauru, Nepal, Netherlands, New Caledonia, New Zealand, Nicaragua, Niger, Nigeria, Niue, Norfolk Island, North Korea, Northern Mariana Islands, Norway, Oman, Pakistan, Palau, Palestine, Panama, Papua New Guinea, Paraguay, Peru, Philippines, Pitcairn Islands, Poland, Portugal, Puerto Rico, Qatar, Republic of Kosovo, Réunion, Romania, Russia, Rwanda, Saint Barthélemy, Saint Helena, Saint Kitts and Nevis, Saint Lucia, Saint Martin, Saint Pierre and Miquelon, Saint Vincent and the Grenadines, Samoa, San Marino, São Tomé and Príncipe, Saudi Arabia, Senegal, Serbia, Seychelles, Sierra Leone, Singapore, Sint Maarten, Slovakia, Slovenia, Solomon Islands, Somalia, South Africa, South Georgia, South Korea, South Sudan, Spain, Sri Lanka, Sudan, Suriname, Svalbard and Jan Mayen, Swaziland, Sweden, Switzerland, Syria, Taiwan, Tajikistan, Tanzania, Thailand, East Timor, Togo, Tokelau, Tonga, Trinidad and Tobago, Tunisia, Turkey, Turkmenistan, Turks and Caicos Islands, Tuvalu, Uganda, Ukraine, United Arab Emirates, United Kingdom, United States, Uruguay, Uzbekistan, Vanuatu, Venezuela, Vietnam, Wallis and Futuna, Western Sahara, Yemen, Zambia, Zimbabwe}; 
  
  @Test
  public void makeSureGoogleIsUp(){
	  
	  //To specify any params
	  given().
	  
	  //GET OR POST Request
	  when().
	  	get("http://www.google.com").
	  
	  //Checks or verifications to be made
	  then().
	  //Verify status code = OK
	  	statusCode(200);
	  
	  //Print
	  System.out.println("Google is up");
  }
  
  
  @Test
  	public void getCountry()
  	{
	 //API for entire country list
	 //http://restcountries.eu/rest/v1/all
	  
	  given().

	  when().
	  	get("http://restcountries.eu/rest/v1/currency/inr").
	  	
	  then().
	  //To confirm contentType of response
	  contentType(ContentType.JSON).
	  
	  //Multiple verifications
	  body("name[0]", equalTo("Bhutan")).
	  body("translations[0].fr", equalTo("Bhoutan"));
 
	  System.out.println("FINISH!");
	    		
  	}

}
