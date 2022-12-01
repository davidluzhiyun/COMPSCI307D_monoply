package ooga.model;

import ooga.model.components.ConcretePlayerTurn;
import ooga.model.place.Place;
import ooga.model.place.property.ConcreteStreet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

class ConcretePlayerTurnTest {
  static ConcretePlayerTurn turn;
  static List<Place> places;
  static List<Player> players;
  @BeforeAll
  static void setUpTest() {
    places = List.of(new ConcreteStreet(121), new ConcreteStreet(121), new ConcreteStreet(121), new ConcreteStreet(121), new ConcreteStreet(121), new ConcreteStreet(121), new ConcreteStreet(121), new ConcreteStreet(121));
    players = List.of(new ConcretePlayer(0), new ConcretePlayer(1), new ConcretePlayer(2), new ConcretePlayer(3));
    turn = new ConcretePlayerTurn(players, places);
  }

  @Test
  void testMultipleTurnsOnePlayer() {
  }

  @Test
  void test() {
    System.out.println(turn.getCurrentPlace().getStationaryActions(players.get(turn.getCurrentPlayerTurnId())));
    turn.roll();
    System.out.println(turn.getCurrentPlace().getStationaryActions(players.get(turn.getCurrentPlayerTurnId())));
    System.out.println(turn.getCurrentPlace().getStationaryActions(players.get(turn.getCurrentPlayerTurnId())));
  }
}