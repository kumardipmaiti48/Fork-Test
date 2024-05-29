Feature: Adding Multiple users

  Scenario Outline: Adding Multiple users using outline
    Given User already on reqres URL
    When User enters "<name>" and "<job>"
    And User click on post
    Then The user should be created

    Examples: 
      | name  | job |
      | Kumardip | Analyst |
      | Suraj | Sr. Analyst |
