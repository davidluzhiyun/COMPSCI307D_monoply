#### Test Plan

## Specific strategies to make API testable
* Keep non-main classes small and make all public methods short
* Reduce the length of parameter lists to avoid unwieldy parameter lists
  * Avoid Single Responsibility violation

## Test scenarios for project features
# Controller:
* Handle interactions with the view through event handling (e.g. receiving a roll dice event)
  * Tested through creating some information related to a specific event that will be passed to the controller
  * The controller will then create another event to pass to the model
  * Test for the correct event created
* Handle interactions with the model through event handling (e.g. receiving an updateView event)
  * Tested through creating some information related to a specific event that will be passed to the controller
  * The controller will then create another event to pass to the model
  * Test for the correct event created
* Interact with an event listener class whose main purpose is to listen for events and relay information to Controller
  * Tested through creating a mock event that is passed to the event listener class
  * Check to see if it relays the correct information
  * Negative test: test with a mock event that is not supposed to be handled
    * Nothing should be passed on