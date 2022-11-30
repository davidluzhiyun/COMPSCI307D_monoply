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
import ooga.event.command.Command;
import ooga.event.command.RollDiceCommand;
import ooga.view.pop_ups.DiceRollPopUp;
import ooga.view.pop_ups.GamePiecePopUp;
import ooga.view.pop_ups.RentPopUp;
import ooga.view.pop_ups.RollResultPopUp;

public class GameView extends View implements GameEventListener {

  private final GameEventHandler gameEventHandler;
  private final Stage myStage;
  private Scene myScene;
  private String myStyle;
  private String myLanguage;
  private DiceRollPopUp myDicePopUp;
  private final ResourceBundle myScreenResources;
  public static final String GAME_WIDTH_KEY = "GameWidth";
  public static final String GAME_HEIGHT_KEY = "GameHeight";
  public static final String GAME_OBJECTS_KEY = "GameObjects";
  public static final String GAME_BUTTONS_ID = "GameButtons";
  public static final String GAME_PIECE = "GamePiece";
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
      box.getChildren().add((Node) makeInteractiveObject(name, myLanguage, this));
    }
    box.setId(GAME_BUTTONS_ID);
    return box;
  }

  public void changeStyle(Number newValue) {
    ResourceBundle choiceResources = ResourceBundle.getBundle(
        Main.DEFAULT_RESOURCE_PACKAGE + StartView.DROP_DOWN);
    myStyle = choiceResources.getString(
        String.format(StartView.STRING_INT_FORMATTER, StartView.STYLE, newValue));
    myStage.close();
    setUpScene();
  }

  /**
   * Set in property files to be the handler method when someone clicks the "Save game" button. This
   * should be implemented as one of our project extensions.
   * TODO: change this to actually implement the savegame feature.
   */
  public void saveGame() {
  }

  /**
   * Will later need to take in current player (int) parameter -- or use instance variable
   */
  private void startPlayerTurn() {
    myDicePopUp = new DiceRollPopUp(1, myStyle, myLanguage);
    myDicePopUp.showMessage(myLanguage);
    myDicePopUp.makeButtonActive(this);
  }

  /**
   * Set in property files to be called when the user clicks "Roll" within the RollDicePopUp
   */
  public void rollDice() {
    Command cmd = new RollDiceCommand();
    GameEvent event = gameEventHandler.makeGameEventwithCommand("VIEW_TO_CONTROLLER_ROLL_DICE",
        cmd);
    gameEventHandler.publish(event);
  }

  /**
   * TODO: change this to actually get the dice result from the controller and show it.
   */
  private void showDiceResult(int roll) {
    myDicePopUp.close();
    RollResultPopUp pop = new RollResultPopUp(roll);
    pop.showMessage(myLanguage);
  }

  @Override
  public void onGameEvent(GameEvent event) {
    switch (event.getGameEventType()) {
      case "CONTROLLER_TO_VIEW_PLAYER_START" -> startPlayerTurn();
      case "CONTROLLER_TO_VIEW_ROLL_DICE" -> {
        Command cmd = event.getGameEventCommand().getCommand();
        showDiceResult((int) cmd.getCommandArgs());
      }
    }
  }
}
