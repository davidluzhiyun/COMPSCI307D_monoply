package ooga.model;

import java.awt.*;

public class ConcreteDice implements Dice {

  @Override
  public Point roll() {
    int r1 = (int) ((Math.random() * 6) + 1);
    int r2 = (int) ((Math.random() * 6) + 1);
    System.out.printf("%d %d%n", r1, r2);
    return new Point(r1, r2);
    //TODO: roll doubles
  }
}
