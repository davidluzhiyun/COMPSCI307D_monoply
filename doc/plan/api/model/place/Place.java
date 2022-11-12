package model.place;

import model.Player;
import java.util.Collection;

/**
 * The Place interface is shared feature of all the places in monopoly
 */
public interface Place {
  /**
   * Get the id of the place. The id corresponds to more detailed data in .json format.
   * @return the id of the place
   */
  int getPlaceId();
  Collection<Player> getPlayers();
  double getMoney();
}
