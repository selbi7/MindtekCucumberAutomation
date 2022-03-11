@API @MB3-58
Feature: Validating Bank api calls
  Scenario: Validating limit query parameter for customers api call
    Given   user gets all customers with api call with limit 3
    Then    user validates bank api status code is 200
    And     user validates that response includes 3 customers only

