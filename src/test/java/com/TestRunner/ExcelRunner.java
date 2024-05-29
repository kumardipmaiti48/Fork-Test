package com.TestRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.testng.*;

@RunWith(Cucumber.class)
@CucumberOptions(features="src\\test\\resources\\Features\\Excel.feature",glue="com.stepDefinition",
dryRun=true,monochrome=false,tags="@excel2"/*,
	plugin= {"pretty","html:target/ExcelReport.html","json:target/ExcelReport.json",
		"junit:target/ExcelReport.xml","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}*/)
public class ExcelRunner extends AbstractTestNGCucumberTests{

}
