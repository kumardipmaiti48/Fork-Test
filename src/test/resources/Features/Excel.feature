@full
Feature: Create user using excel

Background:
	Given User is on Reqres URL
	
  @excel1
  Scenario: multiple data
    When User give name and job
    And User hit the post button
    Then User should be created.

  @excel2
  Scenario Outline: multiple data1
    When User give name and job from excel
    And User hit the post button1
    Then User should be created1

    #Examples: 
      #| sheet | row |
      #| data |   0  |
      #| data |   1  |
      #| data |   2  |
      #| data |   3  |
      #| data |   4  |
