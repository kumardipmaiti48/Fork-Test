package com.restAssured.RestAssuredDemo;

import org.testng.annotations.DataProvider;

public class DataProviderTest {
	@DataProvider(name="userdata")
	public Object[][] userData(){
		Object[][] data=new Object[4][2];
		data[0][0]= "Kumardip";
		data[0][1]="Analyst";
		
		data[1][0]= "Subhadip";
		data[1][1]="Manager";
		
		data[2][0]= "Suraj";
		data[2][1]="Software Engineer";
		
		data[3][0]= "Sayan";
		data[3][1]="Senior Analyst";
		return data;
	}

}
