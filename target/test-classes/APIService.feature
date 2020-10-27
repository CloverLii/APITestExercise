@SmokeTest @Regression
Feature: 
  As an API tester
  I want to confirm the correctness of API services

@GetAPI @Charities
  Scenario: Query a list of charities
  	Given I connect to the API server
    When I send GET HTTP retrieve charities request
    Then I receive valid HTTP Response
    And St Johin is included in the list
    
@GetAPI @UsedCars
  Scenario: Retrieve a list of Used Cars
  	Given I connect to the API server
    When I send GET HTTP retrieve used cars request
    Then I receive valid HTTP Response
    And The car contains the details of Number plate, Kilometres, Body and Seats
    
