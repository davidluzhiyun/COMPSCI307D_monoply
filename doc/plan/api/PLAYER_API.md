#### ModelOutput API Overview

## Overview
* getting information related to a player. Used in updating info and internal processing

## Details
* getPlayId returns the id of the player, which is an integer
* int getCurrentSpace returns the place where the player is at as a integer
* isInJail returns a boolean signaling whether the player is in jail
* remainingJailTurns returns the remaining turns the player need to stay in jail.
if not in jail, returns -1.
* stationaryAction is used for publishing the stationary actions the player can take.
"Stationary" actions are those the player can take without clicking open the pop-up menu
of places (cells on the board)
* getProperties returns a collection of Properties containing info about the properties the player owns
* getTotalMoney returns the money the player has

