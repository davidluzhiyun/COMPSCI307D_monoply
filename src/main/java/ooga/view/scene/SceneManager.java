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
  private String myLanguage;

  public SceneManager(Stage primaryStage, String language, GameEventHandler gameEventHandler) {
    this.primaryStage = primaryStage;
    this.gameEventHandler = gameEventHandler;
    this.myLanguage = language;
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

  public void setGameSelectionScene() {
    GameSelectionScene gameSelectionScene = new GameSelectionScene(myLanguage, primaryStage);
    currentScene = gameSelectionScene.createScene(primaryStage.getMaxWidth(), primaryStage.getMaxHeight());
    setPrimaryStageToCurrScene();
  }

  @Override
  public void onGameEvent(GameEvent event) {
    System.out.println(event.getGameEventType());
    if (event.getGameEventType().equals("VIEW_LAUNCH_GAME_SCREEN")) {
      setMonopolyGamePlayScene();
    }
    if (event.getGameEventType().equals("VIEW_LAUNCH_GAME_SELECTION_SCREEN")) {
      setGameSelectionScene();
    }

  }

  private void setPrimaryStageToCurrScene() {
    primaryStage.setScene(currentScene);
  }
}
