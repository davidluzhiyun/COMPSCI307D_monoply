package ooga.model.place;

import ooga.model.*;
import ooga.model.exception.CannotBuildHouseException;
import ooga.model.exception.NoColorAttributeException;

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

  /**
   * A method to update place actions for the current player
   *
   * @param player
   *
   */
  void updatePlaceActions(Player player);

  /**
   * Get the price to purchase the property.
   *
   * @return the price to purchase the property
   */
  double getPurchasePrice() throws IllegalStateException;

  void purchaseBy(int playerId) throws IllegalStateException;

  void setHouseCount(int count) throws IllegalStateException;
}
