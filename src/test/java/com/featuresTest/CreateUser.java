package com.featuresTest;

import org.json.simple.JSONObject;
import org.testng.AssertJUnit;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateUser {
	RequestSpecification req;
	Response res;
	JSONObject obj=new JSONObject();
	JsonPath path;
	
	@Given("user is on reqres url")
	public void user_is_on_reqres_url() {
	    RestAssured.baseURI="https://reqres.in/";
	    req=RestAssured.given();
	}

	@When("user clicks on post api")
	public void user_clicks_on_post_api() {
	    obj.put("name", "Kumardip");
	    obj.put("job", "Analyst");
	    res=req.body(obj.toJSONString()).headers("Content-Type","application/json").post("api/users");
	    path=res.jsonPath();
	}

	@Then("user should be created")
	public void user_should_be_created() {
	    System.out.println(res.asPrettyString());
	    AssertJUnit.assertEquals(path.getString("name"), "Kumardip");
	}
}
