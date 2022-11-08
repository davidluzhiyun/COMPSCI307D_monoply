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

  /**
   * {
   * "Current_Player": 0,
   * "1": {
   * "Players": [0, 2],
   * "Grid_Actions": ["View_Info", "Houses", "Purchase_House", "(Un)Mortgage", "Sell_House", "Trade"],
   * }
   * "2": {
   * "Players": [1],
   * "Grid_Actions": ["View_Info", "Houses", "Trade_With"],
   * }
   * "3": {
   * "Players": [3],
   * "Grid_Actions": ["View_Info", "(Un)Mortgage", "Sell_House", "Trade_With"],
   * }
   * "5": {
   * "Players": [],
   * "Grid_Actions": ["View_Info", "Trade_With"],
   * }
   * "5": {
   * "Players": [],
   * "Grid_Actions": ["View_Info"],
   * }
   * }
   */
  void boardUpdateData();
}