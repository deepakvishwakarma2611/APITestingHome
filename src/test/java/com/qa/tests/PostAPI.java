package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.text.html.parser.Entity;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.Base;
import com.qa.data.Users;
import com.qa.pages.Crux;

public class PostAPI extends Base {

	Base base;

	String websiteURL;

	String apiURL;

	String URI;

	com.qa.pages.RestClient restClient;

	Crux crux;

	CloseableHttpResponse closeableHttpResponse;
	
	JSONObject json;

	Users users;

	@BeforeMethod
	public void setup() {

		base = new Base();

		websiteURL = prop.getProperty("websiteURL");

		apiURL = prop.getProperty("apiURL");

		URI = websiteURL + apiURL;

		System.out.println("Your URI is: -->" + URI);

	}

	@Test
	public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException {

		crux = new Crux();

		HashMap<String, String> header = new HashMap<String, String>();

		header.put("Content-type", "application/json");

		// Jackson API
		ObjectMapper mapper = new ObjectMapper();

		users = new Users("Deepak", "QA");

		//Object to Json File
		mapper.writeValue(new File(
				"C:\\Users\\Deepak Vishwakarma\\eclipse-workspace\\APITestingHome\\src\\main\\java\\com\\qa\\util\\users.json"),
				users);
		
		//Object to Json to String
		 
		String mapperStringValue = mapper.writeValueAsString(users);	
		System.out.println(mapperStringValue);
		
		closeableHttpResponse = crux.postAPI(URI, mapperStringValue, header);
		
		
		// 1.Status Code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code of the Post API is : -->"+ statusCode);
		
		Assert.assertEquals(statusCode, base.Status_Code_201, "Status does not match");
		
		//2 Response Message
		
		String responseMessage = EntityUtils.toString(closeableHttpResponse.getEntity());
		
		json = new JSONObject(responseMessage);
		
		System.out.println("Json Rsponse from the Api is: -->"+ json);
		
		//json to Java Obkect
		Users usersobj = mapper.readValue(responseMessage, Users.class);
		System.out.println(usersobj); 
		
		Assert.assertEquals(users.getName(), usersobj.getName());
		
		Assert.assertEquals(users.getJob(), usersobj.getJob());
		
		System.out.println(users.getId());
		
		System.out.println(users.getCreatedAt());
		
	}

}
