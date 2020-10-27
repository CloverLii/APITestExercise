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
	
	Logger log = Logger.getLogger(APITestStepDef.class);
	
	final String HOST = "https://api.trademe.co.nz";
	final String API_RETRIEVE_CHARITIES = "/v1/Charities";
	final String API_RETRIEVE_USED_CARS = "/v1/Search/Motors/Used";	
	final String fileFormatJson = ".json";
	final String fileFormatXml = ".xml";
	
	//Authorization info 
	private String ConsumerKey = "3369C289E0CFA55E689C41D93182BC9F";
	private String ConsumerSecret = "202B7805030BAD2DC882E20C58F65FB3";
	
	String url;
	
	RestClient restClient;
	CloseableHttpResponse response;
	HttpGet httpGet;
	StringBuilder headerReq;
	
	@Given("^I connect to the API server$") @Test
	public void setUp() {
		restClient = new RestClient();
		headerReq = new StringBuilder();
		headerReq.append("OAuth")
				.append("\"").append(ConsumerKey).append("\"")
				.append("oauth_signature_method=\"PLAINTEXT\"") 
				.append("oauth_signature=").append("\"").append(ConsumerSecret).append("\"")
				.append("%26");
	}

	@When("^I send GET HTTP retrieve charities request$")  @Test
    public void queryCharities() throws ClientProtocolException, IOException{
      
		url = HOST + API_RETRIEVE_CHARITIES + fileFormatJson;
        restClient.setGetWithouHeader(url);
        httpGet = new HttpGet(url);
        response = restClient.sendGetRequest(httpGet);
    }
	
	
	@Then("^I receive valid HTTP Response$")  @Test
	public void checkResponseCode() {
		try {
			int statusCode = restClient.getStatusCode(response);
			System.out.println("**** actual response status code is: " + statusCode);
			Assert.assertEquals(restClient.RESPONSE_STATUS_CODE_200, statusCode, "response status code is not 200");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@And("^St Johin is included in the list$")  @Test
	public void vefiryGetResponse() throws ParseException, IOException {
		String expectedText = "St John";
		String responseStr = EntityUtils.toString(response.getEntity(),"UTF-8");
		//System.out.println("****charities: " + responseStr);
		boolean result = responseStr.contains(expectedText);
		Assert.assertTrue(result, "'St John' is not included in the charities list");
	}
	
	@When("^I send GET HTTP retrieve used cars request$")
	public void queryUsedCars() throws ClientProtocolException, IOException {
		url = HOST + API_RETRIEVE_USED_CARS + fileFormatJson;
		HashMap<String, String> headerMap = new HashMap<String, String>(); 
		headerMap.put("Authorization", headerReq.toString());
		
        restClient.setGetWithHeader(url, headerMap);
        httpGet = new HttpGet(url);
        response = restClient.sendGetRequest(httpGet);
	}

	@And("^The car contains the information of Number plate, Kilometres, Body and Seats$")
	public void confirmUsedCarsDetails() throws ParseException, IOException {
		
		String expectedText1 = "Number plate";
		String expectedText2 = "Kilometres";
		String expectedText3 = "Body";
		String expectedText4 = "Seats";
		
		String responseStr = EntityUtils.toString(response.getEntity(),"UTF-8");
		boolean result = responseStr.contains(expectedText1) &&  responseStr.contains(expectedText2) &&  responseStr.contains(expectedText3) &&  responseStr.contains(expectedText4);
		Assert.assertTrue(result, "The car does not contain all required details");
	}
}
