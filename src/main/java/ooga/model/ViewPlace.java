package ooga.model;

import java.util.Collection;

/**
 * Place data for view to update.
 */
public interface ViewPlace {
  ViewPlayer getOwner();
  Collection<ViewPlayer> getPlayers();
  int getHousesNum();
  Collection<PlaceAction> getPlaceActions();
}
