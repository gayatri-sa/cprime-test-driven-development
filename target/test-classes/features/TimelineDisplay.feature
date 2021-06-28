Feature: Timeline Display And Order of Updates
  Scenario: Timeline Display Order
    Given Timeline Database has at least 10 items
    When Timeline is initialized by consumer
    Then timeline should be loaded with 10 items from database
    And items should be sorted in descending order of date-time when items were created
  Scenario: Consumer requests to fetch more items
    Given Database has more than 20 items
    And Timeline is initialized by consumer
    When Consumer requests more items
    Then Timeline should be loaded with 10 more items
    And total item count should be 20
