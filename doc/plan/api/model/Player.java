package model;

import model.place.Place;
import model.place.property.Property;

import java.util.Collection;
import java.util.Properties;

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
