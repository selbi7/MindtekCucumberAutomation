@smoke @regression @HrAppFunctionalities
Feature: Validating HrApps functionalities
  @EditFunctionality
  Scenario: Validating edit functionality
    Given  User navigates to HrApp Page
    When user enters "Mindtek" for username and "MindtekStudent" for password and clicks edit button
    And user edits information with valid data
    Then user validates First name "John" Last name "Doe" Department Name "IT Support"

  @smoke @regression @HrAppFunctionalities @DeleteFunctionality
  Scenario: Validating delete functionality
    Given  User navigates to HrApp Page
    When user enters "Mindtek" for username and "MindtekStudent" for password and clicks edit button
    And user clicks on delete button
    Then user validates that employer was deleted


