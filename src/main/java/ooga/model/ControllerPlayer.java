package ooga.model;

import ooga.model.place.Place;

import java.util.Collection;

/**
 * Player interface for view classes. Jail not implemented
 */
public interface ControllerPlayer {
  int getPlayerId();
  int getCurrentPlaceIndex();
  Boolean isInJail();
  int remainingJailTurns();
  Collection<Place> getProperties();
  double getTotalMoney();
}
