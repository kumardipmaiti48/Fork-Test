package com.restAssured.RestAssuredDemo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.testng.annotations.BeforeTest;
import org.json.simple.JSONObject;
import org.testng.annotations.AfterTest;

public class SwaggerUITest {
	RequestSpecification req;
	Response res;
	JSONObject obj=new JSONObject();
	JsonPath data;
  
  @BeforeTest
  public void beforeTest() {
	  RestAssured.baseURI="https://bookstore.toolsqa.com/";
	  req=RestAssured.given();
  }
  
  @Test
  public void createUser() {
	  obj.put("userName", "Kumardip");
	  obj.put("password", "Test@123");
	  res=req.body(obj.toJSONString()).post("Account/v1/User");
	  System.out.println(res.asPrettyString());
	  System.out.println(res.statusLine());
  }
  
  @AfterTest
  public void afterTest() {
	  req=null;
	  res=null;
	  data=null;
  }

}
