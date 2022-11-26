package ooga.model;

import ooga.model.place.property.Property;

import java.util.Collection;

/**
 * Player interface for view classes. Jail not implemented
 */
public interface ControllerPlayer {
  int getPlayerId();
  int getCurrentPlaceId();
  Boolean isInJail();
  int remainingJailTurns();
  Collection<Property> getProperties();
  double getTotalMoney();
}
