package ooga.model.colorSet;

import java.util.Collection;
import java.util.Map;
import java.util.function.Predicate;
import ooga.model.place.Place;

/**
 * Immutable interface for color set
 */
public interface ImmutableColorSet {

  /**
   * Output ColorSet checkers
   * @return a map that maps each color to a predicate that checks if
   * a collection of places have monopoly over a color id
   */
  Map<Integer, Predicate<Collection<Place>>> outputCheckers();
}
