package ooga.controller;

import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventListener;
import ooga.event.command.Command;
import ooga.view.SampleViewData;

public class SampleController implements GameEventListener {
  private GameEventHandler gameEventHandler;
  public SampleController(GameEventHandler gameEventHandler){
    this.gameEventHandler = gameEventHandler;
  }

  @Override
  public void onGameEvent(GameEvent event) {
    switch(event.getGameEventType()){
      case "VIEW_TO_CONTROLLER_GAME_START":
        Command cmd = event.getGameEventCommand().getCommand();
        gameStart(cmd);
        break;
    }
  }

  private void gameStart(Command cmd){
    SampleViewData d = (SampleViewData) cmd.getCommandArgs();
    System.out.println(d.getData());
  }
}
