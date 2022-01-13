@regression @smoke @MB2P-122
Feature: Validating pizza application functionality

  Scenario Outline: Validating place order functionality
    Given user navigates to pizza application
    When user creates pizza order with data
      | Pizza        | <Pizza>        |
      | Toppings 1   | <Toppings 1>   |
      | Toppings 2   | <Toppings 2>   |
      | Quantity     | <Quantity>     |
      | Name         | <Name>         |
      | Email        | <Email>        |
      | Phone        | <Phone>        |
      | Payment Type | <Payment Type> |
    Then user validates that order is created with success message "Thank you for your order! TOTAL: " "<Pizza>"

    Examples:
      | Pizza                        | Toppings 1 | Toppings 2   | Quantity | Name        | Email                | Phone       | Payment Type   |
      | Small 6 Slices - no toppings | Mushrooms   | Extra cheese | 1        | John Doe    | johndoe@gmail.com    | 1234567788  | Cash on Pickup |
      | Large 10 Slices - 2 toppings  | Mushrooms  | Extra cheese | 3        | Patel Harsh | patelharsh@gmail.com | 12344445566 | Credit Card    |
      | Medium 8 Slices - 2 toppings  | Olives     | Salami       | 2        | Kim Yang    | kimyang@gmail.com    | 1112223344  | Cash on Pickup |