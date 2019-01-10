Feature: Login Tests for phpTravels

  Scenario: Valid Login
    Given I am a user of phpTravels.com
    When I log in using valid credentials
    Then  I should be logged in

  Scenario: Re-login
    Given I am a user of phpTravels.com
    When I log in using valid credentials
    Then  I should be logged in
    Given I log out
    Then I log in using valid credentials
    And I should be logged in

  Scenario: Invalid Login
    Given I am a user of phpTravels.com
    When I log in using invalid credentials
    Then I should not be logged in

  Scenario: My Profile
    Given I am a user of phpTravels.com
    When I log in using valid credentials
    Then I should be logged in
    When I want to visit my profile settings
    Then I want to change phone number to "9999999"
    Then My phone number has to be changed to "9999999"

  Scenario: Search and book hotel
    Given I am a user of phpTravels.com
    When I log in using valid credentials
    Then  I should be logged in
    And I searches hotels in the "istanbul" with check in date "02/02/2019" and checkout date "02/02/2019"
    Then I book cheapest room of cheapest hotel
    Then I should see the invoice
    Then My book should be in my account with same Book id
    #refactor

  Scenario Outline: Users able to login only their sections
    Given I am an <userType> user of phpTravels
    Then As a <userType> user I should not be able to login on <page> site
    Examples:
      | userType | page            |
      | admin    | defaultUserPage |
      | admin    | supplierPage    |
      | supplier | defaultUserPage |
      | supplier | adminPage       |
      | default  | adminPage       |
      | default  | supplierPage    |



