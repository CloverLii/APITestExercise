package cucumber.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/APIService.feature", 	//the path of feature file
	glue = {"cucumber.steps"},	//the package name of step definitions file
	plugin = {"html:reports/cucumber-html-report", 
			"json:reports/cucumber-report.json",
			"junit:reports/cucumber-result.xml"},
	tags= "@SmokeTest")

//Entry to execute the UI tests
public class APITestRunner {
}
