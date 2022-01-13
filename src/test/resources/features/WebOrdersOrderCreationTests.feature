@regression @UI @MBP-120
Feature: Validating calculate and order creation functionalities

  Scenario Outline: Validating calculate functionality
    Given user navigates to WebOrders application
    When user provides username "Tester" and password "test" and clicks on login button
    And user clicks on Order module
    And user selects "<product>" product with Integer <quantity> quantity
    Then user validates total is calculated correctly for quantity <quantity>

    Examples:
      | product     | quantity |
      | MyMoney     | 1        |
      | FamilyAlbum | 100      |
      | ScreenSaver | 55       |
      | MyMoney     | 20       |

  @MB2-413
  Scenario Outline: Validating create order functionality
    Given user navigates to WebOrders application
    When user provides username "Tester" and password "test" and clicks on login button
    And user counts number of orders in table
    And user clicks on Order module
    And user creates order with data
      | order   | quantity   | name   | street   | city   | state   | zip   | cc   | expire date   |
      | <order> | <quantity> | <name> | <street> | <city> | <state> | <zip> | <cc> | <expire date> |
    Then user validates success message "New order has been successfully added."
    And user validates order added to List of Orders
    Examples:
      | order       | quantity | name        | street      | city        | state | zip   | cc               | expire date |
      | MyMoney     | 1        | John Doe    | 123 My Road | Chicago     | IL    | 12345 | 1234123412341234 | 12/21       |
      | FamilyAlbum | 5        | Patel Harsh | 123 My Road | New York    | NY    | 12311 | 1111123412341234 | 12/21       |
      | ScreenSaver | 50       | Kim Yang    | 123 My Road | Los Angeles | CA    | 12310 | 2222123412341234 | 12/21       |