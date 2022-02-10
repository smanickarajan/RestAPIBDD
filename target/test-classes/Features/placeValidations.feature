Feature: Validating place API's

  @Addplace
  Scenario Outline: Verify if place is succesfully added usng Addplace API
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user calls "AddplaceAPI" with "POST" http request
    Then API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id created maps to "<name>" using "GetplaceAPI"

    Examples: 
      | name     | language | address            |
      | AA House | English  | World cross center |

  #    | BB House | English  | Sea cross center   |
  
  
  @Deleteplace
  Scenario: Verify the delete place functionality
    Given DeletePlace Payload
    When user calls "DeleteplaceAPI" with "POST" http request
    Then API call got success with status code 200
    And "status" in response body is "OK"
