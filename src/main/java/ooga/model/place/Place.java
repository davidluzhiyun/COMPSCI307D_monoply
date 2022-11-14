package ooga.model.place;

import ooga.model.Player;
import ooga.model.StationaryAction;
import ooga.model.ViewPlayer;

import java.util.Collection;
import java.util.List;

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
  List<StationaryAction> getStationaryActions(Player player);
}
