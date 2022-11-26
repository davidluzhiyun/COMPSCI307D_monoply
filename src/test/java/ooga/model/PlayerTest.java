package ooga.model;

import ooga.model.place.Place;
import ooga.model.place.property.ConcreteStreet;
import ooga.model.place.property.Property;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
  ConcretePlayer player;

  @BeforeEach
  void setUpTest() {
    player = new ConcretePlayer(0);
  }

  @Test
  void getPlayId() {
    assertEquals(0, player.getPlayerId());
  }

  @Test
  void getTotalMoney() {
    player.earnMoney(200);
    assertEquals(200, player.getTotalMoney());
  }

  @Test
  void getProperties() {
    Property place = new ConcreteStreet(121);
    player.purchase(place);
    List<Place> places = List.of(place);
    assertEquals(places, player.getProperties());
  }

  @Test
  void move() {
    player.move(new ConcreteStreet(121));
    assertEquals(121, player.getCurrentPlaceId());
  }

  @Test
  void rollTestInJail() {
    player.newTurn();
    player.decrementOneDiceLeft();
    player.addOneDiceRoll();
    player.decrementOneDiceLeft();
    player.addOneDiceRoll();
    player.decrementOneDiceLeft();
    player.addOneDiceRoll();
    assertTrue(player.isInJail());
  }

  @Test
  void rollTestNotInJail() {
    player.newTurn();
    player.decrementOneDiceLeft();
    player.addOneDiceRoll();
    player.decrementOneDiceLeft();
    player.addOneDiceRoll();
    player.decrementOneDiceLeft();
    assertFalse(player.isInJail());
  }
}