package cucumber.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/APIFunc.feature"}, 
	glue = {"cucumber.steps"},
	plugin ={"pretty","html:reports/test-report"},
	tags= "@SmokeTest")

public class APITestRunner {
}
