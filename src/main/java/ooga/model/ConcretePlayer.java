package ooga.model;


import java.util.HashMap;
import java.util.HashSet;

import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import ooga.model.exception.CannotBuildHouseException;
import ooga.model.exception.NoColorAttributeException;

import ooga.model.place.Place;

import java.util.ArrayList;
import java.util.Collection;

public class ConcretePlayer implements Player, ControllerPlayer {
  private double money;
  private int playerId;
  private int currentPlaceIndex;
  private int dicesTotal;
  private int remainingJailTurns;
  private boolean hasNextDice;
  private Collection<Integer> propertyIndices;
  private Collection<Place> properties;
  private Map<Integer, Predicate<Collection<Place>>> colorSetCheckers;

  /**
   * Universal constructor for loading the game./
   *
   */
  public ConcretePlayer(int playerId, double money,  int remainingJailTurns, int currentPlaceIndex, int dicesTotal, boolean hasNextDice, Collection<Integer> propertyIndices) {
    this.playerId = playerId;
    this.money = money;
    this.currentPlaceIndex = currentPlaceIndex;
    this.remainingJailTurns = remainingJailTurns;
//    this.properties = properties;
    this.dicesTotal = dicesTotal;
    this.hasNextDice = hasNextDice;
    this.propertyIndices = propertyIndices;
  }

  public ConcretePlayer(int playerId) {
    this.currentPlaceIndex = 0;
    this.money = 0;
    this.playerId = playerId;
    this.hasNextDice = false;
    properties = new ArrayList<>();
    propertyIndices = new ArrayList<>();
  }

  @Override
  public void newTurn() {
    hasNextDice = true;
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
    currentPlaceIndex = destinationIndex;
  }

  @Override
  public void setColorSetCheckers(Map<Integer, Predicate<Collection<Place>>> colorSetCheckers) {
    this.colorSetCheckers = new HashMap<>(colorSetCheckers);
  }

  /**
   * @author David Lu
   * Helper funtion to check if the place has at least a certain number of houses as another place
   * @param place the place to check
   * @param target the latter place
   */
  private boolean checkHouseNum(Place place, Place target){
    try {
      int placeHouseNum = place.getHouseCount();
      return placeHouseNum >= target.getHouseCount();
    }
    catch (CannotBuildHouseException e){
      return false;
    }
  }

  /**
   * Check if player can build a house on a place
   * @param place a place to check
   * @return
   */
  @Override
  public boolean canBuildOn(Place place) {
    try {
     int color = place.getColorSetId();
     Predicate<Collection<Place>> checker = colorSetCheckers.get(color);
     if (checker == null){
       return false;
     }
     return checker.test(properties.stream().filter((Place p) -> checkHouseNum(p,place)).collect(
         Collectors.toSet()));
    }
    catch (NoColorAttributeException e){
      // not something with a color
      return false;
    }
    catch (NullPointerException e){
      throw new IllegalStateException("Input is null or checker unset",e);
    }
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
}
