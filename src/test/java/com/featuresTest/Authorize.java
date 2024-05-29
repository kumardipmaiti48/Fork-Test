package com.featuresTest;

import org.json.simple.JSONObject;
import org.testng.AssertJUnit;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Authorize {
	RequestSpecification req;
	Response res;
	JSONObject obj=new JSONObject();
	JsonPath path;
	
	@Given("user opened the bookstore API")
	public void user_opened_the_bookstore_api() {
	    RestAssured.baseURI="https://bookstore.toolsqa.com/";
	    req=RestAssured.given().header("Content-Type","application/json");
	}

	@When("user click on authorize option")
	public void user_click_on_authorize_option() {
	    obj.put("userName","Kumardip1");
	    obj.put("password", "TestPassword@123");
	    res=req.body(obj.toJSONString()).auth().basic("Kumardip1", "TestPassword@123").post("Account/v1/Authorized");
	    path=res.jsonPath();
	}

	@Then("it should authorize the user")
	public void it_should_authorize_the_user() {
		System.out.println("Authorized Successfully..............");
	    System.out.println(res.statusLine());
	    System.out.println(res.asPrettyString());
	    AssertJUnit.assertEquals(res.asPrettyString(), "true");
	}
}
