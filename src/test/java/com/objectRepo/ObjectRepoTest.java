package com.objectRepo;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import org.json.simple.JSONObject;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;

public class ObjectRepoTest {
	RequestSpecification req;
	Response res;
	JSONObject obj=new JSONObject();
	JsonPath data;
	File file;
	FileInputStream fis;
	Properties p=new Properties();
  
  @BeforeTest
  public void beforeTest() {
	  RestAssured.baseURI="https://reqres.in/";
	  req=RestAssured.given();
  }
  
  @Test
  public void addUser() throws Exception {
	  file=new File("C:\\Users\\KMAITI\\OneDrive - Capgemini\\Desktop\\API Testing\\RestAssuredDemo\\src\\test\\java\\com\\objectRepo\\UserData.properties");
	  fis=new FileInputStream(file);
	  p.load(fis);
	  String name=p.getProperty("name");
	  String job=p.getProperty("job");
	  obj.put("name", name);
	  obj.put("job", job);
	  req.headers("Content-Type","application/json");
	  res=req.body(obj.toJSONString()).post("api/users");
	  data=res.jsonPath();
	  AssertJUnit.assertEquals(data.getString("name"), name);
  }

  @AfterTest
  public void afterTest() {
	  req=null;
	  res=null;
	  data=null;
	  file=null;
	  fis=null;
  }

}
