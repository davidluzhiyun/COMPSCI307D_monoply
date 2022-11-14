package ooga.model.place.property;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConcreteStreetTest {
  static Street street;

  @BeforeAll
  static void setUpTest() {
    street = new ConcreteStreet(121);
  }

  @Test
  void getColorId() {
  }

  @Test
  void getHousePrice() {
    System.out.println(street.getPurchasePrice());
  }

  @Test
  void getHousesBuilt() {
  }
}