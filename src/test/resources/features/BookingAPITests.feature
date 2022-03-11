@API @MB3-101
Feature: Validating api calls for booking
  Scenario: Validating booking api calls with valida data
    Given   user sends create booking api POST call with data
    |firstName|Jim|
    |lastName |Brown|
    |totalPrice|111 |
    |depositPaid|true|
    |checkIn|2018-01-01|
    |checkOut|2019-01-01|
    |additionalNeeds|Breakfast|

    Then user validates status code 200 fro booking
    And  user validates that booking is created with api GET call
    When user updates booking with api PATCH call with data
    |firstName|James|
    Then user validates status code 200 fro booking
    And  user validates that booking is created with api GET call
    When user deletes booking with api DELETE call
    Then user validates status code 201 fro booking
    And  user validates booking is deleted and GET call has 404 status code