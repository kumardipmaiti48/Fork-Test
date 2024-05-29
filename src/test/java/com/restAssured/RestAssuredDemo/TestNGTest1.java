package com.restAssured.RestAssuredDemo;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class TestNGTest1 {
	
  @Test
  public void f() {
	  System.out.println("Kumardip");
  }
  @BeforeTest
  public void beforeTest() {
	  System.out.println("Hello");
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("Welcome");
  }

}
