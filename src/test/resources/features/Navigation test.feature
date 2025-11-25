Feature: Navigation tests

  Scenario: Open Google
    Given I navigate to "https://www.google.com"
    Then The page title should contain "Google"

  Scenario: Open Bing
    Given I navigate to "https://www.bing.com"
    Then The page title should contain "Bing"

  Scenario: Open DuckDuckGo
    Given I navigate to "https://duckduckgo.com"
    Then The page title should contain "DuckDuckGo"

  Scenario: Open YouTube
    Given I navigate to "https://www.youtube.com"
    Then The page title should contain "YouTube"
