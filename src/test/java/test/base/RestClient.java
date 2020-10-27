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

import org.testng.log4testng.Logger;


public class RestClient {
	Logger log = Logger.getLogger(RestClient.class);
	
	public static int RESPONSE_STATUS_CODE_200 = 200;
	public static int RESPONSE_STATUS_CODE_100 = 100;
		
	CloseableHttpClient httpClient;
	HttpGet httpGet;
	HttpPost httpPost;
	HttpPut httpPut;
	HttpDelete httpDelete;
	CloseableHttpResponse httpResponse;
	
	/*
	 * Set request without header
	 * @param url
	 * @throws ClientProtocolException, IOException
	 */
	public void setGetWithouHeader (String url) throws ClientProtocolException, IOException{
		// create an install of HttpClient
		httpClient  = HttpClients.createDefault();
		// create an install of HttpGet
		httpGet = new HttpGet(url);	
		log.info("Set GET HTTP request(without header)...");
	}

	/*
	 * Set request with header
	 * @param url
	 * @throws ClientProtocolException, IOException
	 */
	public void setGetWithHeader (String url, HashMap<String, String> headerMap) throws ClientProtocolException, IOException{
		httpClient  = HttpClients.createDefault();
		httpGet = new HttpGet(url);	
		// load request header to the HttpGet instance
		for(Map.Entry<String, String> entry: headerMap.entrySet()) {
			httpGet.addHeader(entry.getKey(), entry.getValue());
		}
		log.info("Set GET HTTP request(with header)...");
	}
	
	/*
	 * Set post request
	 * @param url
	 * @param entity
	 * @param headerMap
	 * @throws ClientProtocolException, IOException
	 */
	public void setPost (String url, String entity, HashMap<String, String> headerMap) throws ClientProtocolException, IOException{
		httpClient  = HttpClients.createDefault();
		httpPost = new HttpPost(url);
		//set pay load
		httpPost.setEntity(new StringEntity(entity));
		// load request header to the HttpPost instance
		for(Map.Entry<String, String> entry: headerMap.entrySet()) {
			httpPost.addHeader(entry.getKey(), entry.getValue());
		}
		log.info("Set POST HTTP request...");
	}	
	
	/*
	 * Set put request
	 * @param url
	 * @param entity
	 * @param headerMap
	 * @throws ClientProtocolException, IOException
	 */
	public void setPut (String url, String entity, HashMap<String, String> headerMap) throws ClientProtocolException, IOException{
		httpClient  = HttpClients.createDefault();
		httpPut = new HttpPut(url);
		//set pay load
		httpPut.setEntity(new StringEntity(entity));
		// load request header to the HttpPost instance
		for(Map.Entry<String, String> entry: headerMap.entrySet()) {
			httpPut.addHeader(entry.getKey(), entry.getValue());
		}
		log.info("Set PUT HTTP request...");
	}
	
	/*
	 * Set delete request
	 * @param url
	 * @throws ClientProtocolException, IOException
	 */
	public void setDelete(String url) throws ClientProtocolException, IOException{
		httpClient  = HttpClients.createDefault();
		httpDelete = new HttpDelete(url);	
		log.info("Set DELETE HTTP request...");
	}
	
	/*
	 * Get response status code
	 * @param response
	 * @return statusCode
	 */
	public int getStatusCode(CloseableHttpResponse response) {
		int statusCode = response.getStatusLine().getStatusCode();
		log.info("Get response status code: " + statusCode);
		return statusCode;
	}
	
	/*
	 * Sent GET HTTP request
	 * @param httpGet
	 * @throws ClientProtocolException, IOException
	 */
	public CloseableHttpResponse sendGetRequest(HttpGet httpGet) throws ClientProtocolException, IOException {
		log.info("Start to send GET HTTP request...");
		httpResponse = httpClient.execute(httpGet);
		log.info("Request sent! Get response...");
		return httpResponse;
	}

}
