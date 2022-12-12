package ooga.model.player;

import ooga.model.place.Place;

import java.util.Collection;
import java.util.Map;
import java.util.function.Predicate;

public class BuildHouseCheckerNoColor implements CanBuildOn {
  @Override
  public boolean canBuildOn(Place place, Map<Integer, Predicate<Collection<Place>>> colorSetCheckers, Collection<Place> properties, int playerId) {
    return place.getOwnerId() == playerId;
  }
}
