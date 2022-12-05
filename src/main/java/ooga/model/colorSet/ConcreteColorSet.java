
package ooga.model.colorSet;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import ooga.model.GameModel;
import ooga.model.place.Place;
import ooga.model.place.property.Property;
import ooga.model.place.property.Street;
import org.jetbrains.annotations.NotNull;

/**
 * Concrete class for managing color sets of streets
 * Used to check relationship between a collection of streets and color sets
 * For future used add in more methods
 * Rudimentary demo, design subject to change based on that of other classes
 * For example: remove down-casting, output predicate etc.
 */
public class ConcreteColorSet implements ImmutableColorSet {

  private Map<Integer, Collection<String>> allSets;
  private List<Place> boardCopy;

  /**
   * Constructs color sets from a collection of places
   *
   * @param places a collection of places, for example some representation of the board=
   */
  public ConcreteColorSet(List<Place> places) throws IllegalStateException {
    try {
      allSets = new HashMap<Integer, Collection<String>>();
      boardCopy = new ArrayList<>(places);
      for (Place place : places) {
        // Here the down-casting and typechecking is for ignoring non-Street places
        // Remove if plans to only input streets
        if (place instanceof Street myStreet) {
          if (allSets.get(myStreet.getColorSetId()) == null) {
            Collection<String> newColorSet = new HashSet<>();
            newColorSet.add(myStreet.getPlaceId());
            allSets.put(myStreet.getColorSetId(), newColorSet);
          } else {
            allSets.get(myStreet.getColorSetId()).add(myStreet.getPlaceId());
          }
        }
      }
    } catch (NullPointerException e) {
      throw new IllegalStateException("Board can't be null", e);
    }
  }

  /**
   * private method to see if a collection of places have monopoly over a color id
   *
   * @param properties collection of places such as the properties someone own or some subset of it.
   *                   If collection is null return false
   * @param colorId    the color id corresponding to the color id we want to check if invalid return
   *                   false
   * @return See description above
   */
  private boolean check(Collection<Place> properties, int colorId) {
    if (properties == null) {
      return false;
    }
    Collection<String> propertyPlaceIds = properties.stream().map(Place::getPlaceId)
        .collect(Collectors.toSet());
    Collection<String> targetPlaceIds = allSets.get(colorId);
    if (targetPlaceIds == null) {
      return false;
    }
    return propertyPlaceIds.containsAll(targetPlaceIds);
  }
  /**
   * Output ColorSet checkers
   * @return a map that maps each color to a predicate that checks if
   * a collection of places have monopoly over a color id
   */
  public Map<Integer, Predicate<Collection<Place>>> outputCheckers() {
    Map<Integer, Predicate<Collection<Place>>> output = new HashMap<>();
    for (int colorId : allSets.keySet()) {
      output.put(colorId, (Collection<Place> a) -> check(a, colorId));
    }
    return output;
  }
}
