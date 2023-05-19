package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import lombok.ToString;
import org.junit.runner.RunWith;

@ToString
@RunWith(Cucumber.class)
@CucumberOptions(

        features = "src/test/java/features",
        glue = {"stepDefinitions"},
        stepNotifications = true,
        monochrome = true,
        tags = "@Sanity",
        plugin = {
                "pretty",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "rerun:target/failed_scenarios.txt"
        })

public class TestRunner {

}
