package view;

public interface View {

  /**
   * Displays to the current player the property they have landed on, the rent owed, and to
   * which player it is owed. Should present them with a button (or something else) that, upon
   * clicking, starts the rent transaction (signals to controller)
   * THIS SHOULD ONLY BE CALLED IF THE PLAYER HAS ENOUGH MONEY TO PAY THE RENT! OTHERWISE, CALL
   * tellPlayerTheyAreBroke()
   * Information needed from model/controller: some indication of which property has been landed on
   * so the correct info can be pulled (rent, owner)
   */
  public void payRent();

  /**
   * Tells the player that they do not have enough money. Present them with the opportunity to sell/
   * mortgage properties OR to announce defeat.
   */
  public void tellPlayerTheyAreBroke();

  /**
   * Announce the current player; make new PlayerInfo object to display their "stats"/info;
   * present them with dice.
   * Click either the dice or some sort of button indicating they are ready to roll and start
   * their turn. Clicking to roll should signal to controller
   */
  public void startPlayerTurn();

  /**
   * Produces some sort of visualization of dice being rolled -- or just show the number that
   * they rolled. Call movePlayer() within BoardView to update player location on board.
   */
  public void rollTheDice();
}
