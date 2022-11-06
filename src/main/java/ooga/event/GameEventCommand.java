package ooga.event;

import ooga.event.command.Command;

public class GameEventCommand {
  private final String eventType;
  private Command cmd;

  public GameEventCommand(GameEventType t, Command cmd) {
    this.eventType = t.toString();
    this.cmd = cmd;
  }

  public Command getCommand() {
    return cmd;
  }
}
