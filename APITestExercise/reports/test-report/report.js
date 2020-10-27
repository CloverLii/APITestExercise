$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/APIFunc.feature");
formatter.feature({
  "line": 2,
  "name": "",
  "description": "As an API tester\nI want to confirm the correctness of API services",
  "id": "",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@SmokeTest"
    },
    {
      "line": 1,
      "name": "@Regression"
    }
  ]
});
formatter.scenario({
  "line": 7,
  "name": "Query a list of charities",
  "description": "",
  "id": ";query-a-list-of-charities",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 6,
      "name": "@GetAPI"
    },
    {
      "line": 6,
      "name": "@Charities"
    }
  ]
});
formatter.step({
  "line": 8,
  "name": "I connect to the API server",
  "keyword": "Given "
});
formatter.step({
  "line": 9,
  "name": "I send GET HTTP retrieve charities request",
  "keyword": "When "
});
formatter.step({
  "line": 10,
  "name": "I receive valid HTTP Response",
  "keyword": "Then "
});
formatter.step({
  "line": 11,
  "name": "St Johin is included in the list",
  "keyword": "And "
});
formatter.match({
  "location": "APITestStepDef.setUp()"
});
formatter.result({
  "duration": 202509985,
  "status": "passed"
});
formatter.match({
  "location": "APITestStepDef.queryCharities()"
});
formatter.result({
  "duration": 701519316,
  "status": "passed"
});
formatter.match({
  "location": "APITestStepDef.checkResponseCode()"
});
formatter.result({
  "duration": 1884973,
  "status": "passed"
});
formatter.match({
  "location": "APITestStepDef.vefiryGetResponse()"
});
formatter.result({
  "duration": 2075185,
  "status": "passed"
});
formatter.scenario({
  "line": 14,
  "name": "Retrieve a list of Used Cars",
  "description": "",
  "id": ";retrieve-a-list-of-used-cars",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 13,
      "name": "@GetAPI"
    },
    {
      "line": 13,
      "name": "@UsedCars"
    }
  ]
});
formatter.step({
  "line": 15,
  "name": "I connect to the API server",
  "keyword": "Given "
});
formatter.step({
  "line": 16,
  "name": "I send GET HTTP retrieve used cars request",
  "keyword": "When "
});
formatter.step({
  "line": 17,
  "name": "I receive valid HTTP Response",
  "keyword": "Then "
});
formatter.step({
  "line": 18,
  "name": "The car contains the information of Number plate, Kilometres, Body and Seats",
  "keyword": "And "
});
formatter.match({
  "location": "APITestStepDef.setUp()"
});
formatter.result({
  "duration": 21786,
  "status": "passed"
});
formatter.match({
  "location": "APITestStepDef.queryUsedCars()"
});
formatter.result({
  "duration": 100198368,
  "status": "passed"
});
formatter.match({
  "location": "APITestStepDef.checkResponseCode()"
});
formatter.result({
  "duration": 1119489,
  "error_message": "java.lang.AssertionError: response status code is not 200 expected [401] but found [200]\n\tat org.testng.Assert.fail(Assert.java:99)\n\tat org.testng.Assert.failNotEquals(Assert.java:1037)\n\tat org.testng.Assert.assertEqualsImpl(Assert.java:140)\n\tat org.testng.Assert.assertEquals(Assert.java:122)\n\tat org.testng.Assert.assertEquals(Assert.java:907)\n\tat cucumber.steps.APITestStepDef.checkResponseCode(APITestStepDef.java:71)\n\tat ✽.Then I receive valid HTTP Response(src/test/resources/APIFunc.feature:17)\n",
  "status": "failed"
});
formatter.match({
  "location": "APITestStepDef.confirmUsedCarsDetails()"
});
formatter.result({
  "status": "skipped"
});
});