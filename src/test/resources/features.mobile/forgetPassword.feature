@test
Feature: Forget password operation
  Background: accessing loginPage
    Given  I skip tutorial
    And I access the loginPage from the navigation bar

  Scenario: real account forget password operation
    When I access the forgetPasswordPage and enter the email "RESET_EMAIL" and submit
    And I navigate to navigator and get the code
    And I get back to app and change password
    When I log with the email "RESET_EMAIL" and the password "RESET_PASSWORD"
    Then I should be logged


  Scenario: random account forget password operation
    When I access the forgetPasswordPage and enter the email "RANDOM_EMAIL" and submit
    Then I should get an error
