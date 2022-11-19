package ooga.event.command;


import ooga.model.GameData;

public class GameDataCommand implements Command {
  private GameData gameData;

  public GameDataCommand(GameData data) {
    gameData = data;
  }

  @Override
  public Object getCommandArgs() {
    return gameData;
  }
}
