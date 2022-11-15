package ooga.model.place;

import ooga.model.*;

import java.util.Collection;
import java.util.List;

/**
 * The Place interface is shared feature of all the places in monopoly
 */
public interface Place extends SuperPlace {
  /**
   * Get the id of the place. The id corresponds to more detailed data in .json format.
   * @return the id of the place
   */
  int getPlaceId();
  Collection<? extends Player> getPlayers();
  double getMoney();

  /**
   * A method to get a collection of stationary actions for the current player
   * @param player the current player
   * @return a collection of stationary actions for the current player
   */
  Collection<StationaryAction> getStationaryActions(Player player);
  /**
   * A method to get a collection of stationary actions for the current player
   * @param player
   * @return a collection of stationary actions for the current player at the place
   */
  Collection<PlaceAction> getPlaceAction(Player player);
}
