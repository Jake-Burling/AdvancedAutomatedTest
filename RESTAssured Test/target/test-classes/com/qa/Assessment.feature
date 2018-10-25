Feature: Assessment API

Scenario: Testing a pet clinic site
Given a users list exists that returns status code 200
  When a user is created and posted to the site
  And the same user is then updated
  Then searching for that user returns status code 200