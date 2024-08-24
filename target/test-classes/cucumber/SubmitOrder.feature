
@tag
Feature: Purchase order from Ecommerce Website
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce page
  

  @Regression
  Scenario Outline: Positive test of purchasing order
    Given Logged in with username <username> and password <password>
    When I add the product <Productname> to cart
    And checkout <Productname> and submit the order
    Then "Thankyou for the order." message is displayed for the confirmation
    
    Examples:
    |			username 	        |		 password    	| 	Productname |
		|			nisha76@gmail.com |		Example@123   |		ZARA COAT 3	|
  