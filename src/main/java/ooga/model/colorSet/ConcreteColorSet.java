
package ooga.model.colorSet;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import ooga.model.place.Place;
import ooga.model.place.property.Property;
import ooga.model.place.property.Street;

/**
 * Concrete class for managing color sets of streets
 * Used to check relationship between a collection of streets and color sets
 * For future used add in more methods
 * Rudimentary demo, design subject to change based on that of other classes
 * For example: remove down-casting, output predicate etc.
 */
public class ConcreteColorSet implements ImmutableColorSet{
  private Map<Integer, Collection<String>> allSets;

  /**
   * Constructs color sets from a collection of places
   * @param places a collection of places, for example some representation of the board
   */
  public ConcreteColorSet(Collection<Place> places){
    allSets = new HashMap<Integer, Collection<String>>();
    for(Place place : places){
      // Here the down-casting and typechecking is for ignoring non-Street places
      // Remove if plans to only input streets
      if (place instanceof Street myStreet){
        if (allSets.get(myStreet.getColorSetId()) == null){
          Collection<String> newColorSet = new ArrayList<>();
          newColorSet.add(myStreet.getPlaceId());
          allSets.put(myStreet.getColorSetId(),newColorSet);
        }
        else {
          allSets.get(myStreet.getColorSetId()).add(myStreet.getPlaceId());
        }
      }
    }
  }

  /**
   * Check the complete color sets a collection of places contains
   * @param properties a collection of places, such as the properties owned by a player
   * @return a collection of integer representing the color sets the input has monopoly over
   */
  public Collection<Integer> monopolizedBy(Collection<Place> properties){
    Collection<String> placeIds = new ArrayList<>();
    Collection<Integer> colorIds = new ArrayList<>();
    for(Place place : properties){
      placeIds.add(place.getPlaceId());
    }
    for (int color : allSets.keySet()){
      boolean monopolized = true;
      for (String place : allSets.get(color)){
        if (!placeIds.contains(place)){
          monopolized = false;
          break;
        }
      }
      if (monopolized){
        colorIds.add(color);
      }
    }
    return colorIds;
  }
}
