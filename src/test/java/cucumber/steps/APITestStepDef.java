package cucumber.steps;

import test.base.*;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

public class APITestStepDef {
	// create an instance of logger for logging
	Logger log = Logger.getLogger(APITestStepDef.class);
	
	static String HOST = "https://api.trademe.co.nz";
	static String API_RETRIEVE_CHARITIES = "/v1/Charities";
	static String API_RETRIEVE_USED_CARS = "/v1/Search/Motors/Used";	
	
	static String fileFormatJson = ".json";
	static String fileFormatXml = ".xml";
	
	//Authorization data 
	String consumer_key = "3369C289E0CFA55E689C41D93182BC9F";
	String consumer_secret = "202B7805030BAD2DC882E20C58F65FB3";
	String oAuth_token = "8DF8D9803B7ED7F09F7FC2DDD0467646";
	String oAuth_token_secret = "EADF946A7B2EBD1506EBA0D50D5E367F";	
	
	RestClient restClient;
	CloseableHttpResponse response;
	HttpGet httpGet;
	StringBuilder headerReq;
	
	@Given("^I connect to the API server$") @Test
	public void setUp() {
		restClient = new RestClient();
		headerReq = new StringBuilder();
		headerReq.append("OAuth")
				.append("\"").append(consumer_key).append("\"")
				.append("oauth_signature_method=\"PLAINTEXT\"") 
				.append("oauth_signature=").append("\"").append(consumer_secret).append("\"")
				.append("%26");
	}

	@When("^I send GET HTTP retrieve charities request$")  @Test
    public void queryCharities() throws ClientProtocolException, IOException{
		// set retrieve charities api
		log.info("Set GET HTTP retrive charities API");
		String url = HOST + API_RETRIEVE_CHARITIES + fileFormatJson;
        restClient.setGetWithouHeader(url);
        httpGet = new HttpGet(url);
        response = restClient.sendGetRequest(httpGet);
        
        log.info("Send retrieve charities GET HTTP request...");
        System.out.println("**** Retrieve charities, sent ");
    }
	
	
	@Then("^I receive valid HTTP Response$")  @Test
	public void checkResponseCode() {
		try {
			int statusCode = restClient.getStatusCode(response);
			
			log.info("Get reponse status code: " + statusCode + "...");
			System.out.println("**** Response status code is: " + statusCode);			
			Assert.assertEquals(RestClient.RESPONSE_STATUS_CODE_200, statusCode, "response status code is not 200");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@And("^St Johin is included in the list$")  @Test
	public void vefiryGetResponse() throws ParseException, IOException {
		String expectedText = "St John";
		
		// get the content of response entity
		log.info("Encode the response entiry...");
		String responseStr = EntityUtils.toString(response.getEntity(),"UTF-8");
		
		log.info("Charities: " + responseStr);
		System.out.println("**** Charities: " + responseStr);
		
		boolean result = responseStr.contains(expectedText);
		Assert.assertTrue(result, "'St John' is not included in the charities list");
	}
	
	@When("^I send GET HTTP retrieve used cars request$") @Test
	public void queryUsedCars() throws ClientProtocolException, IOException {
		log.info("Set GET HTTP retrive used cars API");
		String url = HOST + API_RETRIEVE_USED_CARS + fileFormatJson;
		
		// set header of the request, this request requires authorization
		HashMap<String, String> headerMap = new HashMap<String, String>(); 
		headerMap.put("Authorization", headerReq.toString());
		
		//TODO: complete the authorization first
        restClient.setGetWithHeader(url, headerMap);
        httpGet = new HttpGet(url);
        response = restClient.sendGetRequest(httpGet);
	}

	@And("^The car contains the details of Number plate, Kilometres, Body and Seats$") @Test
	public void confirmUsedCarsDetails() throws ParseException, IOException {
		
		String expectedText1 = "Number plate";
		String expectedText2 = "Kilometres";
		String expectedText3 = "Body";
		String expectedText4 = "Seats";
		
		//TODO: analyze the response entity, containing a list of used cars 
		
		String responseStr = EntityUtils.toString(response.getEntity(),"UTF-8");
		boolean result = responseStr.contains(expectedText1) &&  responseStr.contains(expectedText2) &&  responseStr.contains(expectedText3) &&  responseStr.contains(expectedText4);
		Assert.assertTrue(result, "The car does not contain all required details");
	}
}
