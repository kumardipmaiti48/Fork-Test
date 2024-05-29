package com.authorization.test;

import org.testng.annotations.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;


public class Authorization {
	RequestSpecification req;
	Response res;
	JSONObject obj=new JSONObject();
	

  @BeforeTest
  public void beforeTest() {
	  RestAssured.baseURI="https://bookstore.toolsqa.com/";
	  req=RestAssured.given().header("Content-Type","application/json");
	  obj.put("userName", "Kumardip1");
	  obj.put("password", "TestPassword@123");
  }
  
  @Test
  public void auth() {
	  req.auth().basic("Kumardip1", "TestPassword@123").body(obj.toJSONString());
	  res=req.post("Account/v1/Authorized");
	  System.out.println(res.asPrettyString());
	  System.out.println(res.statusLine());
  }

  @Test
  public void generateToken() {
	  req.body(obj.toJSONString());
	  res=req.post("Account/v1/GenerateToken");
	  System.out.println(res.asPrettyString());
	  System.out.println(res.statusLine());
  }
  
  @Test
  public void getListBooks() {
	  res=req.get("BookStore/v1/Books");
	  System.out.println(res.asPrettyString());
	  System.out.println(res.statusLine());
  }
  
  @Test
  public void addBook() {
	  //req.
  }
  
  @AfterTest
  public void afterTest() {
	  req=null;
	  res=null;
  }

}
