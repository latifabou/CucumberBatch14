Feature: Login functionality
  Scenario: Valid admin login
    Given user is navigated to HMHs application
    When user enters valid username and valid password
    And user clicks on login button
    Then user is successfully logged in