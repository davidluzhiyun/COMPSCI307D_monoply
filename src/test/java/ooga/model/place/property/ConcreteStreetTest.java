package ooga.model.place.property;

import ooga.event.GameEventHandler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConcreteStreetTest {
  static Street street;

  @BeforeAll
  static void setUpTest() {
    street = new ConcreteStreet("Shanghai1", new GameEventHandler());
  }

  @Test
  void testColorId() {
    assertEquals(0, street.getColorSetId());
  }

  @Test
  void testHousePrice() {
    assertEquals(100, street.getPurchasePrice());
  }

  @Test
  void testRentPrices() {
    List<Double> expected = List.of(30.0, 90.0, 270.0, 400.0, 550.0);
    assertEquals(expected, street.getRentWithProperties());
  }
}