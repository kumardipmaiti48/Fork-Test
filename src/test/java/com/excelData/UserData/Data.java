package com.excelData.UserData;

import java.io.*;
import org.apache.poi.xssf.usermodel.*;
import org.testng.annotations.DataProvider;

public class Data {
	@DataProvider
	public Object[][] data() throws Exception{
		File file=new File("C:\\Users\\KMAITI\\OneDrive - Capgemini\\Desktop\\API Testing\\RestAssuredDemo\\src\\test\\java\\com\\excelData\\UserData\\userdata.xlsx");
		FileInputStream fis=new FileInputStream(file);
		XSSFWorkbook w=new XSSFWorkbook(fis);
		XSSFSheet s=w.getSheet("userdata");
		int totalRows=s.getPhysicalNumberOfRows();
		int totalColumns=s.getRow(0).getPhysicalNumberOfCells();
		Object[][] arr=new Object[totalRows][totalColumns];
		int firstRow=s.getFirstRowNum();
		int lastRow=s.getLastRowNum();
		for(int i=firstRow;i<totalRows;i++) {
			for(int j=0;j<totalColumns;j++) {
				arr[i][j]=s.getRow(i).getCell(j).toString();
			}
		}
		return arr;		
	}
}
