package ooga.event.command;

public class DiceResultCommand implements Command {

  private int myRoll;

  public DiceResultCommand (int roll) {
    this.myRoll = roll;
  }

  @Override
  public Object getCommandArgs() {
    return myRoll;
  }
}
