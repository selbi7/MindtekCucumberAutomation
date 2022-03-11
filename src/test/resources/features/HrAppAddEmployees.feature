@JDBC @regression
Feature: Validate create employee functionality
  @JDBC_TC01 @smoke
  Scenario: Validate create employee persisted in DataBase
    Given   user navigates to login page
    When    user logs in to HR App
    And     user creates new employee
    Then    user validates new employee is created in Data Base