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
  private int dicesLeft;
  private int dicesTotal;
  private int remainingJailTurns;
  private final Collection<Property> properties;

  public ConcretePlayer(int playerId) {
    this.currentPlaceId = 0;
    this.money = 0;
    this.playerId = playerId;
    properties = new ArrayList<>();
  }
//  @Override
//  public int getPlayerId() {
//    return playerId;
//  }

  public void newTurn() {
    dicesLeft = 1;
    dicesTotal = 1;
    //TODO: when in jail
  }

  @Override
  public void earnMoney(double money) {
    this. money += money;
  }

  public void decrementOneDiceLeft() {
    dicesLeft--;
  }

  public void addOneDiceRoll() {
    dicesLeft++;
    dicesTotal++;
    if (dicesTotal == 4)
      isInJail = true;
  }

  public boolean hasNextDice() {
    return dicesLeft > 0;
  }

  public boolean goJail() {
    return isInJail;
  }

  @Override
  public int getPlayerId() {
    return 0;
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
