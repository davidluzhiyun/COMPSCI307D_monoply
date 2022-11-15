package ooga.model.colorSet;

import java.util.Collection;
import ooga.model.place.Place;

/**
 * Immutable interface for color set
 */
public interface ImmutableColorSet {
  /**
   * Check the complete color sets a collection of places contains
   * @param properties a collection of places, such as the properties owned by a player
   * @return a collection of integer representing the color sets the input has monopoly over
   */
  public Collection<Integer> monopolizedBy(Collection<Place> properties);
}
