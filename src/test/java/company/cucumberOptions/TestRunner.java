package company.cucumberOptions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)

@CucumberOptions(features = "src/test/java/company/features",
        glue = "company.stepDefinitions",
        plugin = {"json:target/jsonReports/cucumber_report.json"}

)

public class TestRunner {

}
