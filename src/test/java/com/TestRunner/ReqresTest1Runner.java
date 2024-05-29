package com.TestRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.testng.*;

@RunWith(Cucumber.class)
@CucumberOptions(features="src\\test\\resources\\Features\\reqres.feature",glue="com.stepDefinition")
public class ReqresTest1Runner extends AbstractTestNGCucumberTests{

}
