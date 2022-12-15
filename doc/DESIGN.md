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
* For the most part, the view is sending its events to the model directly versus to the controller which sends the corresponding event to the model.

## New Features HowTo
How to add more features:
* Make a UI component to show the new feature/allow the user to interact with it
  * Update properties files
* Add functionality in the view to send an event to the model to do the new feature
* Implement the necessary backend logic in order for the new feature to occur
* Implement the necessary controller logic to interpret and parse the necessary information from the model output to give back to the view.

#### Easy to Add Features
* New and different properties
* New UI features, such as popups and more information displayed
* Finishing Jail implementation: need to add a new method within MonopolyBoardBuilder to place a 
    user at the appropriate property index that represents Jail. This will be called when the model
    sends an event indicating the user should be sent to jail, which will be easy to do by simply updating the GameViewReflection.properties file once the appropriate method has been made.
* Adding in UI support for rolling doubles as another variation in game play, since the model already has support for this feature. The view can easily make a pop-up to announce that the user has rolled doubles, and present them with the RollDicePopUp again.


#### Other Features not yet Done
* Chance/community cards.
  * Some aspects of this would be relatively easy to make, since we already have support for different cards via our .json files & CardBuilder classes in View.
  * In a simple implementation, each card could fall into roughly two categories: moving a player OR changing a player's money (or changing the money of several players). Since we do have support for both of these types of changes, we would not necessarily need to make extensive changes to the view or the model, BUT the file parsing would be another thing to figure out, since we would need to somehow parse the action and all the important information out of the card file.
* Auctions/trading


