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
    places = List.of(new ConcreteStreet("New York1"), new ConcreteStreet("New York1"), new ConcreteStreet("Shanghai1"), new ConcreteStreet("Shanghai1"), new ConcreteStreet("121"), new ConcreteStreet("121"), new ConcreteStreet("121"), new ConcreteStreet("121"));
    Player p1 = new ConcretePlayer(0);
    p1.setMoney(1500);
    players = List.of(p1, new ConcretePlayer(1), new ConcretePlayer(2), new ConcretePlayer(3));
    turn = new ConcretePlayerTurn(players, places, 0);
  }

  @Test
  void testMultipleTurnsOnePlayer() {
  }

  @Test
  void test() {
    System.out.println(turn.getCurrentPlace().getStationaryActions(players.get(turn.getCurrentPlayerTurnId())));
    turn.roll();
    System.out.println(turn.getCurrentPlace().getStationaryActions(players.get(turn.getCurrentPlayerTurnId())));
    System.out.println(turn.getCurrentPlayerTurnId());
    turn.nextTurn();
    System.out.println(turn.getCurrentPlayerTurnId());
    System.out.println(turn.getCurrentPlace().getStationaryActions(players.get(turn.getCurrentPlayerTurnId())));
  }
}