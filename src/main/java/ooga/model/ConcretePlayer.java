package ooga.model;


import ooga.model.place.Place;
import ooga.model.place.property.Property;

import java.util.ArrayList;
import java.util.Collection;

public class ConcretePlayer implements Player, ViewPlayer {
  private double money;
  private int playerId;
  private int placeId;
  private boolean isInJail = false;
  private int remainingJailTurns;
  private final Collection<Property> properties;
  public ConcretePlayer(int playerId) {
    this.playerId = playerId;
    properties = new ArrayList<>();
  }
  @Override
  public int getPlayId() {
    return playerId;
  }

  @Override
  public int getCurrentSpaceId() {
    return placeId;
  }

  @Override
  public Boolean isInJail() {
    return isInJail;
  }

  @Override
  public int remainingJailTurns() {
    return remainingJailTurns;
  }

  @Override
  public Collection<Property> getProperties() {
    return properties;
  }

  @Override
  public double getTotalMoney() {
    return money;
  }

  @Override
  public void move(Place place) {
    //Auto part
    placeId = place.getPlaceId();
    money += place.getMoney();
  }

  @Override
  public void purchase(Property place) {
    money -= place.getPurchasePrice();
    properties.add(place);
  }
}
