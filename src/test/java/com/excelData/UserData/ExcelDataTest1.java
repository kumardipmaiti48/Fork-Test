package com.excelData.UserData;

import org.testng.annotations.*;
import com.relevantcodes.extentreports.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.AssertJUnit;

public class ExcelDataTest1 {
	RequestSpecification req;
	Response res;
	JSONObject obj=new JSONObject();
	JsonPath data;
	ExtentReports report;
	ExtentTest test;
	 
  @BeforeTest
  public void beforeTest() {
	  RestAssured.baseURI="https://reqres.in/";
	  req=RestAssured.given();
	  report=new ExtentReports("C:\\Users\\KMAITI\\OneDrive - Capgemini\\Desktop\\API Testing\\RestAssuredDemo\\target\\ExcelDataTest1Report.html");
	  test=report.startTest("Getting Data from Excel", "This is description"); 
  }
  
  @Test(dataProvider="data",dataProviderClass=Data.class)
  public void addUser(String name,String job) {
	  obj.put("name", name);
	  obj.put("job", job);
	  res=req.headers("Content-Type","application/json").body(obj.toJSONString()).post("api/users");
	  data=res.jsonPath();
	  System.out.println(res.asPrettyString());
	  System.out.println(res.statusLine());
	  AssertJUnit.assertEquals(data.getString("name"),name);
	  if(name.equals(data.getString("name"))) {
		  test.log(LogStatus.PASS, "Data is valid for Expected "+name+" and actual is "+data.getString("name"));
	  }
	  else {
		  test.log(LogStatus.FAIL, "Data is invalid for "+name+" and actual is "+data.getString("name"));
	  }
  }

  @AfterTest
  public void afterTest() {
	  req=null;
	  res=null;
	  obj=null;
	  data=null;
	  report.endTest(test);
	  report.flush();
  }

}
