package model;

import java.util.Collection;

/**
 * Place data for view to update.
 */
public interface ViewPlace {
  int getOwnerId();
  Collection<Player> getPlayers();
  int getHousesNum();
  Collection<PlaceAction> getPlaceActions();
}
