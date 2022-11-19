package ooga.event.command;

import java.io.File;

public class GameStartViewCommand implements Command {

  private File myConfigFile;

  public GameStartViewCommand(File configFile) {
    this.myConfigFile = configFile;
  }

  @Override
  public Object getCommandArgs() {
    return myConfigFile;
  }
}
