package ooga.view;

import javafx.stage.Stage;
import ooga.event.GameEventHandler;
import ooga.view.scene.SceneManager;

public class MainView {

  private final GameEventHandler myGameEventHandler;

  public MainView(Stage primaryStage, GameEventHandler gameEventHandler) {
    myGameEventHandler = gameEventHandler;
    SceneManager sceneManager = new SceneManager(primaryStage, "English", gameEventHandler);
    myGameEventHandler.addEventListener(sceneManager);
  }

  public void start() {

  }
}
