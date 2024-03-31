Feature: CRUD Place Functionality

@addPlace @regression @p0
  Scenario: Verify add place api works fine
    Given I have payload for the "AddPlaceAPI" endpoint
    When I hit the "POST" request for the "AddPlaceAPI" endpoint
    Then I verify status code is 200
    And I verify "status" field in response body is "OK"
    And I verify "scope" field in response body is "APP"
    And I verify "Server" header in response is "Apache/2.4.52 (Ubuntu)"
    And I store value of "place_id" from the response body
   
@getPlace @regression @p0
   Scenario: Verify get place api works fine
    Given I have payload for the "GetPlaceAPI" endpoint
    When I hit the "GET" request for the "GetPlaceAPI" endpoint
    Then I verify status code is 200

@updatePlace @regression @p1
    Scenario: Verify update place api works fine
    Given I have payload for the "UpdatePlaceAPI" endpoint
    When I hit the "PUT" request for the "UpdatePlaceAPI" endpoint
    Then I verify status code is 200
    And I verify "msg" field in response body is "Address successfully updated"

@deletePlace @regression @p0     
   Scenario: Verify delete place api works fine
    Given I have payload for the "DeletePlaceAPI" endpoint
    When I hit the "DELETE" request for the "DeletePlaceAPI" endpoint
    Then I verify status code is 200
    And I verify "status" field in response body is "OK"