package ooga.model;

import java.util.Collection;

/**
 * Place data for view to update.
 */
@Deprecated
public interface ViewPlace{
  Collection<? extends ControllerPlayer> getViewPlayers();
  int getHousesNum();
  Collection<PlaceAction> getPlaceActions();
}
