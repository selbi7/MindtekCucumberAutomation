@HRDepartmentsApis @API
Feature: Validating departments API calls
  Scenario: Validating department was created with API post call and shown in UI
    Given   user creates departments with departments post call having random data
    Then    user validates statusCode 201
    When    user gets created department
    Then    user validates statusCode 200
    When    user navigates to login page
    When    user logs in to HR App
    Then    user validates created department is in departments dropdown
    When    user deletes created department
    Then    user validates statusCode 204
    When    user gets created department
    Then    user validates statusCode 404

