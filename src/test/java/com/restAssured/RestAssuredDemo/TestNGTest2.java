package com.restAssured.RestAssuredDemo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.testng.annotations.BeforeTest;
import org.json.simple.JSONObject;
import org.testng.annotations.AfterTest;

public class TestNGTest2 {
	RequestSpecification req;
	Response res;
	JSONObject obj=new JSONObject();
  
  @BeforeTest
  public void beforeTest() {
	  RestAssured.baseURI="https://reqres.in";
	  req=RestAssured.given();
	  
  }
  
  @Test(priority=0)
  public void getSingleUser() {
	  res=req.get("/api/users/2");
	  System.out.println(res.asPrettyString());
	  System.out.println(res.statusLine());
  }
  @Test(priority=1)
  public void postUser() {
	  obj.put("name", "Kumardip");
	  obj.put("job", "Analyst");
	  res=req.body(obj.toJSONString()).post("/api/users");
	  //res=req;
	  System.out.println(res.asPrettyString());
	  System.out.println(res.statusLine());
  }
  
  @Test(priority=2)
  public void putUser() {
	  obj.put("name", "Kumardip");
	  obj.put("job", "Software");
	  req.body(obj.toJSONString());
	  res=req.put("/api/users/2");
	  System.out.println(res.asPrettyString());
	  System.out.println(res.statusLine());
  }
  
  @Test(priority=3)
  public void patchUser() {
	  obj.put("name", "Kumardip");
	  obj.put("job", "Software Engineer");
	  req.body(obj.toJSONString());
	  res=req.patch("/api/users/2");
  }
  
  @Test(priority=4,enabled=false)
  public void deleteUser() {
	  res=req.delete("/api/users/2");
	  System.out.println(res.asPrettyString());
	  System.out.println(res.statusLine());
  }
  
  @AfterTest
  public void afterTest() {
	  req=null;
	  res=null;
  }

}
