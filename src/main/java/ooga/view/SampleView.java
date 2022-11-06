package ooga.view;

import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventListener;
import ooga.event.command.Command;
import ooga.event.command.SampleCommand;

public class SampleView implements GameEventListener {
  private GameEventHandler gameEventHandler;
  public SampleView(GameEventHandler gameEventHandler){
    this.gameEventHandler = gameEventHandler;
  }
  public void start(){
    SampleViewData d = new SampleViewData(307);
    Command cmd = new SampleCommand(d);
    // Create a game Event
    GameEvent event = gameEventHandler.makeGameEventwithCommand("VIEW_TO_CONTROLLER_GAME_START", cmd);
    gameEventHandler.publish(event);
  }
  @Override
  public void onGameEvent(GameEvent event) {

  }
}
