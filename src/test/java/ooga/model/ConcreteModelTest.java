package ooga.model;

import ooga.event.GameEventHandler;
import ooga.model.place.Place;
import ooga.model.place.property.ConcreteStreet;
import ooga.model.place.property.Property;
import ooga.model.place.property.Street;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

import static ooga.model.ConcreteModel.DEFAULT_RESOURCE_PACKAGE;
import static ooga.model.place.AbstractPlace.PLACE_PACKAGE_NAME;
import static org.junit.jupiter.api.Assertions.*;

class ConcreteModelTest {
  static ConcreteModel model;
  private static ResourceBundle modelResources;

  @BeforeAll
  static void setUpTest() {
    model = new ConcreteModel(new GameEventHandler());
    modelResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "Model");
  }

  @Test
  void testCreatePlaceStreet() {
    Street street = (Street) model.createPlace("Street", 121);
    assertEquals(100, street.getHousePrice());
  }
}