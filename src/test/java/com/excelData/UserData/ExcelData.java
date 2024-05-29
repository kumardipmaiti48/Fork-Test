package com.excelData.UserData;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;
import java.io.*;

import org.apache.poi.xssf.usermodel.*;
import org.json.simple.JSONObject;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;

public class ExcelData {
	RequestSpecification req;
	Response res;
	JSONObject obj=new JSONObject();
	JsonPath data;
	File file;
	FileInputStream fis;
	XSSFWorkbook w;
	XSSFSheet s;
  
  @BeforeTest
  public void beforeTest() {
	  RestAssured.baseURI="https://reqres.in/";
	  req=RestAssured.given();
  }
  
  @Test
  public void createUser() throws Exception{
	  file=new File("C:\\Users\\KMAITI\\OneDrive - Capgemini\\Desktop\\API Testing\\RestAssuredDemo\\src\\test\\java\\com\\excelData\\UserData\\userdata.xlsx");
	  fis=new FileInputStream(file);
	  w=new XSSFWorkbook(fis);
	  s=w.getSheetAt(0);
	  //s=w.getSheet("userdata");
	  String name=s.getRow(1).getCell(0).toString();
	  String job=s.getRow(1).getCell(1).toString();
	  obj.put("name", name);
	  obj.put("job", job);
	  req.headers("Content-Type","application/json");
	  res=req.body(obj.toJSONString()).post("api/users");
	  data=res.jsonPath();
	  int row=s.getPhysicalNumberOfRows();
	  int column=s.getRow(0).getPhysicalNumberOfCells();
	  System.out.println(row);
	  System.out.println(column);
	  System.out.println(res.asPrettyString());
	  System.out.println(res.statusLine());
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
