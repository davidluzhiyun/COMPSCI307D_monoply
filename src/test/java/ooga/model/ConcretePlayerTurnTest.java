package ooga.model;

import ooga.model.components.ConcretePlayerTurn;
import ooga.model.place.Place;
import ooga.model.place.property.ConcreteRailroad;
import ooga.model.place.property.ConcreteStreet;
import ooga.model.place.property.ConcreteUtility;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

class ConcretePlayerTurnTest {
  static ConcretePlayerTurn turn;
  static List<Place> places;
  static List<Player> players;
  static Player p1;
  static Player p2;
  static Place utility;

  @BeforeAll
  static void setUpTest() {
    utility = new ConcreteUtility("9");
    places = List.of(new ConcreteStreet("New York1"), new ConcreteStreet("New York1"), new ConcreteStreet("Shanghai1"), new ConcreteStreet("Shanghai1"), utility, new ConcreteRailroad("45"), new ConcreteStreet("121"), new ConcreteStreet("121"));
    p1 = new ConcretePlayer(0);
    p2 = new ConcretePlayer(1);
    p1.setMoney(1500);
    p2.setMoney(1500);
    p2.purchase(utility, 4);
    players = List.of(p1, p2, new ConcretePlayer(2), new ConcretePlayer(3));
    turn = new ConcretePlayerTurn(players, places, 0);
  }

  @Test
  void testMultipleTurnsOnePlayer() {
  }

  @Test
  void test() {
    System.out.println(turn.getCurrentPlace().getStationaryActions(players.get(turn.getCurrentPlayerTurnId())));
    turn.roll();
//    p1.setIndex(4);
    System.out.println(turn.getCurrentPlace().getStationaryActions(players.get(turn.getCurrentPlayerTurnId())));
    System.out.println(turn.getCurrentPlayerTurnId());
    System.out.println(players.get(turn.getCurrentPlayerTurnId()).getTotalMoney());
    turn.nextTurn();
    System.out.println(turn.getCurrentPlayerTurnId());
    System.out.println(turn.getCurrentPlace());
    System.out.println(players.get(turn.getCurrentPlayerTurnId()).getTotalMoney());
    System.out.println(turn.getCurrentPlace().getStationaryActions(players.get(turn.getCurrentPlayerTurnId())));
  }
}