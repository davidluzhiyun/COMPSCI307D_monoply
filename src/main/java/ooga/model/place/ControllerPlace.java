package ooga.model.place;

import java.util.Collection;
import ooga.model.ControllerPlayer;
import ooga.model.PlaceAction;
import ooga.model.Player;
import ooga.model.StationaryAction;
import ooga.model.exception.CannotBuildHouseException;
import ooga.model.exception.NoColorAttributeException;

public interface ControllerPlace {
  /**
   * Get the id of the place. The id corresponds to more detailed data in .json format.
   *
   * @return the id of the place
   */
  int getPlaceId();


  /**
   * Get a collection of all the players that are currently on the place (not necessarily possess that place).
   *
   * @return
   */
  Collection<ControllerPlayer> getPlayers();

  double getMoney();

  /**
   * A method to update place actions for the current player
   *
   * @return Collection of place actions
   *
   */
  Collection<PlaceAction> getPlaceActions();

  /**
   *
   * @return number of houses on place
   * When houses can't be built on place
   * @throws CannotBuildHouseException
   *
   */
  int getHouseCount() throws CannotBuildHouseException;
  /**
   *
   * @return color of place
   * When the place doesn't belong to some colorset
   * @throws NoColorAttributeException
   *
   */
  int getColorSetId() throws NoColorAttributeException;
}
