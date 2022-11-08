#### ModelOutput API Overview

## Overview
* Publishes necessary info from the model in events

## Details
* publishDice method is used to publish the current number on the two dice
* currentPlayer method publishes the id of the player operating in the current stage.
* playersData publishes the information of all the players in a Collection. The exact form 
can be seen in the players api
* boardData publishes the data about the board that doesn't change. Includes the type of the places
and the property cards. Used for initialization of view and maybe checking info. Details are
still under discussion
* stationaryAction is used for publishing the stationary actions the player can take.
"Stationary" actions are those the player can take without clicking open the pop-up menu
of places (cells on the board)
* boardUpdateData publishes an array or similar data structure of PlaceUpdate.
PlaceUpdate allows one to get the id of the place's owner (if any), a collection of players at
the place number of houses at the place and a collection of actions tied to the place. (Actions that
require clicking on the place)

## Considerations
* Whether the Model is responsible for calling the methods or the Control
* Details of initialization remains to be discussed