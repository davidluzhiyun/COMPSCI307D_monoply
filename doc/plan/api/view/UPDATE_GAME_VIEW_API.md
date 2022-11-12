### Update Game View API: Overview

This API allows for the game display to be constantly changing so the user always has the
most up-to-date information during their turn.

* Updates different information displayed in the main game view screen. For example:
    * Can update the location of a player's piece on the board
    * Can change which player's info is currently being displayed/update their information
    * Can indicate that a property is now owned
    * Can "build" houses

### Classes

* BoardView: the main class responsible for displaying the game board, player pieces, etc.
* PlayerInfo: contains a player's information (e.g. the amount of $ they have, the properties they
  own, etc.)
* GameView: responsible for displaying both BoardView and PlayerInfo object for the current player
* Controller: must coordinate the information needed for any of these changes, as well as report to
  the model to make updates there

### Example.

Situation: a new player's turn has started. Sequence of actions using API methods:

1. startPlayerTurn() -- GameView.java
2. displayPlayerInfo() -- GameView.java (will create an instance of PlayerInfo.java class)
3. rollDice() -- GameView.java
4. handleGameEvent() -- Controller.java (must tell the Model to roll the dice, calculate new player position, communicate back to View)
5. movePlayer() -- GameView.java

(From here, View will present the user with whatever actions they can take.)

### Details.

* This will need to collaborate with the Command and Controller APIs to coordinate with the Model
  (otherwise there would really be nothing to update within the game)
* Ensures that the HUD is displayed and maintained
* Will need resources that have player and property information.

### Considerations.

* We still definitely need to decide how player info will be communicated via the controller.
  * Is it best to allow View to access actual instances of the Player class? In this case, methods such
    as startPlayerTurn() or movePlayer() could take in a Player parameter.
* This same consideration applies to instances of the Place class. 
  * Should this remain an abstraction solely within the model, or is it best to have the view
    access this as well?
