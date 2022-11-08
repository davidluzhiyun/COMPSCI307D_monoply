#### Backlog

## Use Cases:

# Model

*

# View

1. Situation: User wants to start a new game.
    * StartView is presented to the user.
    * User must select a language
    * Optionally, user may pick their CSS style
    * User must upload a configuration file
    * These changes need to be applied and the user must be able to go to the game board screen.

2. Situation: User lands on a property that is not owned by anyone.
    * User must see the property information
    * Offer the user the opportunity to buy the property.
    * If the user buys the property, their player information must be updated (they should lose
      money and the property name should be added to their list of properties)
        * the game board should update to display the most up-to-date player information AND to
          indicate that this property is now owned
    * If the user tries to buy the property but does not have enough money, an error message should
      be displayed telling the user they cannot purchase the property.
    * The user's turn ends.

3. Situation: User lands on a property that is owned by someone else AND they have enough rent
   money.
    * User must see the property information
    * user is told that they must pay rent.
    * the amount that they owe is displayed
    * The user clicks on a button of some sort to pay rent
        * this should signal to the controller
        * The HUD for the current player should update to decrease their money.
        * (Within model, both the current player AND the owner of the property should have their
          money adjusted)
    * The user's turn ends.

4. Situation: User lands on a property owned by someone else AND they do NOT have enough money to
   pay rent.
    * User must see the property information.
    * The user is told they must pay rent (and the amount that they owe)
    * The user is told they do not have enough money to pay rent.
    * user is presented with the choice to 1) mortgage their properties or 2) announce defeat
        * if they mortgage properties, they must be shown the information of each of their
          properties.
          They can select one or more to sell back to the bank. If they mortgage properties, their
          net
          worth should be updated, and the properties should also be removed from their list of
          owned
          properties.
            * if they try to mortgage properties, but do not own any, an error message should be
              displayed
        * if they choose to resign, this player must be taken out of the rotation & the board should
          be
          updated to remove them
    * if the player earns enough money to pay rent by mortgaging properties, they then click on a
      button to pay rent.
        * this should signal to the controller; the HUD for the player should be updated; model
          needs to update

5. Situation: One user finishes their turn and it is now the next player's turn.

   * Switch to now display the current player's information instead.
   * Announce which player's turn it is
   * Show a player dice to roll, give them a button to click to roll.
   * Player must "roll"
       * this signals to controller, view must "wait"

6. Situation: User has rolled the dice.
    * View should now tell the user the number they rolled (information that is received from the
      controller)
    * the user's game piece should be moved to the appropriate location
    * IF the user passes GO because of this, their net worth must be updated.
    * the user should be presented with actions they can take based on which cell they land on.

# Controller

* Initialize the model with the correct game configurations are the start of the game
* Interact with an event handler to appropriately handle events
* Handle game events (listed):
    * Roll dice (from view): needs to relay this event to the model
    * Update view (from model): takes in parameters form the model to send to the view to update it
      based on their dice roll, current state, and square they landed on
    * Update model (from view): view needs to update what choices a player made on the screen
* (Possibly) parse files from a database
* Add more event handling for extensions