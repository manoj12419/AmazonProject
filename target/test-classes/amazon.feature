#Author: manojnarikadapu@gmaio.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@assignment
Feature: Verify Department section 
  I want to verify the Verify second highest priced item in TV, appliances, Electronics in amazon 
  
 
  @Tv
  Scenario:  Buy a second highest priced  TV from amzon   
  Given I want to lunch <url> website in <browser> browser
   | browser | url                    |
   | Chrome  | https://www.amazon.in/ |
    And Click on Hamburger menu in the top left corner
    When Scroll down and then Click on the 'TV, Appliances, Electronics' link under 'Shop by Department' section
    Then Click on 'Televisions' under 'Tv, Audio & Cameras' sub menu
    And Scroll down and filter the results by <Brand>
    |Brand|
    |Samsung| 
    And Sort the Samsung results with 'Price: High to Low'
    And Click on the 2 nth highest priced item
    Then Veify the 'About this item' section is present for selected Item
    
    
      @Tv
  Scenario:  Buy a second highest priced  TV from amzon   
  Given I want to lunch <url> website in <browser> browser
   | browser | url                    |
   | Chrome  | https://www.amazon.in/ |
    And Click on Hamburger menu in the top left corner
    When Scroll down and then Click on the 'TV, Appliances, Electronics' link under 'Shop by Department' section
    Then Click on 'Televisions' under 'Tv, Audio & Cameras' sub menu
    And Scroll down and filter the results by <Brand>
    |Brand|
    |OnePlus| 
    And Sort the Samsung results with 'Price: High to Low'
    And Click on the 2 nth highest priced item
    Then Veify the 'About this item' section is present for selected Item
   
  

