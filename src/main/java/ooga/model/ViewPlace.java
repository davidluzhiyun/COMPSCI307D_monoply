package ooga.model;

import java.util.Collection;

/**
 * Place data for view to update.
 */
@Deprecated
public interface ViewPlace{
  Collection<? extends ViewPlayer> getViewPlayers();
  int getHousesNum();
  Collection<PlaceAction> getPlaceActions();
}
