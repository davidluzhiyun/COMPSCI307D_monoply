package ooga.view;

import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import ooga.Main;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventListener;
import ooga.event.command.Command;
import ooga.event.command.RollDiceCommand;
import ooga.view.components.Board;
import ooga.view.pop_ups.DiceRollPopUp;
import ooga.view.pop_ups.GamePiecePopUp;
import ooga.view.pop_ups.RentPopUp;
import ooga.view.pop_ups.RollResultPopUp;

public class GameView extends View implements GameEventListener {

  private final GameEventHandler gameEventHandler;
  private final Stage myStage;
  private String myStyle;
  private final String myLanguage;
  private DiceRollPopUp myDicePopUp;
  private double width;
  private double height;
  private final ResourceBundle myScreenResources;
  public static final String GAME_WIDTH_KEY = "GameWidth";
  public static final String GAME_HEIGHT_KEY = "GameHeight";
  public static final String GAME_OBJECTS_KEY = "GameObjects";
  public static final String GAME_BUTTONS_ID = "GameButtons";
  public static final String GAME_PIECE = "GamePiece";

  public GameView(GameEventHandler gameEventHandler, String style, String language, Stage stage) {
    this.myStyle = style;
    this.myLanguage = language;
    this.myStage = stage;
    this.gameEventHandler = gameEventHandler;
    myScreenResources = ResourceBundle.getBundle(Main.DEFAULT_RESOURCE_PACKAGE + StartView.SCREEN);
  }

  public Scene setUpScene(double width, double height) {
    this.width = width;
    this.height = height;
    Rectangle background = new Rectangle(width, height);
    background.setId(StartView.BACKGROUND);
    BorderPane myBorderPane = new BorderPane(background);
    myBorderPane.setTop(makeInteractiveObjects());
    myBorderPane.setCenter(new Board().getBoard());
    Scene myScene = new Scene(myBorderPane, width, height);
    styleScene(myScene, myStyle);
    myStage.setScene(myScene);
    myStage.show();
    return myScene;
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
    setUpScene(width, height);
  }

  /**
   * NOTE: Currently this is just pseudo-code because we do not have full support for this yet.
   * Theoretically, should present the GamePiecePopUp to each player, let them pick their piece,
   * then should add this piece to our Board class -- presumably there will be a method in Board
   * class that allows for a new piece to be initialized on the Go button
   */
  public void chooseGamePieces() {
    // make pop up to select number of players, then for each player, do that.
//     for (int i = 0; i < numPlayers; i ++) {
//       GamePiecePopUp pop = new GamePiecePopUp(i, myStyle, myLanguage);
//       pop.showMessage(myLanguage);
//       GamePiece piece = pop.getGamePiece();
//       myBoard.addPiece(piece, i);
//     }
  }

  /**
   * Set in property files to be the handler method when someone clicks the "Save game" button. This
   * should be implemented as one of our project extensions.
   * TODO: change this to actually implement the savegame feature.
   */
  public void saveGame() {
    GamePiecePopUp popUp = new GamePiecePopUp(1, myStyle, myLanguage);
    popUp.showMessage(myLanguage);
    RentPopUp pop = new RentPopUp(20);
    pop.showMessage(myLanguage);
    startPlayerTurn();
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
   * TODO: change to communicate directly to model -- view_to_model
   */
  public void rollDice() {
    Command cmd = new RollDiceCommand();
    GameEvent event = gameEventHandler.makeGameEventwithCommand("VIEW_TO_CONTROLLER_ROLL_DICE",
        cmd);
    gameEventHandler.publish(event);
    showDiceResult(new int[]{6,5});
  }

  /**
   * TODO: change this to actually get the dice result from the controller and show it.
   * may need to change to display the separate rolls of each die... can also have images for each!
   */
  private void showDiceResult(int[] roll) {
    myDicePopUp.close();
    RollResultPopUp pop = new RollResultPopUp(roll[0], roll[1]);
    pop.showMessage(myLanguage);
  }

  @Override
  public void onGameEvent(GameEvent event) {
    switch (event.getGameEventType()) {
      case "CONTROLLER_TO_VIEW_PLAYER_START" -> startPlayerTurn();
      case "CONTROLLER_TO_VIEW_ROLL_DICE" -> {
        Command cmd = event.getGameEventCommand().getCommand();
        showDiceResult((int[]) cmd.getCommandArgs());
      }
    }
  }
}
