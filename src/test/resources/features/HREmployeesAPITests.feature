@HREmployeesApis @API
Feature: validating employees api calls
  Scenario Outline: Validating DB, UI and API data matching for employee
    Given user gets employee from DB with employeeId <employeeId>
    When  user navigates to login page
    And   user logs in to HR App
    And   user searches for employee with <employeeId> employeeId
    Then  user validates UI data matches with DB
    When  user gets employee with get employee api call with <employeeId> employeeId
    Then  user validates 200 statusCode
    And   user validates API data matches with DB

    Examples:
    |employeeId|
    |120       |
    |123       |
    |206       |

    @MB3-55
    Scenario: creating employee with post api call
      Given   user creates employee with post api call and with data
      |firstName|Jennifer |
      |lastName|Lopez     |
      |departmentName|Administration|
      Then  user validates 201 statusCode
      When  user navigates to login page
      And   user logs in to HR App
      And   user searches for created employee
      Then  user validates employee is created in UI with data
        |fistName|Jennifer |
        |lastName|Lopez     |
        |departmentName|Administration|

      @MB3-56
      Scenario: Validating number of employees in specific department
        Given   user gets number of employees in "IT" department from DB
        When  user navigates to login page
        And   user logs in to HR App
        And   user selects "IT" department from the dropdown
        Then  user validates UI number of employees batches with DB number
        When  user gets employees from "IT" department with  api call
        Then  user validates number of employees in API matches with DB number


