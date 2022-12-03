package ooga.event.command;

public class DiceResultCommand implements Command {

  private int[] myRoll;

  public DiceResultCommand (int roll1, int roll2) {
    this.myRoll = new int[]{roll1, roll2};
  }

  @Override
  public Object getCommandArgs() {
    return myRoll;
  }
}
