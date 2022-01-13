@HrAppNewEmployee
Feature: Creating a new employee in Hr App
  Scenario Outline: User creates a new employee in Hr App
    Given User navigates to HR App "<Login>" Page
    When user enters "Mindtek" for username and "MindtekStudent" for password and clicks login button
    And User clicks on Create new employee tab
    And User provides data for the input fields and clicks on SAVE button

    Then User validates new employee is created


    Examples:
      | Login                                      |

      | https://qeenv-webhr-arslan.herokuapp.com/  |