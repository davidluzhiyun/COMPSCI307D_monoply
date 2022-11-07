package model;

public interface Model<T> {
  T getCommandArgs();

  /**
   * All information about dice. If a double is rolled, one more result will be provided.
   * For example, [(3, 3)], [(2, 2), (3, 2)], [(2, 2), (3, 3), (4, 3)], or [(2, 2), (3, 3), (4, 4)] (go jail)
   */
  void rollDice();

  /**
   * Information about all players, wrapped in command.
   */
  void playersData();

  /**
   * Information about the board, used to initialize the view.
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
  void gridData();
}