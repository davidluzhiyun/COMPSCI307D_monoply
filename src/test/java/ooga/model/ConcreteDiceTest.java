package ooga.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConcreteDiceTest {
  Dice dice = new ConcreteDice();

  @Test
  void roll() {
    for (int i = 0; i < 100; i++) {
      assertTrue(dice.roll() <= 12 && dice.roll() >= 2);
    }
  }
}