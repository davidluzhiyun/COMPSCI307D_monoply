# OOGA Design Final
### Names
Elaine Guo, Hosung Kim, David Lu, Leila Nelson, Luyao Wang

## Team Roles and Responsibilities

 * Team Member #1
   * Elaine Guo: designed and implemented controller

 * Team Member #2
   * Hosung Kim: designed and implemented view; main focus on board implementation and design and game editor

 * Team Member #3
   * Leila Nelson: designed and implemented view; main focus on auxiliary view features and game integration

 * Team Member #4
   * David Lu: designed and implemented model; main focus on auxiliary model features

 * Team Member #5
   * Luyao Wang: designed and implemented model; main focus on backend game engine


## Design goals
* A design that supports:
  * Encapsulation
  * Abstraction

#### What Features are Easy to Add
* New and different properties
* More UI features, such as popups and more information text

## High-level Design
The high-level design of this project is to split the functionality of the game between the model, view and controller.
To encourage encapsulation, the communication between these three main components is done through publishing and handling
events.

#### Core Classes
View: the UI of the game. This includes the board, game editor, game pieces (cards, player pieces, etc.), and popups. The
view communicates most of its events to the model whenever a user wants to do something.

Model: the backend game engine of the game. The model deals with any game logic and information storing necessary to run
the game, such as the current board state and all player information.

Controller: responsible to interpreting information from the model to pass to the view in a parsable manner. Also responsible
for extraneous view events that does not update or need the model to function, like getting the place information of a 
specific spot on the board.


## Assumptions that Affect the Design
* If the user wants to manually upload a configuration file to the game, they know the correct format of 
the json files.
* The users know how monopoly works since each feature does not have an instruction with it

#### Features Affected by Assumptions
* The types of configuration files are limited since they have to follow a specific format
* New users to monopoly cannot play the game


## Significant differences from Original Plan
* A couple model APIs have changed to reduce redundancy and for easier game communication/implementation
* The controller needs to store several variables in order to properly load, start, and run games


## New Features HowTo
How to add more features:
* Make a UI component to show the new feature/allow the user to interact with it
* Add functionality in the view to send an event to the model to do the new feature
* Implement the necessary backend logic in order for the new feature to occur
* Implement the necessary controller logic to interpret and parse the necessary information from the model output to give back to the view.

#### Easy to Add Features
* New and different properties
* New UI features, such as popups and more information displayed

#### Other Features not yet Done

