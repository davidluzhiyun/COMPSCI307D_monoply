package ooga.model.player;

import java.util.Collection;

/**
 * Player interface for view classes. Jail not implemented
 */
public interface ControllerPlayer {
  int getPlayerId();
  int getCurrentPlaceIndex();
  int remainingJailTurns();
  Collection<Integer> getPropertyIndices();
  double getTotalMoney();
  boolean hasNextDice();
  int getDice();
  int getOwnedRailroadCount();
  /**
   * Check if the player is still alive
   */
  boolean isAlive();
}
