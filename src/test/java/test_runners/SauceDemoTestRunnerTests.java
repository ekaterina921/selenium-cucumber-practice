package test_runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/features",
        glue = {"step_definitions"},
        plugin = {
                "pretty",
                "html:target/cucumber-Report.html", "json:target/report.json"},
        tags = "@Regression")
public class SauceDemoTestRunnerTests extends AbstractTestNGCucumberTests {
}
