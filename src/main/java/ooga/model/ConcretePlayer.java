package ooga.model;


import java.util.HashSet;
import ooga.model.place.Place;

import java.util.ArrayList;
import java.util.Collection;

public class ConcretePlayer implements Player, ControllerPlayer {
  private double money;
  private int playerId;
  private int currentPlaceIndex;
  private boolean isInJail = false;
  private int dicesLeft;
  private int dicesTotal;
  private int remainingJailTurns;
  private Collection<Integer> propertyIndices;
  private Collection<Place> properties;

  /**
   * Universal constructor for loading the game./
   *
   * @param money
   * @param playerId
   * @param currentPlaceIndex
   * @param isInJail
   * @param dicesLeft
   * @param dicesTotal
   * @param remainingJailTurns
   * @param properties
   */
  public ConcretePlayer(double money, int playerId, int currentPlaceIndex, boolean isInJail,
                        int dicesLeft, int dicesTotal, int remainingJailTurns, Collection<Integer> properties) {
    this.money = money;
    this.playerId = playerId;
    this.currentPlaceIndex = currentPlaceIndex;
    this.isInJail = isInJail;
    this.dicesLeft = dicesLeft;
    this.propertyIndices = properties;
  }

  public ConcretePlayer(int playerId) {
    this.currentPlaceIndex = 0;
    this.money = 0;
    this.playerId = playerId;
    propertyIndices = new ArrayList<>();
  }

  public void newTurn() {
    dicesLeft = 1;
    dicesTotal = 1;
    //TODO: when in jail
  }

  @Override
  public void setProperties(Collection<Integer> propertyIndices) {
    this.propertyIndices = propertyIndices;
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
  public int getCurrentPlaceIndex() {
    return currentPlaceIndex;
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
  public Collection<Integer> getPropertyIndices() {
    return new HashSet<>(propertyIndices);
  }

  @Override
  public double getTotalMoney() {
    return money;
  }

  @Override
  public void purchase(Place property, int propertyIndex) {
    try {
      money -= property.getPurchasePrice();
      propertyIndices.add(propertyIndex);
      properties.add(property);
      property.setOwner(playerId);
    } catch (IllegalStateException e) {
      throw new IllegalStateException();
    }
  }
  @Override
  public void purchase(Place property) throws IllegalStateException{
    purchase(property, currentPlaceIndex);
  }
}
