//package ooga.model.colorSet;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import ooga.model.place.Place;
//import ooga.model.place.property.Street;
//import org.junit.jupiter.api.Test;
//
//class ConcreteColorSetTest {
//  private final ImmutableColorSet myColorSet;
//  private final Collection<Place> properties;
//  private final Collection<Integer> expected;
//  ConcreteColorSetTest(){
//    Collection<Place> places= new ArrayList<Place>();
//    properties = new ArrayList<Place>();
//    expected = new ArrayList<Integer>();
//    expected.add(1);
//    places.add(new DummyPlace(0));
//    Street street1 = new DummyStreet(1,1);
//    places.add(street1);
//    properties.add(street1);
//    Street street2 = new DummyStreet(1,2);
//    places.add(street2);
//    properties.add(street2);
//    Street street3 = new DummyStreet(2,3);
//    places.add(street2);
//    properties.add(street2);
//    Street street4 = new DummyStreet(2,4);
//    places.add(street4);
//    myColorSet = new ConcreteColorSet(places);
//  }
//  private boolean collectionEqual(Collection<Integer> c1,Collection<Integer> c2){
//    for (int elem : c1){
//      if (!c2.contains(elem)){
//        return false;
//      }
//    }
//    for (int elem : c2){
//      if (!c1.contains(elem)){
//        return false;
//      }
//    }
//    return true;
//  }
//  @Test
//  void monopolizedByTest(){
//    Collection<Integer> actual = myColorSet.monopolizedBy(properties);
//    assert (collectionEqual(actual,expected));
//  }
//
//
//}