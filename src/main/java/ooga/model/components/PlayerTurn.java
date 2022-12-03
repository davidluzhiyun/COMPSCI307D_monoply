package ooga.model.components;

import java.awt.Point;
import ooga.model.place.Place;


public interface PlayerTurn {
  /**
   * Current player roll the dice and move to a place.
   *
   * @return
   */
  Point roll();
  int getCurrentPlayerTurnId();
  /**
   * Current player ends turn and switch next player's turn.
   */
  void nextTurn();
  Place getCurrentPlace();
  Point getDiceNum();

}
