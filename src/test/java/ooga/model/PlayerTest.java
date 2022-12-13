package ooga.model;

import ooga.event.GameEventHandler;
import ooga.model.place.Place;
import ooga.model.place.property.ConcreteStreet;
import ooga.model.place.property.Property;
import ooga.model.player.BuildHouseCheckerNoColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
  ConcretePlayer player;

  @BeforeEach
  void setUpTest() {
    player = new ConcretePlayer(0, new BuildHouseCheckerNoColor());
  }

  @Test
  void getPlayId() {
    assertEquals(0, player.getPlayerId());
  }

  @Test
  void getTotalMoney() {
    player.setMoney(200);
    assertEquals(200, player.getTotalMoney());
  }

  @Test
  void getProperties() {
    Property place = new ConcreteStreet("121", new GameEventHandler());
    player.purchase(place, 1);
    List<Place> places = List.of(place);
    assertEquals(places, player.getPropertyIndices());
  }

  @Test
  void move() {
    player.setIndex(1);
    assertEquals(1, player.getCurrentPlaceIndex());
  } 

  @Test
  void rollTestInJail() {
    player.newTurn();
    player.nextDice();
    player.addOneDiceRoll();
    player.nextDice();
    player.addOneDiceRoll();
    player.nextDice();
    player.addOneDiceRoll();
    assertEquals(3, player.remainingJailTurns());
  }

  @Test
  void rollTestNotInJail() {
    player.newTurn();
    player.nextDice();
    player.addOneDiceRoll();
    player.nextDice();
    player.addOneDiceRoll();
    player.nextDice();
    assertEquals(0, player.remainingJailTurns());
  }
}