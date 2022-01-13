@MT-123
Feature: Validating HrApp's edit functionality

  Scenario : Validating edit functionality providing different credentials
    Given  User navigates to HrApp Page
    When user enters "Mindtek" for username and "MindtekStudent" for password and clicks edit button
    And user provides <Box> and "<Data>"
      | Box             | Data |
      | First Name      | John |
      | Last Name       | Doe  |
      | Department Name | IT   |
