package model;

public interface Model<T> {
  T getCommandArgs();

  /**
   * Publish the current state of the dice
   */
  void publishDice();


  /**
   * Publish the id of the next player
   */
  void currentPlayer();

  /**
   * Information about all players, wrapped in command.
   */
  void playersData();

  /**
   * Information about the board, used to initialize the view.
   * Kept here in case view need to query corresponding information
   */
  void boardData();

  /**
   * Rolling dice + Current space
   */
  void stationaryAction();

  void boardUpdateData();
}