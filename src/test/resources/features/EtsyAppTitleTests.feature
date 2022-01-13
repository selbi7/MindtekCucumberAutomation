@regression @UI @ONT-113
Feature: Validating etsy titles
#Jewelry & Accessories | Etsy
  Scenario Outline: Validating etsy separate page titles
    Given user navigates to Etsy application
    When user clicks on "<Section>" section
    Then user validates title is "<Title>"

    Examples:
      | Title                                        | Section                  |
      | Jewelry & Accessories \| Etsy                | Jewelery and Accessories |
      | End of Year Sales Event \| Etsy              | End of Year Sales Event  |
      | Clothing & Shoes \| Etsy                     | Clothing & Shoes         |
      | Home & Living \| Etsy                        | Home & Living            |
      | Wedding & Party \| Etsy                      | Wedding & Party          |
      | Toys & Entertainment \| Etsy                 | Toys & Entertainment     |
      | Art & Collectibles \| Etsy                   | Art & Collectibles       |
      | Craft Supplies & Tools \| Etsy               | Craft Supplies and Tools |
      | The Etsy Holiday Gift Guide for 2021 \| Etsy | Gift and Gift Cards      |
