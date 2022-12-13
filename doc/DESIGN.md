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

#### What Features are Easy to Add


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

#### Features Affected by Assumptions


## Significant differences from Original Plan


## New Features HowTo

#### Easy to Add Features

#### Other Features not yet Done

