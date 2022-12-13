package ooga.model.component;

import ooga.event.GameEventHandler;
import ooga.model.player.*;
import ooga.model.place.Place;
import ooga.model.place.property.ConcreteRailroad;
import ooga.model.place.property.ConcreteStreet;
import ooga.model.place.property.ConcreteUtility;
import ooga.model.place.property.Street;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

class ConcretePlayerTurnTest {
  static ConcretePlayerTurn turn;
  static List<Place> places;
  static List<Player> players;
  static Player p1;
  static Player p2;
  static Player p3;
  static Player p4;
  static Place utility;
  static Street NewYork;
  static ManipulableDice myDice;

  @BeforeAll
  static void setUpTest() {
    utility = new ConcreteUtility("9", new GameEventHandler());
    NewYork = new ConcreteStreet("New York1", new GameEventHandler());
    places = List.of(NewYork, new ConcreteStreet("New York1", new GameEventHandler()), new ConcreteStreet("Shanghai1", new GameEventHandler()), new ConcreteStreet("Shanghai1", new GameEventHandler()), utility, new ConcreteRailroad("45", new GameEventHandler()), new ConcreteStreet("121", new GameEventHandler()), new ConcreteStreet("121", new GameEventHandler()));
    p1 = new ConcretePlayer(0, new GameEventHandler(), new BuildHouseCheckerColor());
    p1.setAddOneDiceRollJail(new AddOneDiceRollJail(p1));
    p2 = new ConcretePlayer(0, new GameEventHandler(), new BuildHouseCheckerColor());
    p2.setAddOneDiceRollJail(new AddOneDiceRollJail(p2));
    p3 = new ConcretePlayer(0, new GameEventHandler(), new BuildHouseCheckerColor());
    p3.setAddOneDiceRollJail(new AddOneDiceRollJail(p3));
    p4 = new ConcretePlayer(0, new GameEventHandler(), new BuildHouseCheckerColor());
    p4.setAddOneDiceRollJail(new AddOneDiceRollJail(p4));
    p1.setMoney(1500);
    p2.setMoney(1500);

    p2.purchase(utility, 4);
    p2.purchase(NewYork, 0);

    players = List.of(p1, p2, p3, p4);
    turn = new ConcretePlayerTurn(players, places, 0, new GameEventHandler());
    myDice = new ManipulableDice();
    turn.setDice(myDice);
  }

  @Test
  void testMultipleTurnsOnePlayer() {
  }

  @Test
  void test() {
    // Added newline to ensure the old test work as intended
    turn.setDice(new ConcreteDice());
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

  /**
   * Testing the functionality of rolling doubles
   * @author David Lu
   */
  @Test
  void rollingDoubles(){
    myDice.set(1,1);
    turn.roll();
    assert (p1.hasNextDice());
  }
  /**
   * Testing the functionality of rolling doubles and go to jail, then get out
   * @author David Lu
   */
  @Test
  void jailDouble(){
    myDice.set(1,1);
    for (int i = 1; i <= ConcretePlayer.MAX_ROWS_IN_A_ROW + 1; i++) {
      turn.roll();
    }
    assert (p1.remainingJailTurns() > 0);
    int jailIndex = p1.getCurrentPlaceIndex();
    myDice.set(1,2);
    turn.roll();
    assert (p1.getCurrentPlaceIndex() == jailIndex);
    myDice.set(1,1);
    turn.roll();
    assert (p1.remainingJailTurns() == 0);
    assert (p1.getCurrentPlaceIndex() == jailIndex + 2);
  }
  /**
   * Testing the functionality of getting out of jail by spending enough turns
   */
  @Test
  void jailTurns(){
    p1.setJail(ConcretePlayer.DEFAULT_JAIL_TURNS);
    int jailIndex = p1.getCurrentPlaceIndex();
    myDice.set(1,2);
    for (int i = 0; i < ConcretePlayer.DEFAULT_JAIL_TURNS; i++) {
      for (int j = 0; j < players.size(); i++) {
        turn.roll();
        turn.nextTurn();
      }
    }
    assert (p1.remainingJailTurns() == 0);
    assert (p1.getCurrentPlaceIndex() == jailIndex + 3);
    assert (p1.getTotalMoney() == 1500 - ConcretePlayer.DEFAULT_FINE);
  }
  /**
   * Testing the basic functionality of bankruptcy
   */
  @Test
  void bankruptcy(){
    p1.setMoney(-1);
    turn.nextTurn();
    assert (!p1.isAlive());
    for (int i = 0; i < 3; i++) {
      turn.nextTurn();
    }
    assert (turn.getCurrentPlayerTurnId() == 1);
  }
}