
Feature: Create a user account at http://mailchimp.com
I want to test the Sign Up Free function at mailchimp by attempting to create a user account in 
different scenarios.



  Scenario Outline: Create a user account at mailchimp.com
    Given I have entered an <email> address 
    And I have entered a <username>
    And I have entered a password
    And I have accepted the cookies
    When I click on the Sign Up button 
    Then This <outcome> is shown on screen

    Examples: 
      | email    | username | outcome                        |
      | "david.e.berglind@gmail.com"  | "newUser"   | "Success!"                    |
       | "david.e.berglind@gmail.com"  | "LongName"  | "The username is too long."   |
           | "david.e.berglind@gmail.com" | "InUse" | "Username is already in use." |
          | ""  | "newUser"   | "Email is missing."           |

      