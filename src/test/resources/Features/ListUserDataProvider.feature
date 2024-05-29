Feature: Creating User
Background: # whatever is common lines is there we have to write in background only it acts like global for all scenerio.
		Given User is on Reqres page
	
	@create
  Scenario: Create Users   
    When User Enters name and job
    |Kumardip|Analyst|
    |Suraj|Sr. Analyst|
    And User hits the API
    Then User should be created
    
	@multipleData	
	Scenario Outline: Create data with multiple data
    When User enter "<name>" and "<job>"
    And User hit the API
    Then User should be create
    
    Examples:
    |name|job|
    |Kumardip|Analyst|
    |Subhadip|Manager|
    |Suraj|Manager|
    
	@put
	Scenario: To update a value with put
		When user enters job
		|Senior Analyst|
		And User hits the put API
		Then User provided value should updated
		
	@patch
	Scenario: To update a value with patch request
		When User enters a name
		|Analyst|
		And User hits the patch API
		Then Patch method work successfully	
		
	@delete
	Scenario: To Delete a record
		When 	User hits the delete API
		Then Record should delete
