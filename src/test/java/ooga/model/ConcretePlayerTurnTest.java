package ooga.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConcretePlayerTurnTest {
  ConcretePlayerTurn turn = new ConcretePlayerTurn(4);

  @Test
  void roll() {
    for (int i = 0; i < 100; i++) {
      assertTrue(turn.roll() <= 12 && turn.roll() >= 2);
    }
  }

  @Test
  void testMultipleTurnsOnePlayer() {
  }
}