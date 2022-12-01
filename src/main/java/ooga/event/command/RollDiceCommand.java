package ooga.event.command;

/**
 * This is created by GameView when it wants to tell the controller that the user wants to roll the
 * dice.
 */
public class RollDiceCommand implements Command {

  /**
   * I don't believe there's anything that needs to be returned here...?
   * @return
   */
  @Override
  public Object getCommandArgs() {
    return null;
  }
}
