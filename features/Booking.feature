Feature: All bookings.com scenarios
  https://www.booking.com/searchresults.en-gb.html?ss=Goa&ssne=Goa&ssne_untouched=Goa&efdco=1&label=gen173nr-1FCAEoggI46AdIM1gEaGyIAQGYAQm4ARfIAQzYAQHoAQH4AQuIAgGoAgO4Asyv4rIGwAIB0gIkZmY5ZTBjY2YtMThkMC00ZDY5LWE0ZmUtODkxNDA4NGU0NGRl2AIG4AIB&sid=8918bb74d902fb87b582710b0a1c8fc6&aid=304142&lang=en-gb&sb=1&src_elem=sb&src=index&dest_id=4127&dest_type=region&checkin=2024-06-13&checkout=2024-06-16&group_adults=2&no_rooms=1&group_children=0



# 1
  @starverify
  Scenario Outline: Verify user can only view the result by selected property class
    Given I am on default locations search result screen
    When I select option for stars as <stars>
    Then I verify system displays only <stars> hotels on search result
    Examples:
      | stars   |
      | 5 stars |
    #  | 4 stars |
    #  | 3 stars |


#2
  @maxAmount
  Scenario: check the hotel prices are below a certain amount
    Given I am on default locations search result screen
    Then I verify system displays all hotels within "20000" amount


#3
  @hotelsSearch
  Scenario: verify given hotel is present in the list
    Given I am on default locations search result screen
    Then I verify "Sea Sapphire" is within the search result
