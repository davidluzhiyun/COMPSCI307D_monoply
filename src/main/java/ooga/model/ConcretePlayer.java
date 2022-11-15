package ooga.model;


import ooga.model.place.Place;
import ooga.model.place.property.Property;

import java.util.ArrayList;
import java.util.Collection;

public class ConcretePlayer implements Player, ViewPlayer {
  private double money;
  private int playerId;
  private int currentPlaceId;
  private boolean isInJail = false;
  private int turnsLeft;
  private int turnsUsed;
  private int remainingJailTurns;
  private final Collection<Property> properties;
  public ConcretePlayer(int playerId) {
    this.currentPlaceId = 0;
    this.playerId = playerId;
    properties = new ArrayList<>();
  }
  @Override
  public int getPlayId() {
    return playerId;
  }

  public void newTurn() {
    turnsLeft = 1;
    turnsUsed = 0;
  }

  public void decrementOneTurnLeft() {
    turnsLeft--;
  }
  public void addOneTurnLeft() {
    turnsLeft++;
  }

  public void addOneTurnUsed() {
    turnsUsed++;
  }

  public boolean hasNextTurn() {
    return turnsLeft > 0;
  }

  public boolean goJail() {
    return turnsUsed == 3;
  }

  @Override
  public int getCurrentPlaceId() {
    return currentPlaceId;
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
    currentPlaceId = place.getPlaceId();
    money += place.getMoney();
  }

  @Override
  public void purchase(Property property) {
    money -= property.getPurchasePrice();
    properties.add(property);
    property.purchaseBy(this);
  }
}
