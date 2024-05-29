package com.stepDefinition;

import org.json.simple.JSONObject;
import org.testng.AssertJUnit;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ReqresTest1 {
	RequestSpecification req;
	Response res;
	JSONObject obj=new JSONObject();
	JsonPath path;
	String expectedJob, expectedName;
	
	@Given("User already on reqres URL")
	public void user_already_on_reqres_url() {
	    RestAssured.baseURI="https://reqres.in/";
	    req=RestAssured.given().header("Content-Type","application/json");
	}

	@When("^User enters (.*) and (.*)$")
	public void user_enters_and(String name, String job) {
	    obj.put("name",name);
	    obj.put("job", job);
	    expectedJob=job;
	    expectedName=name;
	    req.body(obj.toJSONString());
	}

	@When("User click on post")
	public void user_click_on_post() {
	    res=req.post("api/users");
	    path=res.jsonPath();
	}

	@Then("The user should be created")
	public void the_user_should_be_created() {
		System.out.println(res.asPrettyString());
	    AssertJUnit.assertEquals(path.getString("name"), expectedName);
	    
	}
}
