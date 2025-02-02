package ooga.model.place;

import ooga.event.GameEventHandler;
import ooga.model.*;
import ooga.model.player.Player;

import java.util.Collection;

/**
 * The Place interface is shared feature of all the places in monopoly
 */
public interface Place extends ControllerPlace{

  /**
   * A method to get a collection of stationary actions for the current player
   *
   * @param player the current player
   * @return a collection of stationary actions for the current player
   */
  Collection<StationaryAction> getStationaryActions(Player player);

  Collection<StationaryAction> getPlaceBasedStationaryActions(Player player);

  /**
   * A method to update place actions for the current player
   *
   * @param player
   *
   */
  void updateCurrentPlayerPlaceActions(Player player);

  /**
   * Get the price to purchase the property.
   *
   * @return the price to purchase the property
   */
  double getPurchasePrice() throws IllegalStateException;

  void setOwner(int playerId, Player owner) throws IllegalStateException;

  void setHouseCount(int count) throws IllegalStateException;

  /**
   * Get the price it takes to build a house on the street.
   *
   * @return price it takes to build a house
   * Normally throws exception for non street
   * @throws IllegalStateException
   */
  double getHousePrice() throws IllegalStateException;

  void landingEffect(Player player);
  GameEventHandler getGameEventHandler();
}
