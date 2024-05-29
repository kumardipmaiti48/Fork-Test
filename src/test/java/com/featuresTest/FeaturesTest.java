package com.featuresTest;

import org.json.simple.JSONObject;
import org.testng.AssertJUnit;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class FeaturesTest {
	RequestSpecification req;
	Response res;
	JSONObject obj=new JSONObject();
	JsonPath path;
	
	@Given("user is on reqres URL")
	public void user_is_on_reqres_url() {
	    RestAssured.baseURI="https://reqres.in/";
	    req=RestAssured.given();
	}

	@When("user clicks on user API")
	public void user_clicks_on_user_api() {
	    res=req.get("api/users?page=2");
	    path=res.jsonPath();
	}

	@Then("all users should displayed")
	public void all_users_should_displayed() {
	    System.out.println(res.asPrettyString());
	    AssertJUnit.assertEquals(path.getString("data[0].email"), "michael.lawson@reqres.in");
	}
}
