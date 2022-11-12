package ooga.model;

import ooga.model.place.Place;
import ooga.model.place.property.Property;

import java.util.Collection;

public interface Player {
  int getPlayId();
  int getCurrentSpace();
  Boolean isInJail();
  int remainingJailTurns();
  Collection<Property> getProperties();
  int getTotalMoney();
  void move(Place place);
  void purchase(Property place);
}
