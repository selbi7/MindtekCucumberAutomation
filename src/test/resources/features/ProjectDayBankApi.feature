@BankApiProject
Feature: Validating balance update for an account api calls
  Scenario: Validating balance update for an account with api
    Given user creates a customer with POST call
    Then user validates status code will be 201
    When user creates an account for created customer with POST call
    Then user validates status code will be 201
    When user GETS account created for the customer
    Then user validates status code will be 200
    When user updates account balance with PUT call
    Then user validates status code will be 200
    When user validates that account balance is updated with GET customer call
    Then user validates status code will be 200
    When user deletes account with DELETE call
    Then user validates status code will be 204
    When user deletes customer with DELETE call
    Then user validates status code will be 204
    Then user validates that customer and account is deleted with GET call
    And user validates status code will be 404
    And user validates the response message "Customer not found with ID "