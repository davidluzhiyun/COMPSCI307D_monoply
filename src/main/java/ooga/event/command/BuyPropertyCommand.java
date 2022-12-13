package ooga.event.command;


public class BuyPropertyCommand implements Command {

  private final int property;

  public BuyPropertyCommand(int propertyIndex) {
    this.property = propertyIndex;
  }

  @Override
  public Object getCommandArgs() {
    return property;
  }
}
