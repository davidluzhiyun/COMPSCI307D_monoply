package ooga.view;

import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import ooga.Main;
import ooga.Reflection;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventListener;
import ooga.view.pop_ups.DiceRollPopUp;
import ooga.view.pop_ups.RentPopUp;

public class GameView extends View implements GameEventListener {

  private GameEventHandler gameEventHandler;
  private Stage myStage;
  private Scene myScene;
  private String myStyle;
  private String myLanguage;
  private DiceRollPopUp myDicePopUp;
  private final ResourceBundle myScreenResources;
  public static final String GAME_WIDTH_KEY = "GameWidth";
  public static final String GAME_HEIGHT_KEY = "GameHeight";
  public static final String GAME_OBJECTS_KEY = "GameObjects";
  public static final String GAME_BUTTONS_ID = "GameButtons";
  private BorderPane myBorderPane;
  private final ResourceBundle myLanguageResources;

  public GameView(GameEventHandler gameEventHandler, String style, String language) {
    this.myStage = new Stage();
    this.myStyle = style;
    this.myLanguage = language;
    this.gameEventHandler = gameEventHandler;
    myScreenResources = ResourceBundle.getBundle(Main.DEFAULT_RESOURCE_PACKAGE + StartView.SCREEN);
    myLanguageResources = ResourceBundle.getBundle(Main.DEFAULT_LANGUAGE_PACKAGE + myLanguage);
    setUpScene();
  }

  private void setUpScene() {
    int width = Integer.parseInt(myScreenResources.getString(GAME_WIDTH_KEY));
    int height = Integer.parseInt(myScreenResources.getString(GAME_HEIGHT_KEY));
    Rectangle background = new Rectangle(width, height);
    background.setId(StartView.BACKGROUND);
    myBorderPane = new BorderPane(background);
    myBorderPane.setTop(makeInteractiveObjects());
    myScene = new Scene(myBorderPane, width, height);
    styleScene(myScene, myStyle);
    myStage.setScene(myScene);
    myStage.show();
  }

  private HBox makeInteractiveObjects() {
    HBox box = new HBox();
    String[] names = myScreenResources.getString(GAME_OBJECTS_KEY).split(StartView.SPACE_REGEX);
    for (String name : names) {
      box.getChildren().add((Node) makeInteractiveObject(name));
    }
    box.setId(GAME_BUTTONS_ID);
    return box;
  }

  @Override
  public InteractiveObject makeInteractiveObject(String name) {
    Reflection reflection = new Reflection();
    ResourceBundle resources = ResourceBundle.getBundle(View.BUTTON_PROPERTIES);
    String className = resources.getString(name);
    InteractiveObject object = (InteractiveObject) reflection.makeObject(className,
        new Class[]{String.class},
        new Object[]{myLanguage});
    String method = resources.getString(
        String.format(StartView.STRING_FORMATTER, name, StartView.METHOD));
    if (name.contains(StartView.DROP_DOWN)) {
      object.setAction(reflection.makeMethod(method, GameView.class, new Class[]{Number.class}),
          this);
    } else {
      object.setAction(reflection.makeMethod(method, GameView.class, null), this);
    }
    return object;
  }

  @Override
  public void onGameEvent(GameEvent event) {

  }

  @Override
  public void changeStyle(Number newValue) {
    ResourceBundle choiceResources = ResourceBundle.getBundle(
        Main.DEFAULT_RESOURCE_PACKAGE + StartView.DROP_DOWN);
    myStyle = choiceResources.getString(String.format(StartView.STRING_INT_FORMATTER, StartView.STYLE, newValue));
    myStage.close();
    setUpScene();
  }

  /**
   * Set in property files to be the handler method when someone clicks the "Save game" button. This
   * should be implemented as one of our project extensions.
   * TODO: change this to actually implement the savegame feature. currently this is just showing
   * the pop ups.
   */
  public void saveGame() {
    RentPopUp pop = new RentPopUp();
    pop.showMessage(myLanguage);
    startPlayerTurn();
  }

  private void startPlayerTurn() {
    myDicePopUp = new DiceRollPopUp(1);
    myDicePopUp.showMessage(myLanguage);
    myDicePopUp.makeButtonActive(this);
  }

  /**
   * TODO: use this to send event to controller. also make sure the result of the dice roll is then displayed.
   */
  public void rollDice() {
    myDicePopUp.close();
    System.out.println("does this work... please dear god");
  }
}
