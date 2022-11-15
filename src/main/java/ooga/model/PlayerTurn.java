package ooga.model;

import java.util.List;

public interface PlayerTurn {
  /**
   * Current player roll the dice and move to a place.
   */
  void roll();
  int getCurrentPlayerTurnId();
  /**
   * Current player ends turn and switch next player's turn.
   */
  void nextTurn();

  /**
   * Get the stationary actions that the current player can choose from.
   * @return
   */
  List<StationaryAction> getStationaryActions();
}
