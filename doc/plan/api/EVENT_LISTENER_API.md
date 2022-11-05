#### Event Listener API Overview

## Overview
* Interact with the Controller when the Event Listener receives an event from either the view/controller
* To extend, allow for more events to be listened for

## Classes

## Example

## Details
* Listens for events from the model/view whenever either needs to be updated from the other
  * i.e. whenever the game state needs to be changed, events are sent and listened to by the event listener
* The main API for the Event Listener is to interact with the Controller so it can handle the events that have been received
* To extend, allow the Event Listener to receive more events and create the appropriate functions necessary to communicate to the Controller

## Considerations