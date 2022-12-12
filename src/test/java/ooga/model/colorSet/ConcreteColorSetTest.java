package ooga.model.colorSet;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.function.Predicate;
import ooga.model.place.Place;
import ooga.model.place.property.Street;
import org.junit.jupiter.api.Test;

class ConcreteColorSetTest {
  private final ImmutableColorSet myColorSet;
  private final Collection<Place> properties;
  private final Collection<Integer> expected;
  ConcreteColorSetTest(){
    Collection<Place> places= new ArrayList<Place>();
    properties = new ArrayList<Place>();
    expected = new ArrayList<Integer>();
    expected.add(1);
    places.add(new DummyPlace("0"));
    Street street1 = new DummyStreet(1,"1");
    places.add(street1);
    properties.add(street1);
    Street street2 = new DummyStreet(1,"2");
    places.add(street2);
    properties.add(street2);
    Street street3 = new DummyStreet(2,"3");
    places.add(street3);
    Street street4 = new DummyStreet(2,"4");
    places.add(street4);
    properties.add(street4);
    myColorSet = new ConcreteColorSet(places);
  }
  @Test
  void badConstructorTest(){
    assertThrows(IllegalStateException.class,()->new ConcreteColorSet(null));
  }
  private boolean collectionEqual(Collection<Integer> c1,Collection<Integer> c2){
    for (int elem : c1){
      if (!c2.contains(elem)){
        return false;
      }
    }
    for (int elem : c2){
      if (!c1.contains(elem)){
        return false;
      }
    }
    return true;
  }
  @Test
  void outputTest(){
    Map<Integer, Predicate<Collection<Place>>> checkers = myColorSet.outputCheckers();
    assertTrue(checkers.get(1).test(properties));
    assertFalse(checkers.get(1).test(null));
    assertFalse(checkers.get(2).test(properties));
  }
}