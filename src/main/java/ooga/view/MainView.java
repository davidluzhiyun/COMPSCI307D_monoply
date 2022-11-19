package ooga.view;

import javafx.stage.Stage;
import ooga.event.GameEventHandler;

public class MainView {

  private final GameEventHandler myGameEventHandler;

  public MainView(Stage primaryStage, GameEventHandler gameEventHandler) {
    myGameEventHandler = gameEventHandler;
  }

  public void start() {

  }
}
