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
   * A method to get all the stationary actions the place can have.
   * @param player
   * @return
   */
  Collection<StationaryAction> getStationaryActions();
  /**
   * A method to get the place actions the player can choose from.
   * @param player
   * @return
   */
  Collection<PlaceAction> getPlaceAction(Player player);
}
