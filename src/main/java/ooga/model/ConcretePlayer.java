package ooga.model;


import java.util.HashSet;

import ooga.model.place.Place;

import java.util.ArrayList;
import java.util.Collection;

public class ConcretePlayer implements Player, ControllerPlayer {
  private double money;
  private int playerId;
  private int currentPlaceIndex;
  private int dicesTotal;
  private int remainingJailTurns;
  private Collection<Integer> properties;
  private boolean hasNextDice;

  /**
   * Universal constructor for loading the game./
   *
   */
  public ConcretePlayer(int playerId, double money,  int remainingJailTurns, int currentPlaceIndex, int dicesTotal, boolean hasNextDice, Collection<Integer> properties) {
    this.playerId = playerId;
    this.money = money;
    this.currentPlaceIndex = currentPlaceIndex;
    this.remainingJailTurns = remainingJailTurns;
    this.properties = properties;
    this.dicesTotal = dicesTotal;
    this.hasNextDice = hasNextDice;
  }

  public ConcretePlayer(int playerId) {
    this.currentPlaceIndex = 0;
    this.money = 0;
    this.playerId = playerId;
    this.hasNextDice = false;
    properties = new ArrayList<>();
  }

  @Override
  public void newTurn() {
    hasNextDice = true;
    dicesTotal = 1;
    //TODO: when in jail
  }

  @Override
  public void setProperties(Collection<Integer> propertyIndices) {
    properties = propertyIndices;
  }

  @Override
  public void setMoney(double money) {
    this.money = money;
  }

  @Override
  public void setJail(int jailTurns) {
    remainingJailTurns = jailTurns;
  }

  @Override
  public void setIndex(int destinationIndex) {
    currentPlaceIndex = destinationIndex;
  }


  public void addOneDiceRoll() {
    hasNextDice = true;
    dicesTotal++;
    if (dicesTotal == 4)
      setJail(3);
  }

  @Override
  public boolean hasNextDice() {
    return hasNextDice;
  }

  @Override
  public void nextDice() {
    hasNextDice = false;
  }

  @Override
  public int getPlayerId() {
    return playerId;
  }

  @Override
  public int getCurrentPlaceIndex() {
    return currentPlaceIndex;
  }

  @Override
  public int remainingJailTurns() {
    return remainingJailTurns;
  }

  @Override
  public Collection<Integer> getPropertyIndices() {
    return new HashSet<>(properties);
  }

  @Override
  public double getTotalMoney() {
    return money;
  }

  @Override
  public void purchase(Place property, int propertyIndex) {
    try {
      money -= property.getPurchasePrice();
      properties.add(propertyIndex);
      property.setOwner(playerId);
    } catch (IllegalStateException e) {
      throw new IllegalStateException();
    }
  }
}
