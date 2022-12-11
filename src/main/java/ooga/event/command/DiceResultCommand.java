package ooga.event.command;

import java.awt.*;

public class DiceResultCommand implements Command {

  private Point roll;

  public DiceResultCommand (Point roll) {
    this.roll = roll;
  }

  @Override
  public Object getCommandArgs() {
    return this.roll;
  }
}
