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
   * Information about all players, wrapped in command.
   */
  void propertiesData();

  /**
   * Information about the board, used to initialize the view.
   */
  void boardData();
}