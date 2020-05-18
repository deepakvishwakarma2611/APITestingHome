package com.qa.pages;

import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {

	public void get(String URI) throws ClientProtocolException, Exception {

		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

		HttpGet HttpGet = new HttpGet(URI);

		CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(HttpGet);

		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();

		System.out.println("Status code is --> " + statusCode);

		String responseMessage = EntityUtils.toString(closeableHttpResponse.getEntity(),  "UTF-8");

		JSONObject json = new JSONObject(responseMessage);

		System.out.println("Rsponse Json from API --> " + json);

		Header[] headerArray = closeableHttpResponse.getAllHeaders();

		HashMap<String, String> header = new HashMap<String, String>();

		for (Header head : headerArray) {

			header.put(head.getName(), head.getValue());
		}

		System.out.println("Header from the API is --> " + header);

	}

}
