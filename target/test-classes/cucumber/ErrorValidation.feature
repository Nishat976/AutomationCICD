
@tag
Feature: Error validation
  I want to use this template for my feature file


  @tag2
  Scenario Outline: Title of your scenario outline
  	Given I landed on Ecommerce page
    When Logged in with username <username> and password <password>
    Then "Incorrect email or password." error message is dispalyed

    Examples:
    |			username 	        |		 password    	|
		|			nisha76@gmail.com |		Example@1223   |	
