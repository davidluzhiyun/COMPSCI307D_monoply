package ooga.model.place;

import java.util.Collection;
import ooga.model.PlaceAction;
import ooga.model.Player;
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
   *
   *
   * @return Get a collection of all the players that are currently on the place (not necessarily possess that place).
   */
  Collection<? extends Player> getPlayers();

  /**
   *
   * @return The money displayed on the place
   */
  double getMoney();

  /**
   *
   * @return a collection of place actions tied to the place. return null when forgot to update
   */
  Collection<PlaceAction> getPlaceActions();

  /**
   *
   * @return number of houses on the place
   * @throws CannotBuildHouseException if houses can't be built
   */
  int getHouseCount() throws CannotBuildHouseException;


  int getColorSetId() throws NoColorAttributeException;

}
