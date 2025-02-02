package ooga.model.player;

import java.util.Map;
import java.util.function.Predicate;
import ooga.model.place.Place;

import java.util.Collection;

/**
 * @author Luyao Wang
 * Player interface for model classes. Jail not implemented
 * @author David Lu decided to extend from ViewPlayer
 */
public interface Player extends ControllerPlayer {

  void mergePropertyIndices(Collection<Integer> newIndices);

  void mergeProperties(Collection<Place> newPlaces);

  void purchase(Place place, int propertyIndex) throws IllegalStateException;
  void addOneDiceRoll();
  /**
   * A method to check if the player is able to roll dice
   * @return
   */
  void nextDice();
  void newTurn();
  /**
   * Called when switch from one player's turn to another. Reset turn used and turn left.
   */

  /**
   * This method is meant for load the game from .json file.
   * Player's money is not changed by set the properties.
   * You should use void purchase(Place place, int propertyIndex) for purchasing properties in the game.
   * @param properties
   */
  void setProperties(Collection<Place> properties);

  /**
   * Set money, either through passing GO, collecting rent, or collecting communist chest.
   * Edit by David Lu: The same method will also be used for decreasing money
   * @param money
   */
  void setMoney(double money);
  void setJail(int jailTurns);
  /**
   * By Luyao Wang, updated by Zhiyun Lu
   * Method moves the player to specified index. Doesn't check if the index is legal because
   * the player class is blind to the board. The job of calculating the correct index is of
   * playerTurn
   * @param destinationIndex the index the player should be moved to.
   */
  void setIndex(int destinationIndex);

  /**
   * @author David Lu
   * @param colorSetCheckers see ConcreteColorSet
   */
  void setColorSetCheckers(Map<Integer, Predicate<Collection<Place>>> colorSetCheckers);
  /**
   * Check if the player can build a house at a place
   * Through error if input is null or colorSetCheckers weren't set
   * @author David Lu
   */
  boolean canBuildOn(Place place);
  void setOwnedRailroadsCount(int count);
  void setDice(int result);

  /**
   * @author David Lu
   * Check if the player has monopoly over a color set
   * @param colorId color id
   * @return true if player has monopoly over color set of given id
   */
  boolean checkMonopolyOver(int colorId);
  /**
   * @author David Lu
   * Set the index of the jail the player should goto
   */
  void setJailIndex(int jailIndex);
  /**
   * @author David Lu
   * Get the player out of jail for free;
   */
  void getOutOfJail();
  /**
   * @author David Lu
   * Get the player out of jail for a fee;
   */
  void payOutOfJail();
  Map<Integer, Predicate<Collection<Place>>> getColorSetCheckers();
  Collection<Place> getProperties();
  void setHasNextDice(boolean hasNextDice);
  void setDicesTotal(int dicesTotal);
  int getDicesTotal();
  void setAddOneDiceRollJail(AddOneDiceRoll addOneDiceRollJail);
  /**
   * @author David Lu
   * Backrupt the current player to another player or the bank
   * Assume null stands for the bank
   */
  void bankruptTo(Player player);
}
