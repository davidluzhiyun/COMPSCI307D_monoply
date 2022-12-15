ooga
====

This project implements a player for multiple related games.

Names: Elaine Guo, Hosung Kim, Luyao Wang, Zhiyun (David) Lu, Leila Nelson

### Timeline

Start Date: November 2nd

Finish Date: December 15th

Hours Spent: too many to count.

### Primary Roles

* Elaine Guo: designed and implemented controller

* Hosung Kim: designed and implemented view; main focus on board implementation and design and game
  editor

* Leila Nelson: designed and implemented view; main focus on auxiliary view features and game
  integration

* David Lu: designed and implemented model; main focus on auxiliary model features

* Luyao Wang: designed and implemented model; main focus on backend game engine

### Resources Used

* Course material/code examples, knowledge from prior projects.

* https://edencoding.com/scene-background/#:~:text=The%20simplest%20way%20to%20set%20the%20JavaFX%20Scene,background%2C%20which%20can%20accept%20multiple%20images%20and%20fills
    * Used for reference with images in JavaFX

### Running the Program

Main class: Main.java -- just press run and it will start up our game!

Data files needed: data/config_files folder -- appropriate configuration files (.json). Refer to our
examples
such as InitConfigNoJail.json, InitialConfigJail.json, test.json, etc.

OR if you want to resume a previously paused/saved game, you will go into data/paused_games and use
loaddata.json

Features implemented:
* 4 different languages
* 3 different styling options
* game selection scene to allow users to select to start a new game, resume a game, or go to the board editor
* building houses
* rolling the dice
* selecting game pieces
* paying rent
* the current player can see their information
* railroads and different property types
* gain money as you pass GO
* board size is completely customizable & so are all the properties on it
* etc.

### Notes/Assumptions

Assumptions or Simplifications:

* simplifications: the user only sees the index of the properties they own (rather than the name)
* the user currently is not alerted if they pass GO, they just gain money
* once you start a game, it actually generates all the pop-ups for all users to select their game
  pieces at once -- as soon as one player selects theirs, the window closes. We assume the player
  will not be nosy enough to move these windows around -- but if they do try to avoid selecting a
  game piece, it will alert the user and refuse to let them close the window (as they need to have a
  game piece in order to play the game)

Interesting data files:

* Buttons.properties includes all the important information for our View's use of the
  InteractiveObject hierarchy and allows for any of these subclasses to be easily made with
  reflection. To use, once you create a new subclass that implements InteractiveObjects, just add
  the class name and the method. Then, use the Reflection.java class to easily make the object
  itself and give it a method from the property file.
* GameViewReflection.properties is how the View manages all of the event handling in GameView with
  reflection. To use, simply add the name of an event that should be handled by the view as a key,
  and set its value as a method (implemented in GameView).

Known Bugs:

* if you play a one-player game and land on a property you have previously bought, it will
  make you pay rent to yourself. This might be an error in indexing the player list starting at 0 vs
  1
  in some places.
* Jail is not fully functional yet, although we have most of the pieces in place.
* When you resume/load a game instead of starting a new one, you will have to re-select the game
  pieces
  and it will place them at GO (instead of where they ended the game). However, it will move them
  from the correct position that they ended in, it just displays them at GO initially.

Challenge Features: the board game editor + loading and saving a game!

### Impressions

