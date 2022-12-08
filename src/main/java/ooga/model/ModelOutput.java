package ooga.model;

import java.awt.Point;
import java.util.Collection;
import java.util.List;
import ooga.model.place.ControllerPlace;
import ooga.model.place.Place;

/**
 * The interface published to the controller
 * @author David Lu
 * @author Luyao Wang
 */
public interface ModelOutput {
  GameState getGameState();
  /**
   * Publish the current number on the two dice
   */
  Point getDiceNum();

  /**
   * Gets the id of the next player
   */
  int getCurrentPlayer();

  /**
   * Information about all players, in a List, change to collection if order no longer needed
   */
  List<ControllerPlayer> getPlayers();

  /**
   * Information about the board, used for both initialization and update now
   * (Place action is inside)
   */
  List<ControllerPlace> getBoard();

  /**
   * Get a list of StationaryActions that can be taken by the current player
   */
  Collection<StationaryAction> getStationaryAction();
}
