package ooga.model;

import java.util.Collection;

/**
 * Player interface for view classes. Jail not implemented
 */
public interface ControllerPlayer {
  int getPlayerId();
  int getCurrentPlaceIndex();
  Boolean isInJail();
  int remainingJailTurns();
  Collection<Integer> getPropertyIndices();
  double getTotalMoney();
}
