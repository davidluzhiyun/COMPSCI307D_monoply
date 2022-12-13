package ooga.model;


import static ooga.model.component.ConcretePlayerTurn.modelToken;

import java.util.HashMap;
import java.util.HashSet;

import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import ooga.event.GameEventHandler;
import ooga.model.exception.CannotBuildHouseException;
import ooga.model.exception.NoColorAttributeException;

import ooga.model.place.Place;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConcretePlayer implements Player, ControllerPlayer {

  public static final int DEFAULT_JAIL_TURNS = 3;
  public static final int MAX_ROWS_IN_A_ROW = 4;
  public static final int DEFAULT_FINE = 50;
  private final int playerId;
  private double money;
  private int currentPlaceIndex;
  private boolean hasNextDice;
  private int remainingJailTurns;
  private int dicesTotal;
  private Collection<Integer> propertyIndices;
  private Collection<Place> properties;
  private Map<Integer, Predicate<Collection<Place>>> colorSetCheckers;
  private int diceResult;
  private int ownedRailroadCount;
  private static final Logger LOG = LogManager.getLogger(GameModel.class);

  private int jailIndex;
  private boolean isAlive;

  /**
   * Universal constructor for loading the game./
   */
  public ConcretePlayer(int playerId, double money, int currentPlaceIndex, boolean hasNextDice, int remainingJailTurns, int dicesTotal, Collection<Integer> propertyIndices, boolean isAlive) {
    this.playerId = playerId;
    this.money = money;
    this.currentPlaceIndex = currentPlaceIndex;
    this.remainingJailTurns = remainingJailTurns;
    this.dicesTotal = dicesTotal;
    this.hasNextDice = hasNextDice;
    this.propertyIndices = propertyIndices;
    this.isAlive = isAlive;
  }

  public ConcretePlayer(int playerId) {
    this.currentPlaceIndex = 0;
    this.money = 0;
    this.playerId = playerId;
    this.hasNextDice = false;
    properties = new ArrayList<>();
    propertyIndices = new ArrayList<>();
    this.isAlive = true;
  }

  @Override
  public void newTurn() {
    hasNextDice = true;
    dicesTotal = 1;
    if (remainingJailTurns > 0){
      remainingJailTurns -= 1;
    }
  }


  @Override
  public void setMoney(double money) {
    this.money = money;
  }

  /**
   * @author Luyao Wang
   * @author David Lu
   * Sents the player to Jail for certain amount of turns
   * @param jailTurns
   */
  @Override
  public void setJail(int jailTurns) {
    try {
      remainingJailTurns = jailTurns;
      // jailIndex is 0 when it isn't properly initialized
      // if one wish to do a version without jail, use polymorphism
      assert jailIndex > 0;
      setIndex(jailIndex);
      GameEventHandler gameEventHandler = new GameEventHandler();
      gameEventHandler.publish(modelToken + GameState.TO_JAIL);
    }
    catch (AssertionError e){
      IllegalStateException ex = new IllegalStateException("Jail index must be larger than zero", e);
      LOG.warn(ex);
      throw ex;
    }

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
   * @param place  the place to check
   * @param target the latter place
   * @author David Lu
   * Helper funtion to check if the place has at least a certain number of houses as another place
   */
  private boolean checkHouseNum(Place place, Place target) {
    try {
      int placeHouseNum = place.getHouseCount();
      return placeHouseNum >= target.getHouseCount();
    } catch (CannotBuildHouseException e) {
      return false;
    }
  }

  /**
   * Check if player can build a house on a place
   *
   * @param place a place to check
   * @return
   */
  @Override
  public boolean canBuildOn(Place place) {
    try {
      int color = place.getColorSetId();
      Predicate<Collection<Place>> checker = colorSetCheckers.get(color);
      if (checker == null) {
        return false;
      }
      return checker.test(properties.stream().filter((Place p) -> checkHouseNum(p, place)).collect(
          Collectors.toSet()));
    } catch (NoColorAttributeException e) {
      // not something with a color
      return false;
    } catch (NullPointerException e) {
      throw new IllegalStateException("Input is null or checker unset", e);
    }
  }

  @Override
  public int getOwnedRailroadCount() {
    return ownedRailroadCount;
  }

  @Override
  public void setOwnedRailroadsCount(int count) {
    ownedRailroadCount = count;
  }

  @Override
  public void setDice(int result) {
    diceResult = result;
  }


  /**
   * @author David Lu
   * Check if the player has monopoly over a color set
   * @param colorId color id
   * @return true if player has monopoly over color set of given id
   */
  @Override
  public boolean checkMonopolyOver(int colorId) {
    try {
      Predicate<Collection<Place>> checker = colorSetCheckers.get(colorId);
      if (checker == null){
        return false;
      }
      return checker.test(properties);
    }
    catch (NullPointerException e){
      throw new IllegalStateException("Checker unset",e);
    }
  }

  /**
   * @author Luyao Wang
   * @author David Lu (modifier)
   * Handles special effect of rolling doubles
   */
  public void addOneDiceRoll() {
    if (remainingJailTurns > 0){
      getOutOfJail();
      return;
    }
    hasNextDice = true;
    dicesTotal++;
    if (dicesTotal > MAX_ROWS_IN_A_ROW){
      dicesTotal = 1;
      setJail(DEFAULT_JAIL_TURNS);
    }
  }

  @Override
  public boolean hasNextDice() {
    return hasNextDice;
  }

  @Override
  public int getDice() {
    return diceResult;
  }

  public void setProperties(Collection<Place> properties) {
    this.properties = properties;
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

  /**
   * Add another collection of indices to propertyIndices
   * @param newIndices new ones to add
   */
  @Override
  public void mergePropertyIndices(Collection<Integer> newIndices){
    propertyIndices.addAll(newIndices);
  }
  /**
   * Add another collection of places to properties
   * @param newPlaces new ones to add
   */
  @Override
  public void mergeProperties(Collection<Place> newPlaces){
    properties.addAll(newPlaces);
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
      property.setOwner(playerId, this);
    } catch (IllegalStateException e) {
      throw new IllegalStateException();
    }
  }
  /**
   * @author David Lu
   * Set the index of the jail the player should goto
   */
  @Override
  public void setJailIndex(int jailIndex){
    try {
      this.jailIndex = jailIndex;
      // 0 is reserved for go
      assert jailIndex > 0;

    }
    catch (AssertionError e){
      IllegalStateException ex = new IllegalStateException("Jail index must be larger than zero", e);
      LOG.warn(ex);
      throw ex;
    }
  }
  /**
   * @author David Lu
   * Get the player out of jail for free;
   */
  @Override
  public void getOutOfJail() {
    remainingJailTurns = 0;
    GameEventHandler gameEventHandler = new GameEventHandler();
    gameEventHandler.publish(modelToken + GameState.OUT_OF_JAIL);
  }
  /**
   * @author David Lu
   * Get the player out of jail for a fee
   */
  @Override
  public void payOutOfJail() {
    money -= DEFAULT_FINE;
    getOutOfJail();
  }

  @Override
  public boolean isAlive() {
    return isAlive;
  }

  /**
   * @author David Lu
   * Backrupt the current player to another player or the bank
   * Assume null stands for the bank
   */
  public void bankruptTo(Player player){
    double revenue = 0;
    for (Place place: properties){
      try {
        // Change ownership if possibles
        if (player != null)
          place.setOwner(player.getPlayerId(),player);
        // For non-Streets, following steps won't do anything
        revenue += place.getHousePrice() * place.getHouseCount() / 2;
        place.setHouseCount(0);
      }catch (RuntimeException e){
        // do nothing
      }
      // TODO: un-mortgage if bankruptTo bank
    }
    revenue += Math.max(money,0);
    if (player != null){
      player.setMoney(player.getTotalMoney() + revenue);
      player.mergeProperties(properties);
      player.mergePropertyIndices(propertyIndices);
    }
    GameEventHandler gameEventHandler = new GameEventHandler();
    gameEventHandler.publish(modelToken + GameState.BANKRUPT);
    this.isAlive = false;
  }
}
