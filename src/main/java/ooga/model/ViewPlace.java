package ooga.model;

import ooga.model.place.SuperPlace;

import java.util.Collection;

/**
 * Place data for view to update.
 */
public interface ViewPlace extends SuperPlace {
  Collection<? extends ViewPlayer> getViewPlayers();
  int getHousesNum();
  Collection<PlaceAction> getPlaceActions();
}
