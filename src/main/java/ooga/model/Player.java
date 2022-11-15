package ooga.model;

import ooga.model.place.Place;
import ooga.model.place.property.Property;

import java.util.Collection;

/**
 * @author Luyao Wang
 * Player interface for model classes. Jail not implemented
 * @author David Lu decided to extend from ViewPlayer
 */
public interface Player extends ViewPlayer {
  void move(Place place);
  void purchase(Property place);
  void decrementOneTurnLeft();
  void addOneTurnLeft();
  void addOneTurnUsed();
  boolean hasNextTurn();
  boolean goJail();
  void newTurn();
}
