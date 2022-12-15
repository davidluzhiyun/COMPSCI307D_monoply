## Description

When the player tries to load/resume a game from a previously saved game, some game configurations are not restored.

## Expected Behavior

The game should restore to the previously saved game, including all the configuration.

## Current Behavior

Some game configurations are not restored.

## Steps to Reproduce

1. Prepare a .json file of a previously saved game session to load (can be done by clicking the "save game" button when one runs the app)
2. On the file selection screen, click "load/resume a game" and select that .json file.


## Hypothesis for Fixing the Bug

Whether a player goes into jail after 3 dices roll or whether a player has to all the streets of a color to buy a street should be the variations of the game.
However, when loading the game, these settings are hardcoded to be default value. The factory pattern should also take these variation into account.