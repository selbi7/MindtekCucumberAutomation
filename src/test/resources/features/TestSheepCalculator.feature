@regression @smoke @MB3P-101 @ui
Feature: Validating calculate Functionality in TestSheep
  Background:
    Given user navigates to TestSheep application
  Scenario Outline: validating calculate functionality with valid inputs
    Given user navigates to TestSheep application
    When  user enters "<Number1>" as first number
    And   user user enters "<Number2>" as second number
    And   user selects add "<Operation>" operator
    And   user clicks on calculate button
    Then  user validates output is <Answer>
    Examples:
      | Number1 | Number2 | Operation   | Answer |
      | 6       | 2       | Add         | 8      |
      | 10      | 5       | Subtract    | 5      |
      | 2       | 3       | Multiply    | 6      |
      | 4       | 1       | Divide      | 4      |
      | 1       | 2       | Concatenate | 12     |

  @regression
  Scenario Outline: Validating calculate functionality with invalid input
    Given user navigates to TestSheep application
    When  user enters "<Number1>" as first number
    And   user user enters "<Number2>" as second number
    And   user selects add "<Operation>" operator
    And   user clicks on calculate button
    Then user validates "<ErrorMessage>" error message
    Examples:
      | Number1 | Number2 | Operation | ErrorMessage             |
      | abc     | abc     | Add       | Number 1 is not a number |
      | 5       | abc     | Add       | Number 2 is not a number |
      | 5       | 0       | Divide    | Divide by zero error!    |


