package ooga.event.command;

public class RequestPropertyInfoCommand implements Command  {
  private int property;
  public RequestPropertyInfoCommand(int property) {
    this.property = property;
  }

  @Override
  public Object getCommandArgs() {
    return property;
  }
}
