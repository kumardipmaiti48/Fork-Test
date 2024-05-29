Feature: Creating a new user

  Scenario: Create new user
    Given user is on reqres url
    When user clicks on post api
    Then user should be created
