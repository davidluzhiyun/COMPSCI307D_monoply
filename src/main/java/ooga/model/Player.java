package ooga.model;

import ooga.model.place.Place;

import java.util.Collection;

/**
 * @author Luyao Wang
 * Player interface for model classes. Jail not implemented
 * @author David Lu decided to extend from ViewPlayer
 */
public interface Player extends ControllerPlayer {
  void purchase(Place place, int propertyIndex) throws IllegalStateException;

  /**
   * @author David Lu
   * default purchase method without the need to specify index
   * @param place
   */
  void purchase(Place place) throws IllegalStateException;
  void decrementOneDiceLeft();
  void addOneDiceRoll();
  /**
   * A method to check if the player is able to roll dice
   * @return
   */
  boolean hasNextDice();
  boolean goJail();
  /**
   * Called when switch from one player's turn to another. Reset turn used and turn left.
   */
  void newTurn();

  /**
   * This method is meant for load the game from .json file.
   * Player's money is not changed by set the properties.
   * You should use void purchase(Place place, int propertyIndex) for purchasing properties in the game.
   * @param propertyIndices
   */
  void setProperties(Collection<Integer> propertyIndices);

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
}
