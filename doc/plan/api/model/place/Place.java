package model.place;

/**
 * The Place interface is shared feature of all the places in monopoly
 */
public interface Place {
  /**
   * Get the id of the place. The id corresponds to more detailed data in .json format.
   * @return the id of the place
   */
  int getPlaceId();
  /**
   * Get the player(s) the place.
   * @return the id of players on the place
   */
  int getPlayersId();
}
