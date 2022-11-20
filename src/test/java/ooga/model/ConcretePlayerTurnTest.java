//package ooga.model;
//
//import ooga.model.place.Place;
//import ooga.model.place.property.ConcreteStreet;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class ConcretePlayerTurnTest {
//  static ConcretePlayerTurn turn;
//  @BeforeAll
//  static void setUpTest() {
//    List<Place> places = List.of(new ConcreteStreet(121), new ConcreteStreet(121), new ConcreteStreet(121), new ConcreteStreet(121), new ConcreteStreet(121), new ConcreteStreet(121), new ConcreteStreet(121), new ConcreteStreet(121));
//    List<Player> players = List.of(new ConcretePlayer(0), new ConcretePlayer(1), new ConcretePlayer(2), new ConcretePlayer(3));
//    turn = new ConcretePlayerTurn(players, places);
//  }
//
//  @Test
//  void testMultipleTurnsOnePlayer() {
//  }
//
//  @Test
//  void test() {
//    System.out.println(turn.getStationaryActions());
//    turn.roll();
//    System.out.println(turn.getCurrentPlayerTurnId());
//    System.out.println(turn.getStationaryActions());
//  }
//}