Feature: User Management

  @PostDelete
  Scenario: Successfully create a new user
    Given I have a valid admin access token
    When I try to create a new user with valid details
    Then I should get a successful response
    And the response header should contain the created user's ID

  Scenario: Fail to create a user with missing required fields
    Given I have a valid admin access token
    When I try to create a new user with incomplete data
    Then the 400 response should contain an error message Missing required fields

  @PostDelete
  Scenario: Successfully retrieve a user
    Given I have a valid admin access token
    And  a user exist
    When I try to retrieve that user
    Then I should get a successful response as status code 200

  Scenario: Fail to retrieve a non-existing user
    Given I have a valid admin access token
    When I try to retrieve a user by a non-existing ID
    Then I should get a 404 Not Found response

 @PostDelete
  Scenario: Successfully update a user's details
    Given I have a valid admin access token
    And a user exists
    When I try to update that user's details with valid data
    Then the user's details should be updated successfully

  Scenario: Fail to update a non-existing user
    Given I have a valid admin access token
    When I try to update a non-existing user with ID
    Then I should get a 404 Not Found response with error message

  @PostDelete
  Scenario: Successfully list all users
    Given I have a valid admin access token
    And a user exists
    When I try to list all users
    Then the response should contain a list of users

  Scenario: Successfully count the number of users
    Given I have a valid admin access token
    When I try to count the number of users
    Then the response should return the correct count of users

  Scenario: Successfully delete a user
    Given I have a valid admin access token
    And a user exists
    When I try to delete that user
    Then the user should no longer exist

  Scenario: Fail to delete a non-existing user
    Given I have a valid admin access token
    When I try to delete a non-existing user
    Then I should get a 404 Not Found response in delete

  @PostDelete
  Scenario: Successfully disable a user
    Given I have a valid admin access token
    And a user exists
    When I try to disable that user
    Then the user should be disabled
