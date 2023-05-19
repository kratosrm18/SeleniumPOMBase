package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith( Cucumber.class )
@CucumberOptions(

        features = "@target/failed_scenarios.txt",
        glue = { "stepDefinitions" }, monochrome = true,
        plugin = {
                "pretty",
                "html:target/cucumber.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" } )
public class FailedTestRunner {

}