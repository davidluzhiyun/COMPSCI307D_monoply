package ooga.model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ConcreteDiceTest {

  @Test
  void roll() {
    Dice dice = new ConcreteDice();
    Point result;
    int sum;
    for (int i = 0; i < 100; i++) {
      result = dice.roll();
      sum = result.x + result.y;
      assertTrue(sum <= 12 && sum >= 2);
    }
  }
}