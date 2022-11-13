package ooga.model;

import java.awt.*;

public class ConcreteDice implements Dice {

  @Override
  public int roll() {
    return (int) ((Math.random() * 6) + 1) +  (int) ((Math.random() * 6) + 1);
    //TODO: roll doubles
  }
}
