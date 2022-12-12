package ooga.model.player;

import ooga.model.Player;
import ooga.model.exception.CannotBuildHouseException;
import ooga.model.exception.NoColorAttributeException;
import ooga.model.place.Place;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BuildHouseChecker implements CanBuildOn {
  private Player player;
  public BuildHouseChecker(Player player) {
    this.player = player;
  }


  /**
   * @param place  the place to check
   * @param target the latter place
   * @author David Lu
   * Helper funtion to check if the place has at least a certain number of houses as another place
   */
  private boolean checkHouseNum(Place place, Place target) {
    try {
      int placeHouseNum = place.getHouseCount();
      return placeHouseNum >= target.getHouseCount();
    } catch (CannotBuildHouseException e) {
      return false;
    }
  }


  @Override
  public boolean canBuildOn(Place place) {
    try {
      int color = place.getColorSetId();
      Predicate<Collection<Place>> checker = player.getColorSetCheckers().get(color);
      if (checker == null) {
        return false;
      }
      return checker.test(player.getProperties().stream().filter((Place p) -> checkHouseNum(p, place)).collect(
          Collectors.toSet()));
    } catch (NoColorAttributeException e) {
      // not something with a color
      return false;
    } catch (NullPointerException e) {
      throw new IllegalStateException("Input is null or checker unset", e);
    }
  }
}
