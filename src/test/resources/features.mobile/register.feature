@test
Feature: Login/Logout from the application
  Background: I access the register page
    Given  I skip tutorial
    And I access the loginPage from the navigation bar
    And I access the registerPage from the loginPage

  Scenario: Registering new user
    When I fill data and submit
    Then I should be logged
  Scenario: returning backward
    When I get back to login
    Then I should be logged out
