package ooga.model.player;

import ooga.model.place.AbstractPlace;
import ooga.model.place.Place;

import java.util.Collection;
import java.util.Map;
import java.util.function.Predicate;

public interface CanBuildOn {
  public static final String PLAYER_PACKAGE_NAME = CanBuildOn.class.getPackageName() + ".";
  boolean canBuildOn(Place place, Map<Integer, Predicate<Collection<Place>>> colorSetCheckers, Collection<Place> properties, int playerId);
}
