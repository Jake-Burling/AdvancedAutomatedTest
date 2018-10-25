Feature: Assessment API

Scenario: Testing a pet clinic site
Given a users list exists
  When a user is created and posted to the site
  And the same user is then updated
  Then searching for that user