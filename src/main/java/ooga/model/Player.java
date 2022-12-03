package ooga.model;

import ooga.model.place.Place;
import ooga.model.place.property.Property;

/**
 * @author Luyao Wang
 * Player interface for model classes. Jail not implemented
 * @author David Lu decided to extend from ViewPlayer
 */
public interface Player extends ControllerPlayer {
  void move(Place place);
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
   * Earn money, either through passing GO, collecting rent, or collecting communist chest.
   * @param money
   */
  void earnMoney(double money);
}
