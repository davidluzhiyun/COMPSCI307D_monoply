package ooga.view;

import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventListener;

public class GameView extends View implements GameEventListener {

  private GameEventHandler gameEventHandler;
  public GameView(GameEventHandler gameEventHandler) {
    this.gameEventHandler = gameEventHandler;
  }

  @Override
  public InteractiveObject makeInteractiveObject(String name) {
    return null;
  }

  @Override
  public void onGameEvent(GameEvent event) {

  }
}
