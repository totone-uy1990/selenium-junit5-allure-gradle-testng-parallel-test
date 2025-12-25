# language: en
Feature: Login Flow Challenge
  As a registered user
  I want to authenticate into the application
  So that I can access my secure account dashboard

  Background:
    Given the user is on the Cnarios test_login page "https://www.cnarios.com/challenges/login-flow"

  @smoke @happy_path
  Scenario: Successful login with valid credentials
    When the user enters the username "admin"
    And the user enters the password "admin123"
    And clicks on the login button
    Then the user should be redirected to the "Dashboard" page
    And the user should see a welcome message containing "Welcomes"

#  @regression @negative_testing
  #Scenario Outline: Failed login with invalid credentials
  #  When the user enters the username "<username>"
   # And the user enters the password "<password>"
    #And clicks on the login button
    #Then the user should see an error message saying "<error_message>"

   # Examples:
     # | username       | password      | error_message       |
     # | wrongUser      | admin123      | Invalid username    |
     # | admin          | wrongPass     | Invalid password    |
     # | wrongUser      | wrongPass     | Invalid credentials |

  #@ui @validation
  #Scenario: Login validation with empty fields
  #  When the user leaves the username field empty
   # And the user leaves the password field empty
    #And clicks on the login button
   # Then the user should see a validation message "This field is required" under the username field
   # And the user should see a validation message "This field is required" under the password field