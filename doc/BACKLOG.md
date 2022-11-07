#### Backlog

## Use Cases:
# Model
* 

# View
*

# Controller
* Initialize the model with the correct game configurations are the start of the game
* Interact with an event listener to appropriately handle events
* Handle game events (listed):
  * Roll dice (from view): needs to relay this event to the model
  * Update view (from model): takes in parameters form the model to send to the view to update it based on their dice roll, current state, and square they landed on
  * Update model (from view): view needs to update what choices a player made on the screen 
* Add more event handling for extensions