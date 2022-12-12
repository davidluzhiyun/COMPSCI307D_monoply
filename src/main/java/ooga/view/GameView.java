package ooga.view;

import java.awt.Point;
import java.util.List;
import java.io.File;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import ooga.Main;
import ooga.controller.LoadBoardRecord;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventListener;
import ooga.event.GameEventType;
import ooga.event.command.Command;
import ooga.event.command.RollDiceCommand;
import ooga.model.PlaceAction;
import ooga.view.components.Board;
import ooga.view.components.GamePiece;
import ooga.view.pop_ups.AvailablePlaceActionsPopUp;
import ooga.event.command.SelectBoardEditConfigCommand;
import ooga.view.components.Board;
import ooga.view.components.GamePiece;
import ooga.view.components.MonopolyBoardBuilder;
import ooga.view.components.MonopolyBoardInteractor;
import ooga.view.components.MonopolyBoardViewModel;
import ooga.view.pop_ups.BuyHousePopUp;
import ooga.view.pop_ups.DiceRollPopUp;
import ooga.view.pop_ups.GamePiecePopUp;
import ooga.view.pop_ups.RentPopUp;
import ooga.view.pop_ups.RollResultPopUp;

public class GameView extends View implements GameEventListener {

  private GameEventHandler gameEventHandler;
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
  private MonopolyBoardViewModel model;
  private Region myBoard;
  private MonopolyBoardBuilder monopolyBoardBuilder;
  private MonopolyBoardInteractor interactor;
  // TODO: get this instead from controller
  private int numPlayers;

  public GameView(GameEventHandler gameEventHandler, String language, Stage stage) {
    this.myLanguage = language;
    this.myStage = stage;
    this.gameEventHandler = gameEventHandler;
    //TODO: Change this
    this.numPlayers = 4;
    myScreenResources = ResourceBundle.getBundle(Main.DEFAULT_RESOURCE_PACKAGE + StartView.SCREEN);

    this.gameEventHandler = gameEventHandler;
    gameEventHandler.addEventListener(this);
  }

  public Scene setUpScene(double width, double height, String style) {
    this.width = width;
    this.height = height;
    this.myStyle = style;
    Rectangle background = new Rectangle(width, height);
    background.setId(StartView.BACKGROUND);
    BorderPane myBorderPane = new BorderPane(background);
    myBorderPane.setTop(makeInteractiveObjects());

    // board setup
    setupBoard();
    myBorderPane.setCenter(myBoard);

    Scene myScene = new Scene(myBorderPane, width, height);
    styleScene(myScene, myStyle);
    myStage.setScene(myScene);
    myStage.show();
    chooseGamePieces();
    return myScene;
  }

  private void getInitBoardData() {
    String test = "ooga/model/place/InitialConfig.json";
    GameEvent gameStart = GameEventHandler.makeGameEventwithCommand(
        GameEventType.VIEW_TO_CONTROLLER_LOAD_BOARD.name(),
        new SelectBoardEditConfigCommand(
            new File(this.getClass().getClassLoader().getResource(test).getFile())));
    gameEventHandler.publish(gameStart);
  }

  private void setupBoard() {
    model = new MonopolyBoardViewModel();
    interactor = new MonopolyBoardInteractor(model);
    getInitBoardData();
    monopolyBoardBuilder = new MonopolyBoardBuilder(model, gameEventHandler);
    myBoard = monopolyBoardBuilder.build();
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
    setUpScene(width, height, myStyle);
  }

  /**
   * Presents the GamePiecePopUp to each player to let them pick their piece.
   */
  public void chooseGamePieces() {
    for (int i = numPlayers; i > 0; i--) {
//      GamePiecePopUp pop = new GamePiecePopUp(i, myStyle, myBoard);
//      pop.showMessage(myLanguage);
    }
  }

  /**
   * Set in property files to be the handler method when someone clicks the "Save game" button. This
   * should be implemented as one of our project extensions.
   * TODO: change this to actually implement the savegame feature.
   */
  public void saveGame() {
//    GamePiecePopUp popUp = new GamePiecePopUp(1, myStyle, myBoard);
//    popUp.showMessage(myLanguage);
    RentPopUp pop = new RentPopUp(20);
    pop.showMessage(myLanguage);
    startPlayerTurn();
  }

  /**
   * Will later need to take in current player (int) parameter -- or use instance variable
   */
  private void startPlayerTurn() {
    myDicePopUp = new DiceRollPopUp(1, myStyle);
    myDicePopUp.showMessage(myLanguage);
    myDicePopUp.makeButtonActive(this);
  }

  /**
   * Set in property files to be called when the user clicks "Roll" within the RollDicePopUp
   */
  public void rollDice() {
    Command cmd = new RollDiceCommand();
    GameEvent event = GameEventHandler.makeGameEventwithCommand("VIEW_TO_MODEL_ROLL_DICE",
        cmd);
    gameEventHandler.publish(event);
//    showDiceResult(new int[]{6, 5});
  }

  /**
   * TODO: change this to actually get the dice result from the controller and show it.
   * may need to change to display the separate rolls of each die... can also have images for each!
   */
  private void showDiceResult(Point roll) {
    myDicePopUp.close();
    RollResultPopUp pop = new RollResultPopUp(roll.x, roll.y);
    pop.showMessage(myLanguage);
  }

  public void buyHouse() {
//    BuyHousePopUp pop = new BuyHousePopUp(1, myStyle, myBoard);
//    pop.showMessage(myLanguage);
  }

  public void endTurn() {
  }

  @Override
  public void onGameEvent(GameEvent event) {
    switch (event.getGameEventType()) {
      case "CONTROLLER_TO_VIEW_PLAYER_START" -> startPlayerTurn();
      case "CONTROLLER_TO_VIEW_ROLL_DICE" -> {
        Command cmd = event.getGameEventCommand().getCommand();
        showDiceResult((Point) cmd.getCommandArgs());
      }
      case "CONTROLLER_TO_VIEW_GET_PLACE_ACTIONS" -> {
        Command cmd = event.getGameEventCommand().getCommand();
        AvailablePlaceActionsPopUp pop = new AvailablePlaceActionsPopUp(cmd.getCommandArgs(), myStyle);
        pop.showMessage(myLanguage);
      }
    }
    if (event.getGameEventType().equals("CONTROLLER_TO_VIEW_LOAD_BOARD")) {
      LoadBoardRecord command = (LoadBoardRecord) event.getGameEventCommand().getCommand()
          .getCommandArgs();
      interactor.initialize(command);
    }
    if (event.getGameEventType().equals("VIEW_POST_ACTION_DRAW_BOARD")) {
      monopolyBoardBuilder.drawPostProcessing();
    }
  }
}
