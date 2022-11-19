package ooga.model.place;

import ooga.model.*;
import ooga.model.exception.CannotBuildHouseException;
import ooga.model.exception.NoColorAttributeException;

import java.util.Collection;

/**
 * The Place interface is shared feature of all the places in monopoly
 */
public interface Place {
  /**
   * Get the id of the place. The id corresponds to more detailed data in .json format.
   *
   * @return the id of the place
   */
  int getPlaceId();


  /**
   * Get a collection of all the players that are currently on the place (not necessarily possess that place).
   *
   * @return
   */
  Collection<? extends Player> getPlayers();

  double getMoney();

  /**
   * A method to get a collection of stationary actions for the current player
   *
   * @param player the current player
   * @return a collection of stationary actions for the current player
   */
  Collection<StationaryAction> getStationaryActions(Player player);

  /**
   * A method to get a collection of stationary actions for the current player
   *
   * @param player
   * @return a collection of stationary actions for the current player at the place
   */
  Collection<PlaceAction> getPlaceActions(Player player);

  int getHouseCount() throws CannotBuildHouseException;
  int getColorSetId() throws NoColorAttributeException;
}
