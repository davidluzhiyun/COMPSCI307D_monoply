package ooga.event.command;


import ooga.model.ModelOutput;

public class GameDataCommand implements Command {
  private ModelOutput gameData;

  public GameDataCommand(ModelOutput data) {
    gameData = data;
  }

  @Override
  public Object getCommandArgs() {
    return gameData;
  }
}
