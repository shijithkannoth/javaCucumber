@smoke
Feature: This is a sample test feature file to automate the Amazon add to cart feature

Background: Login to the Application and Navigate to the Dashboard Page
    Given I am on the login page
    When I enter the username and password
    And I click on login button
    Then I should be able to login successfully

      @validateCards
    Scenario Outline: Add a product to the basket and verify the card failure reasons in the payment page
      When I search "<product>" in search bar
      And add first product to the basket
      And I go to the basket
      And I go to Basket and verify the product added
      And I click on proceed to checkout
      And select the existing address from the saved list
      And I select payment card method
      And I validate the card with below data
        |card-number|name|cvv|month|error|
        |5432267818901264|Test Automation|662|12|Card number is not correct.|
        |5105 1051 0510 5100|Test Automation|662|01|Expiration date is not correct.|
        |5105 1051 0510 5100|Test Automation|0|12|Security code (CVV) is not correct. Look for the 3-digit code on the back of the card, near the signature line.|

      Examples:
      |product|
      |badminton Racket|

      @modifyBasket
    Scenario: Add/Modify basket and verify the basket is updated
      Then I check if basket is empty
      And I go to Basket and verify the product added
      Then I modify the number of Items
      Then I verify the updated items in the checkout page
