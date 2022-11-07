#### Controller API Overview

## Overview
* Separates the internal representations of information between the view and model
* Accept inputs and converts it to commands for the model or view
* Handles events from both the model and view
  * For any new update needed to the model from the view or from the view to the model, the Controller handles events through the Event Listener and relays the appropriate commands to the target
* Creates an Event Listener object to listen for events and relays the information to the Controller
* To extend the controller, allow it to handle the events added

## Classes

## Example

## Details
* Interacts with the model and view by converting information given in events to commands to the other side
* Collaborates with the Event Listener API by receiving the information of events received and doing the appropriate logic to handle the event
* Can be extended by adding more internal functions that handle events and/or more functions that relay events to the model/view
* The API in the Controller will be mainly used to interface with the Event Listener; the interactions with the model/view are done through events

## Considerations
* Design decision: interactions done with outside classes are done solely through events
  * Pro: reduces design complexity and public methods, improves encapsulation
  * Cons: any interaction must be an event, no public methods allowed to be easily called