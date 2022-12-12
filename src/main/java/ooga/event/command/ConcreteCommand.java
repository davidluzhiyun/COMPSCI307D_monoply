package ooga.event.command;

public class ConcreteCommand<T> implements Command<T>{
  private final T argument;
  public ConcreteCommand(T t) {
    this.argument = t;
  }
  @Override
  public T getCommandArgs() {
    return argument;
  }
}
