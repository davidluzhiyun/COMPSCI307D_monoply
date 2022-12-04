package ooga.model;

import ooga.model.place.Place;
import ooga.model.place.property.ConcreteStreet;
import ooga.model.place.property.Property;
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
    Property place = new ConcreteStreet("121");
    player.purchase(place, 1);
    List<Place> places = List.of(place);
    assertEquals(places, player.getPropertyIndices());
  }

  @Test
  void move() {
    player.move(1);
    assertEquals(1, player.getCurrentPlaceIndex());
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