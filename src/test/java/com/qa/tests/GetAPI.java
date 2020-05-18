package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.Base;
import com.qa.pages.Crux;
import com.qa.pages.RestClient;
import com.qa.util.TestUtil;

public class GetAPI extends Base {

	Base base;

	String websiteURL;

	String apiURL;

	String URI;

	com.qa.pages.RestClient restClient;

	Crux crux;

	CloseableHttpResponse CloseableHttpResponse;

	TestUtil testUtil;

	JSONObject json;

	@BeforeMethod
	public void setup() {

		base = new Base();

		websiteURL = prop.getProperty("websiteURL");

		apiURL = prop.getProperty("apiURL");

		URI = websiteURL + apiURL;

	}

	// Without Headers

	@Test(priority = 1)
	public void getURI() throws ClientProtocolException, Exception {

		restClient = new RestClient();

		restClient.get(URI);

		System.out.println("**************************************************");

	}

	@Test(priority = 2)
	public void verifyStatusCode() throws ClientProtocolException, IOException {

		crux = new Crux();

		CloseableHttpResponse = crux.get(URI);

		int statusCode = CloseableHttpResponse.getStatusLine().getStatusCode();

		System.out.println("Status code is --> " + statusCode);

		Assert.assertEquals(statusCode, base.Status_Code_200, "Status code does not match");

		System.out.println("**************************************************");

	}

	@Test(priority = 3)
	public void verifyDataPerPage() throws Exception, Exception {

		crux = new Crux();

		CloseableHttpResponse = crux.get(URI);

		String responseMessage = EntityUtils.toString(CloseableHttpResponse.getEntity(), "UTF-8");

		json = new JSONObject(responseMessage);

		System.out.println("Response from API is " + json);

		String dataPerPage = testUtil.getValueByJPath(json, "/per_page");

		System.out.println("Data per page is -->" + dataPerPage);

		Assert.assertEquals(dataPerPage, "6", "Data per page does not match");

		System.out.println("**************************************************");

	}

	@Test(priority = 4)
	public void verifyTotal() throws Exception, Exception {

		crux = new Crux();

		CloseableHttpResponse = crux.get(URI);

		String responseMessage = EntityUtils.toString(CloseableHttpResponse.getEntity());

		json = new JSONObject(responseMessage);

		System.out.println("Response from API is " + json);

		String total = testUtil.getValueByJPath(json, "/total");

		System.out.println("Total data is -->" + total);

		Assert.assertEquals(total, "12", "Total does not match");

		System.out.println("**************************************************");

	}

	@Test(priority = 5)
	public void verifyParticulatAtribute() throws Exception, Exception {

		crux = new Crux();

		CloseableHttpResponse = crux.get(URI);

		String responseMessage = EntityUtils.toString(CloseableHttpResponse.getEntity());

		json = new JSONObject(responseMessage);

		System.out.println("Response from API is " + json);

		String email = testUtil.getValueByJPath(json, "/data[1]/email");

		System.out.println("Email of the user is -->" + email);

		Assert.assertEquals(email, "janet.weaver@reqres.in", "email does not match");

		System.out.println("**************************************************");
	}

	@Test(priority = 6)
	public void verifyHeaders() throws ClientProtocolException, IOException {

		crux = new Crux();

		CloseableHttpResponse = crux.get(URI);

		Header[] headerArray = CloseableHttpResponse.getAllHeaders();

		HashMap<String, String> allHeaders = new HashMap<String, String>();

		for (Header head : headerArray) {

			allHeaders.put(head.getName(), head.getValue());
		}

		System.out.println("Header of the API is " + allHeaders);

		Assert.assertEquals(allHeaders.get("Connection"), "keep-alive", "Header does not match");

	}
	
	//With Headers
	@Test(priority = 7)
	public void verifyAPIWithHeaders() throws ClientProtocolException, IOException {

		crux = new Crux();

		HashMap<String, String> headerMap = new HashMap<String, String>();

		headerMap.put("Content-type", "application/json");
//		headerMap.put("Content-Type", "");
//		headerMap.put("", "");

		CloseableHttpResponse = crux.getAPIWithHeaders(URI, headerMap);

		String responseMessage = EntityUtils.toString(CloseableHttpResponse.getEntity());

		json = new JSONObject(responseMessage);

		System.out.println("Response from the API is -->" + json);

	}
}
