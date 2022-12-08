package ooga.view;

//import static ooga.event.GameEventType.VIEW_LAUNCH_GAME_EDITOR_SCREEN;
import static ooga.event.GameEventType.VIEW_LAUNCH_GAME_SCREEN;

import javafx.stage.Stage;
import ooga.event.GameEventHandler;
import ooga.view.scene.SceneManager;

public class MainView {

  private final GameEventHandler myGameEventHandler;

  public MainView(Stage primaryStage, GameEventHandler gameEventHandler) {
    myGameEventHandler = gameEventHandler;
    SceneManager sceneManager = new SceneManager("English", gameEventHandler, "Light");
    myGameEventHandler.addEventListener(sceneManager);
  }

  public void start() {
//    myGameEventHandler.publish(VIEW_LAUNCH_GAME_SCREEN);
//    myGameEventHandler.publish(VIEW_LAUNCH_GAME_EDITOR_SCREEN);
  }
}
