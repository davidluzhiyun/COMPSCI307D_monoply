package ooga.view;

import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import ooga.Main;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventListener;

public class GameView extends View implements GameEventListener {

  private GameEventHandler gameEventHandler;
  private Stage myStage;
  private Scene myScene;
  private String myStyle;
  private String myLanguage;
  private final ResourceBundle myScreenResources;
  public static final String GAME_WIDTH_KEY = "GameWidth";
  public static final String GAME_HEIGHT_KEY = "GameHeight";
  private BorderPane myBorderPane;
  private final ResourceBundle myLanguageResources;

  public GameView(GameEventHandler gameEventHandler, String style, String language) {
    this.myStage = new Stage();
    this.myStyle = style;
    this.myLanguage = language;
    this.gameEventHandler = gameEventHandler;
    myScreenResources = ResourceBundle.getBundle(Main.DEFAULT_RESOURCE_PACKAGE + StartView.SCREEN);
    myLanguageResources = ResourceBundle.getBundle(Main.DEFAULT_LANGUAGE_PACKAGE + myLanguage);
    int width = Integer.parseInt(myScreenResources.getString(GAME_WIDTH_KEY));
    int height = Integer.parseInt(myScreenResources.getString(GAME_HEIGHT_KEY));
    Rectangle background = new Rectangle(width, height);
    background.setId(StartView.BACKGROUND);
    myBorderPane = new BorderPane(background);
    myScene = new Scene(myBorderPane, width, height);
    styleScene(myScene, myStyle);
    myStage.setScene(myScene);
    myStage.show();
  }

  @Override
  public InteractiveObject makeInteractiveObject(String name) {
    return null;
  }

  @Override
  public void onGameEvent(GameEvent event) {

  }
}
