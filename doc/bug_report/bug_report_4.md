## Description

Even when users have bought all properties in the color set, there is no option appearing to let them
buy a house.

## Expected Behavior

Once you buy a property and click on it, a list of buttons should appear that includes a buy house button.

## Current Behavior

Right now, this buy house button is not appearing.

## Steps to Reproduce

Provide detailed steps for reproducing the issue.

1. start the game
2. buy all properties in a color set (so all should belong to the same player)
3. click on one of the properties in the set -- see that there is only a view info button.

## Hypothesis for Fixing the Bug

I just need to change from Group to VBox in the AvailablePlaceActionsPopUp so the button 
actually gets added. 