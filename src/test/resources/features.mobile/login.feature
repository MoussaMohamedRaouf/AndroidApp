@test
Feature: Login/Logout from the application
  Background: A user Access and Logs to the app
    Given  I skip tutorial
    And I access the loginPage from the navigation bar

    Scenario: check if im connected
      When I log with the email "GENERIC_EMAIL" and the password "GENERIC_PASSWORD"
      Then I should be logged

    Scenario: A user logs out of the website
      When I log with the email "GENERIC_EMAIL" and the password "GENERIC_PASSWORD"
      And I log out
      Then I should be logged out