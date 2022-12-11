package ooga.event.command;

import java.io.File;

public class SelectBoardEditConfigCommand implements Command<File> {

  private final File file;

  public SelectBoardEditConfigCommand(File config) {
    this.file = config;
  }

  @Override
  public File getCommandArgs() {
    return this.file;
  }
}
