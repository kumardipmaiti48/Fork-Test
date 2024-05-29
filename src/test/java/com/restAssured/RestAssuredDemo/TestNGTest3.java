package com.restAssured.RestAssuredDemo;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.json.simple.JSONObject;
import org.testng.annotations.AfterTest;

public class TestNGTest3 {
	RequestSpecification req;
	Response res;
	JSONObject obj=new JSONObject();
	JsonPath data;
  
  @BeforeTest
  public void beforeTest() {
	  RestAssured.baseURI="https://reqres.in/";
	  req=RestAssured.given();
  }
  
 @Test(priority=1,enabled=true)
  public void addUser() {
	  obj.put("name", "Kumardip");
	  obj.put("job", "Analyst");
	  req.header("Content-Type","application/json");	  
	  res=req.body(obj.toJSONString()).post("api/users");
	  data=res.jsonPath();
	  System.out.println(res.asPrettyString());
	  AssertJUnit.assertEquals(data.getString("name"), "Kumardip");
	  AssertJUnit.assertEquals(data.getString("job"), "Analyst");
	  AssertJUnit.assertEquals(res.getStatusCode(), 201);
  }
 	@Test(priority=0,enabled=true)
 	public void getListUser() {
 		res=req.get("api/users?page=2");
 		data=res.jsonPath();
 		AssertJUnit.assertEquals(data.getString("data[1].email"), "lindsay.ferguson@reqres.in");
 	}

 	//Below test is with dataprovider from another class
 	@Test(priority=2,dataProvider="userdata",dataProviderClass=DataProviderTest.class,enabled=true)
 	public void addUserUsingDataProvider(String name,String job) {
 		obj.put("name", name);
 		obj.put("job",job);
 		req.header("Content-Type","application/json");
 		res=req.body(obj.toJSONString()).post("api/users");
 		data=res.jsonPath();
 		AssertJUnit.assertEquals(data.getString("name"), name);
	}
 	
 
  @AfterTest
  public void afterTest() {
	  req=null;
	  res=null;
	  data=null;
  }

}
