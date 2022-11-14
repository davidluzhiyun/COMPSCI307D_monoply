package ooga.model;

import java.util.List;

public interface PlayerTurn {
  /**
   * Current player roll the dice and move to a place.
   */
  void roll();
  int getCurrentPlayerTurnId();
  void nextTurn();
  List<StationaryAction> getStationaryActions();
}
