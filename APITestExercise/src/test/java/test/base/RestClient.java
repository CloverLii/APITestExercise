package test.base;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.log4testng.Logger;
import org.apache.http.ParseException;

public class RestClient {
	
	public final int RESPONSE_STATUS_CODE_200 = 200;
	public final int RESPONSE_STATUS_CODE_100 = 100;
	
	final static Logger log = Logger.getLogger(RestClient.class);
	CloseableHttpClient httpClient;
	HttpGet httpGet;
	HttpPost httpPost;
	HttpPut httpPut;
	HttpDelete httpDelete;
	CloseableHttpResponse httpResponse;
	
	//Set GET method without header
	public void setGetWithouHeader (String url) throws ClientProtocolException, IOException{
		httpClient  = HttpClients.createDefault();
		httpGet = new HttpGet(url);	
		log.info("Set GET request without header...");
	}

	//Set GET method with header
	public void setGetWithHeader (String url, HashMap<String, String> headerMap) throws ClientProtocolException, IOException{
		httpClient  = HttpClients.createDefault();
		httpGet = new HttpGet(url);	
		for(Map.Entry<String, String> entry: headerMap.entrySet()) {
			httpGet.addHeader(entry.getKey(), entry.getValue());
		}
		log.info("Set GET request with header...");
	}
	
	//Set POST method
	public void setPost (String url, String entity, HashMap<String, String> headerMap) throws ClientProtocolException, IOException{
		httpClient  = HttpClients.createDefault();
		httpPost = new HttpPost(url);
		//set pay load
		httpPost.setEntity(new StringEntity(entity));
		for(Map.Entry<String, String> entry: headerMap.entrySet()) {
			httpPost.addHeader(entry.getKey(), entry.getValue());
		}
		log.info("Set POST request...");
	}	
	
	//Set PUT method
	public void setPut (String url, String entity, HashMap<String, String> headerMap) throws ClientProtocolException, IOException{
		httpClient  = HttpClients.createDefault();
		httpPut = new HttpPut(url);
		//set pay load
		httpPut.setEntity(new StringEntity(entity));
		for(Map.Entry<String, String> entry: headerMap.entrySet()) {
			httpPut.addHeader(entry.getKey(), entry.getValue());
		}
		log.info("Set PUT request...");
	}
	
	//Set DELETE method
	public void setDelete(String url) throws ClientProtocolException, IOException{
		httpClient  = HttpClients.createDefault();
		httpDelete = new HttpDelete(url);	
		log.info("Set DELETE request...");
	}
	
	public int getStatusCode(CloseableHttpResponse response) {
		int statusCode = response.getStatusLine().getStatusCode();
		log.info("Get status code of response: " + statusCode);
		return statusCode;
	}
	
	public CloseableHttpResponse sendGetRequest(HttpGet httpGet) throws ClientProtocolException, IOException {
		log.info("Start to send request...");
		httpResponse = httpClient.execute(httpGet);
		log.info("Send request successfully! Get response...");
		return httpResponse;
	}
	
	public JSONObject getResponseJson (CloseableHttpResponse response) throws ParseException, IOException {

		String responseString = EntityUtils.toString(response.getEntity(),"UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		return responseJson;
	}

}
