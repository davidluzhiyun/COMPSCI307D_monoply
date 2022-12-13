package ooga.model;

import ooga.model.player.ControllerPlayer;

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
