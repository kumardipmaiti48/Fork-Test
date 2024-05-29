package com.TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.*;

@RunWith(Cucumber.class)
@CucumberOptions(features="src\\test\\resources\\Features\\Authorize.feature",glue="com.featuresTest")
public class AuthorizeRunner {

}
