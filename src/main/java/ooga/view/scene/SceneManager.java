package ooga.view.scene;

import java.util.ResourceBundle;
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

//  private final ResourceBundle resources = ResourceBundle.getBundle(DEFAULT_RESOURCE_DIR);

  private final ResourceBundle languageResources;

  public SceneManager(Stage primaryStage, String language, GameEventHandler gameEventHandler) {
    this.primaryStage = primaryStage;
    this.gameEventHandler = gameEventHandler;
    languageResources = ResourceBundle.getBundle(BASE_DIR_UI_LANGUAGE + language);
    setupStage();
  }

  private void setupStage() {
    primaryStage.setTitle(languageResources.getString("Title"));
    primaryStage.show();
  }

  @Override
  public void onGameEvent(GameEvent event) {

  }
}
