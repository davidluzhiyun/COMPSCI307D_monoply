package ooga.event.command;

public class RequestPlaceActionsCommand implements Command{
  private int index;

  public RequestPlaceActionsCommand(int index) {this.index = index;}
  @Override
  public Object getCommandArgs() {return index;}
}
