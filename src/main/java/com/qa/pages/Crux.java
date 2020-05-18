package com.qa.pages;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Crux {

	public CloseableHttpResponse get(String URI) throws ClientProtocolException, IOException {

		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

		HttpGet HttpGet = new HttpGet(URI);

		CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(HttpGet);

		return closeableHttpResponse;
	}

	public CloseableHttpResponse getAPIWithHeaders(String URI, HashMap<String, String> headerMap)
			throws ClientProtocolException, IOException {

		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

		HttpGet HttpGet = new HttpGet(URI);

		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			HttpGet.addHeader(entry.getKey(), entry.getValue());
		}

		CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(HttpGet);

		return closeableHttpResponse;
	}

	public CloseableHttpResponse postAPI(String URI, String entityString, HashMap<String, String> header)
			throws ClientProtocolException, IOException {

		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

		HttpPost httpPost = new HttpPost(URI);

		httpPost.setEntity(new StringEntity(entityString));

		for (Map.Entry<String, String> entry : header.entrySet()) {
			httpPost.addHeader(entry.getKey(), entry.getValue());
		}

		CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpPost);

		return closeableHttpResponse;
	}

}
