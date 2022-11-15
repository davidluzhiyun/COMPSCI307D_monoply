package ooga.model;

import ooga.model.place.Place;
import ooga.model.place.property.ConcreteStreet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConcretePlayerTurnTest {
  static ConcretePlayerTurn turn;
  @BeforeAll
  static void setUpTest() {
    List<Place> places = List.of(new ConcreteStreet(121), new ConcreteStreet(121), new ConcreteStreet(121), new ConcreteStreet(121), new ConcreteStreet(121), new ConcreteStreet(121), new ConcreteStreet(121), new ConcreteStreet(121));
    turn = new ConcretePlayerTurn(List.of(new ConcretePlayer(0), new ConcretePlayer(1), new ConcretePlayer(2), new ConcretePlayer(3)), places);
  }

//  @Test
//  void roll() {
//    for (int i = 0; i < 100; i++) {
//      assertTrue(turn.rollDice() <= 12 && turn.rollDice() >= 2);
//    }
//  }

  @Test
  void testMultipleTurnsOnePlayer() {
  }

  @Test
  void test() {
    System.out.println(turn.getStationaryActions());
    turn.roll();
    System.out.println(turn.getCurrentPlayerTurnId());
    System.out.println(turn.getStationaryActions());
  }
}