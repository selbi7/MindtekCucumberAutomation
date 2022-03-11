@API @HRApis
Feature: Validating locations API calls
  Scenario Outline: Validating get location call
    Given   user sends get location api call with <locationId> locationId
    Then    user validates 200 status code
    And     user validates response body with data
    |locationCountry|<locationCountry> |
    |locationCity   |<locationCity>    |

    Examples:
    |locationId | locationCountry |locationCity |
    |1000       |IT               |Roma         |
    |1200       |JP               |Tokyo        |
    |1400       |US               |Southlake    |




    Scenario: Validating location API calls
      Given   user post location api call with data
      |locationCity|Brooklyn|
      |locationCountry|US   |
      |locationState  |NY   |
      #|locationId|generate|
      Then user validates 201 status code
      When user sends get location api call with generated locationId
      Then user validates 200 status code
      When user sends delete location api call with created locationId
      Then user validates 204 status code
      When user sends get location api call with generated locationId
      Then user validates 404 status code

