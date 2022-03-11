@MB3-57
Feature: Validating jobs api calls

  Scenario: Job api calls validation
    Given   user creates job with POST api call
    Then    user validates that status code is 201
    When    user navigates to login page
    And     user logs in to HR App
    Then    user validates that job was created in UI in job title dropdown in Create new employee page
    Then    user validate get jobs api call response includes created job
    Then    user validates that status code is 200
    Then    user validate get job api call responding created job
    Then    user validates that status code is 200
   When    user sends Delete api call to the new created job
   Then     user validates status code 204
    Then    user validates job is not shown in UI in Job Title dropdown
    When    user gets job api call responding created job.
    Then    user validates that status code is 404





