package ooga.event;

public class GameEvent {

  private final GameEventType type;
  private GameEventCommand cmd;

  public GameEvent(GameEventType type) {
    this.type = type;
  }

  public GameEvent(GameEventType type, GameEventCommand cmd) {
    this(type);
    this.cmd = cmd;
  }

  @Override
  public String toString() {
    return String.format("GameEventType: %s", type);
  }

  public String getGameEventType() {
    return String.format("%s", type);
  }

  public GameEventCommand getGameEventCommand() {
    return cmd;
  }
}
