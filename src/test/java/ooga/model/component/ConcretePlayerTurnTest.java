package ooga.model.component;

import ooga.event.GameEventHandler;
import ooga.model.ConcretePlayer;
import ooga.model.Player;
import ooga.model.place.Place;
import ooga.model.place.property.ConcreteRailroad;
import ooga.model.place.property.ConcreteStreet;
import ooga.model.place.property.ConcreteUtility;
import ooga.model.place.property.Street;
import ooga.model.player.BuildHouseCheckerNoColor;
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
  static Street NewYork;

  @BeforeAll
  static void setUpTest() {
    utility = new ConcreteUtility("9", new GameEventHandler());
    NewYork = new ConcreteStreet("New York1", new GameEventHandler());
    places = List.of(NewYork, new ConcreteStreet("New York1", new GameEventHandler()), new ConcreteStreet("Shanghai1", new GameEventHandler()), new ConcreteStreet("Shanghai1", new GameEventHandler()), utility, new ConcreteRailroad("45", new GameEventHandler()), new ConcreteStreet("121", new GameEventHandler()), new ConcreteStreet("121", new GameEventHandler()));
    p1 = new ConcretePlayer(0, new BuildHouseCheckerNoColor());
    p2 = new ConcretePlayer(1, new BuildHouseCheckerNoColor());
    p1.setMoney(1500);
    p2.setMoney(1500);

    p2.purchase(utility, 4);
    p2.purchase(NewYork, 0);
<<<<<<< HEAD
    players = List.of(p1, p2, new ConcretePlayer(2), new ConcretePlayer(3));
    turn = new ConcretePlayerTurn(players, places, 0, new GameEventHandler());
=======
    players = List.of(p1, p2, new ConcretePlayer(2, new BuildHouseCheckerNoColor()), new ConcretePlayer(3, new BuildHouseCheckerNoColor()));
    turn = new ConcretePlayerTurn(players, places, 0);
>>>>>>> master
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
    System.out.println(players.get(turn.getCurrentPlayerTurnId()).getTotalMoney());
    turn.nextTurn();
    System.out.println(turn.getCurrentPlayerTurnId());
    System.out.println(turn.getCurrentPlace());
    System.out.println(players.get(turn.getCurrentPlayerTurnId()).getTotalMoney());
    System.out.println(turn.getCurrentPlace().getStationaryActions(players.get(turn.getCurrentPlayerTurnId())));
    for (Place place : places) {
      place.updateCurrentPlayerPlaceActions(p2);
    }
    System.out.println(places.get(0).getPlaceActions());
  }
}