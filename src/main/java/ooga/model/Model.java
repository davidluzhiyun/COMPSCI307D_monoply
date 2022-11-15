package ooga.model;

import ooga.model.place.property.Property;

public interface Model<T> {

  /**
   * Publish the current state of the dice
   */
  void publishDice();

  /**
   * Publish the id of the next player
   */
  void publishCurrentPlayer();

  /**
   * Information about all players, wrapped in command.
   */
  void playersData();

  /**
   * Information about the board, used to initialize the view.
   * Kept here in case view need to query corresponding information
   */
  void boardData();

  void stationaryActions();
  void boardUpdateData();

  /**
   * Corresponds to end turn button in frontend. Current player ends turn, and it becomes next player's turn.
   */
  void endTurn();
  void buyProperty(Property property);
}