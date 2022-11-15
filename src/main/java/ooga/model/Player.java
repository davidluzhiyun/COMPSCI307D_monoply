package ooga.model;

import ooga.model.place.Place;
import ooga.model.place.property.Property;

import java.util.Collection;

/**
 * @author Luyao Wang
 * Player interface for model classes. Jail not implemented
 * @author David Lu decided to extend from ViewPlayer
 */
public interface Player extends ViewPlayer {
  void move(Place place);
  void purchase(Property place);
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
