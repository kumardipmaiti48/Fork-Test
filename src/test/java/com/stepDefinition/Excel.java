package com.stepDefinition;

import java.io.*;
import java.util.*;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Excel {
	RequestSpecification req;
	Response res;
	JSONObject obj=new JSONObject();
	JsonPath path;
	File file;
	FileInputStream fis;
	XSSFWorkbook w;
	XSSFSheet s;
	String expectedJob,expectedName;
	
	@DataProvider(name="data")
	public String[][] dataP() throws Exception{
		fis=new FileInputStream(new File("C:\\Users\\KMAITI\\OneDrive - Capgemini\\Desktop\\API Testing\\RestAssuredDemo\\src\\test\\resources\\Excel\\UserData.xlsx"));
		w=new XSSFWorkbook(fis);
		s=w.getSheetAt(0);
		int row=s.getPhysicalNumberOfRows();
		int column=s.getRow(0).getPhysicalNumberOfCells();
		int firstRow=s.getFirstRowNum();
		String[][] arr=new String[row][column];
		for(int i=firstRow;i<row;i++) {
			for(int j=0;j<column;j++) {
				arr[i][j]=s.getRow(i).getCell(j).toString();
			}
		}
		return arr;
	}
	
	@Given("User is on Reqres URL")
	public void user_is_on_reqres_url(){
	    RestAssured.baseURI="https://reqres.in/";
	    req=RestAssured.given().header("Content-Type","application/json");
	}
	
	@When("User give name and job")
	@Test(dataProvider="data")
	public void user_give_name_and_job(String name,String job) {
	    obj.put("name", name);
	    obj.put("job", job);
	    req.body(obj.toJSONString());
	    expectedName=name;
	    expectedJob=job;
	    res=req.post("api/users");
	    System.out.println(res.asPrettyString());
	    System.out.println(res.statusLine());
	}
	
	@And("User hit the post button")
	public void user_hit_the_post_button() {
	    res=req.post("api/users");
	    path=res.jsonPath();
	}
	
	@Then("User should be created.")
	public void user_should_be_created() {
		System.out.println(res.asPrettyString());
		System.out.println(res.statusLine());
	    AssertJUnit.assertEquals(path.getString("name"), expectedName);
	    AssertJUnit.assertEquals(path.getString("job"), expectedJob);
	}
	
/*-----------------------------Scenerio 2---------------------------------*/
	
	@When("User give name and job from excel")
	public void user_give_and() throws Exception {
		fis=new FileInputStream(new File("C:\\Users\\KMAITI\\OneDrive - Capgemini\\Desktop\\API Testing\\RestAssuredDemo\\src\\test\\resources\\Excel\\UserData.xlsx"));
		w=new XSSFWorkbook(fis);
		s=w.getSheet("data");
		int row=s.getPhysicalNumberOfRows();
		int column=s.getRow(0).getPhysicalNumberOfCells();
		List<String> name=new ArrayList<>();
		List<String> job=new ArrayList<>();
		for(int i=0;i<row;i++) {
			name.add(s.getRow(i).getCell(0).toString());
		}
		for(int i=0;i<column;i++) {
			job.add(s.getRow(i).getCell(1).toString());
		}
	    List<List<String>> list=new ArrayList<List<String>>();
	    list.add(name);
	    list.add(job);
	    String n=list.get(0).get(0);
	    String j=list.get(0).get(1);
	    obj.put("name", n);
	    obj.put("job", j);
	    expectedName=n;
	    expectedJob=j;
	    req.body(obj.toJSONString());
	    System.out.println(n);
	    System.out.println(j);
	}
	@And("User hit the post button1")
	public void user_hit_the_post_button1() {
	    res=req.post("api/users");
	    path=res.jsonPath();
	}
	@Then("User should be created1")
	public void user_should_be_created1() {
	    AssertJUnit.assertEquals(path.getString("name"), expectedName);
	    System.out.println(res.asPrettyString());
	    System.out.println(res.statusLine());
	}
	
}
