@regression @smoke @UI @ONT-115
Feature: Validating WebOrders application login functionality

  Scenario: Validating login functionality with valid credentials
    Given user navigates to WebOrders application
    When  user provides username "Tester" and password "test" and clicks on login button
    Then  user validates application is logged in

    Scenario: Validating login functionality with invalid credential
      Given user navigates to WebOrders application
      When user provides username "Invalid" and password "invalid" and clicks on login button
      Then user validates error message "Invalid Login or Password."
