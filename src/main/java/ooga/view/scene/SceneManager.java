package ooga.view.scene;

import static ooga.event.GameEventType.VIEW_LAUNCH_GAME_SCREEN;

import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ooga.Main;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventListener;
import ooga.view.MainView;

public class SceneManager implements GameEventListener {

  private final Stage primaryStage;
  private final GameEventHandler gameEventHandler;
  private static final String DEFAULT_RESOURCE_DIR = Main.DEFAULT_RESOURCE_PACKAGE;
  private static final String BASE_DIR_UI_LANGUAGE = Main.DEFAULT_LANGUAGE_PACKAGE;

  private final ResourceBundle resources = ResourceBundle.getBundle(
      DEFAULT_RESOURCE_DIR + "UserInterface");

  private final ResourceBundle languageResources;
  private Scene currentScene;

  public SceneManager(Stage primaryStage, String language, GameEventHandler gameEventHandler) {
    this.primaryStage = primaryStage;
    this.gameEventHandler = gameEventHandler;
    languageResources = ResourceBundle.getBundle(BASE_DIR_UI_LANGUAGE + language);
    setupStage();
  }

  private void setupStage() {
    primaryStage.setTitle(languageResources.getString("Title"));
    primaryStage.setMinWidth(Double.parseDouble(resources.getString("MinWidth")));
    primaryStage.setMinHeight(Double.parseDouble(resources.getString("MinHeight")));
    primaryStage.setMaxWidth(Double.parseDouble(resources.getString("MaxWidth")));
    primaryStage.setMaxHeight(Double.parseDouble(resources.getString("MaxHeight")));
    primaryStage.show();
  }

  private void setMonopolyGamePlayScene() {
    MonopolyGamePlayScene monopolyScene = new MonopolyGamePlayScene(primaryStage);
    currentScene = new Scene(monopolyScene, primaryStage.getMaxWidth(),
        primaryStage.getMaxHeight());
    setPrimaryStageToCurrScene();
  }

  private void setMonopolyGameEditorScene() {
    MonopolyGameEditorScene monopolyGameEditorScene = new MonopolyGameEditorScene(primaryStage);
    currentScene = new Scene(monopolyGameEditorScene.getRootPane(), primaryStage.getMaxWidth(),
        primaryStage.getMaxHeight());
    setPrimaryStageToCurrScene();
  }

  @Override
  public void onGameEvent(GameEvent event) {
    String eventType = event.getGameEventType();
    if (eventType.equals("VIEW_LAUNCH_GAME_SCREEN")) {
      setMonopolyGamePlayScene();
    } else if (eventType.equals("VIEW_LAUNCH_GAME_EDITOR_SCREEN")) {
      setMonopolyGameEditorScene();
    }
  }

  private void setPrimaryStageToCurrScene() {
    primaryStage.setScene(currentScene);
  }
}
