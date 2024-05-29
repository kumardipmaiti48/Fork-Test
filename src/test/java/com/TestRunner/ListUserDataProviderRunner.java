package com.TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.*;

@RunWith(Cucumber.class)
@CucumberOptions(features="src\\test\\resources\\Features\\ListUserDataProvider.feature",glue="com.stepDefinition",
dryRun=true,monochrome=false,tags="@create or @multipleData or @put or @patch or @delete",plugin= {"pretty","html:target/report.html","json:target/userreport.json",
		"junit:target.userreport.xml","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})
public class ListUserDataProviderRunner extends AbstractTestNGCucumberTests{

}
