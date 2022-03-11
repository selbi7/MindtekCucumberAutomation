@API
Feature: Validating Books API calls

  Scenario: Validating end to end Order API calls
    Given   user sends post order api call with data
    |bookId|5|
    |customerName|Kim Yan|
    When    user sends get order api call
    Then    user validates status code 200
    When    user sends patch order api call
    |customerName|John Doe|
    Then    user validates status code 204
    When    user sends get order api call
    Then    user validates status code 200
    When    user sends delete order api call
    Then    user validates status code 204
    And     user sends get order api call
    Then    user validates status code 404
