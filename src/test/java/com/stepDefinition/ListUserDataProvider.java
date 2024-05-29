package com.stepDefinition;

import java.util.List;
import org.json.simple.JSONObject;
import org.testng.AssertJUnit;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ListUserDataProvider {
	RequestSpecification req;
	Response res;
	JSONObject obj=new JSONObject();
	JsonPath path;
	String expectedName , expectedJob, putJob, patchJob; 
	
	@Given("User is on Reqres page")
	public void user_is_on_reqres_page() {
	    RestAssured.baseURI="https://reqres.in/";
	    req=RestAssured.given().header("Content-Type","application/json");
	}

	@When("User Enters name and job")
	public void user_enters_name_and_job(DataTable data) {
	    List<List<String>> list=data.asLists(String.class);
	    String name=list.get(1).get(0);
	    String job=list.get(1).get(1);
	    obj.put("name", name);
	    obj.put("job", job);
	    req.body(obj.toJSONString());	    
	}

	@And("User hits the API")
	public void user_hits_the_api() {
	    res=req.post("api/users");
	    path=res.jsonPath();	    
	}

	@Then("User should be created")
	public void user_should_be_created() {
		System.out.println(res.asPrettyString());
		System.out.println(res.statusLine());
	    AssertJUnit.assertEquals(path.getString("name"), "Suraj");
	}
	
/*------------------------Scenerio 2--------------------------------------*/
	
	@When("^User enter (.*) and (.*)$")
	public void user_enter_and(String name, String job) {
	    obj.put("name", name);
	    obj.put("job",job);
	    req.body(obj.toJSONString());
	    expectedName=name;
	    expectedJob=job;
	}

	@And("User hit the API")
	public void user_hit_the_api() {
	    res=req.post("api/users");
	    path=res.jsonPath();
	    
	}

	@Then("User should be create")
	public void user_should_be_create() {
	    System.out.println(res.asPrettyString());
	    AssertJUnit.assertEquals(path.getString("name"), expectedName);
	}
/*--------------------------Put Method------------------------------------*/	
	@When("user enters job")
	public void user_put(DataTable dataTable) {
	    List<String> list=dataTable.asList(String.class);
	    putJob=list.get(0);
	    obj.put("name", "Kumardip");
	    obj.put("job", putJob);
	    req.body(obj.toJSONString());
	}

	@When("User hits the put API")
	public void user_hits_the_put_api() {
	    res=req.put("api/users/2");
	    path=res.jsonPath();
	}

	@Then("User provided value should updated")
	public void user_provided_value_should_updated() {
	    System.out.println(res.asPrettyString());
	    System.out.println(res.statusLine());
	    AssertJUnit.assertEquals(path.getString("job"), putJob);
	}
/*-----------------------Patch Method------------------------------------*/	
	@When("User enters a name")
	public void user_enters_a_name(DataTable dataTable) {
	    List<String> list=dataTable.asList(String.class);
	    String patchJob=list.get(0);
	    obj.put("name", "Kumardip");
	    obj.put("job", patchJob);
	    req.body(obj.toJSONString());
	}

	@When("User hits the patch API")
	public void user_hits_the_patch_api() {
	    res=req.patch("api/users/2");
	    path=res.jsonPath();
	}

	@Then("Patch method work successfully")
	public void patch_method_work_successfully() {
	    System.out.println(res.asPrettyString());
	    System.out.println(res.statusLine());
	    AssertJUnit.assertEquals(path.getString("job"), patchJob);
	}
	
/*---------------------------Delete Method-------------------------------*/	
	@When("User hits the delete API")
	public void user_hits_the_delete_api() {
	    res=req.delete("api/users/2");
	}

	@Then("Record should delete")
	public void record_should_delete() {
	    System.out.println(res.asPrettyString());
	    System.out.println(res.statusLine());
	    AssertJUnit.assertEquals(res.statusCode(), 204);
	}
	
}
