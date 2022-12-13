package ooga.view;

import java.awt.Point;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import ooga.Main;
import ooga.Reflection;
import ooga.controller.GetPlaceActionsRecord;
import ooga.controller.InitBoardRecord;
import ooga.controller.LoadBoardRecord;
import ooga.controller.MoveRecord;
import ooga.controller.PlaceActionRecord;
import ooga.controller.UpdateViewRecord;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventListener;
import ooga.event.GameEventType;
import ooga.event.command.Command;
import ooga.event.command.EndTurnCommand;
import ooga.event.command.GetPlayerCommand;
import ooga.event.command.RollDiceCommand;
import ooga.model.GameState;
import ooga.model.StationaryAction;
import ooga.view.pop_ups.AvailablePlaceActionsPopUp;
import ooga.event.command.SelectBoardEditConfigCommand;
import ooga.view.components.MonopolyBoardBuilder;
import ooga.view.components.MonopolyBoardInteractor;
import ooga.view.components.MonopolyBoardViewModel;
import ooga.view.pop_ups.BuyHousePopUp;
import ooga.view.pop_ups.BuyPropertyPopUp;
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
  private final ResourceBundle myScreenResources;
  private final Map<String, Consumer<GameEvent>> eventTypeMap = new HashMap<>();
  public static final String GAME_OBJECTS_KEY = "GameObjects";
  public static final String GAME_BUTTONS_ID = "GameButtons";
  public static final String GAME_PIECE = "GamePiece";
  private MonopolyBoardViewModel model;
  private Region myBoard;
  private MonopolyBoardBuilder monopolyBoardBuilder;
  private MonopolyBoardInteractor interactor;
  // TODO: get this instead from controller
  private final int numPlayers;
  private int currentPlayer;

  public GameView(GameEventHandler gameEventHandler, String language, Stage stage) {
    this.myLanguage = language;
    this.myStage = stage;
    this.gameEventHandler = gameEventHandler;
    //TODO: Change this to actually get the number of players
    this.numPlayers = 4;
    myScreenResources = ResourceBundle.getBundle(Main.DEFAULT_RESOURCE_PACKAGE + StartView.SCREEN);

    this.gameEventHandler = gameEventHandler;
    gameEventHandler.addEventListener(this);
  }

  public Scene setUpScene(double width, double height, String style) {
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
    return myScene;
  }

  /**
   * TODO: use the uploaded config file
   */
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

  /**
   * Presents the GamePiecePopUp to each player to let them pick their piece.
   */
  public void chooseGamePieces() {
    for (int i = numPlayers; i > 0; i--) {
      GamePiecePopUp pop = new GamePiecePopUp(i, myStyle, monopolyBoardBuilder);
      pop.showMessage(myLanguage);
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
  }
  public void endTurn() {
    Command command = new EndTurnCommand();
    GameEvent event = GameEventHandler.makeGameEventwithCommand(GameEventType.VIEW_TO_MODEL_END_TURN.name(), command);
    gameEventHandler.publish(event);
  }

  /**
   * Takes in the current player index, must increment this for display of players 1-4 rather than
   * 0-3. Displays pop up for user to roll the dice and start their turn.
   */
  private void startPlayerTurn(GameEvent event) {
    Command cmd = event.getGameEventCommand().getCommand();
    int player = (int) cmd.getCommandArgs();
    this.currentPlayer = player+1;
    myDicePopUp = new DiceRollPopUp(player+1, myStyle);
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
  }

  /**
   * Displays a pop up with the result of the dice roll.
   */
  private void showDiceResult(GameEvent event) {
    Command cmd = event.getGameEventCommand().getCommand();
    Point roll = (Point) cmd.getCommandArgs();
    myDicePopUp.close();
    RollResultPopUp pop = new RollResultPopUp(roll.x, roll.y);
    pop.showMessage(myLanguage);
  }

  public void showPlaceActions(GameEvent event) {
    GetPlaceActionsRecord cmd = (GetPlaceActionsRecord) event.getGameEventCommand().getCommand().getCommandArgs();
    AvailablePlaceActionsPopUp pop = new AvailablePlaceActionsPopUp(cmd, myStyle, gameEventHandler);
    pop.showMessage(myLanguage);
  }
  public void loadBoard(GameEvent event) {
    LoadBoardRecord command = (LoadBoardRecord) event.getGameEventCommand().getCommand()
            .getCommandArgs();
    interactor.initialize(command);
  }
  public void drawBoard(GameEvent event) {monopolyBoardBuilder.drawPostProcessing();}

  public void startGame(GameEvent event){
    InitBoardRecord command = (InitBoardRecord) event.getGameEventCommand().getCommand().getCommandArgs();
    interactor.initializeNewBoard(command);
    this.currentPlayer = command.currentPlayerId();
    myDicePopUp = new DiceRollPopUp(currentPlayer+1, myStyle);
    myDicePopUp.showMessage(myLanguage);
    myDicePopUp.makeButtonActive(this);
    chooseGamePieces();
  }
  public void movePlayer(GameEvent event) {
    MoveRecord cmd = (MoveRecord) event.getGameEventCommand().getCommand().getCommandArgs();
    System.out.println(cmd.placeIndex());
    monopolyBoardBuilder.movePlayer(cmd.placeIndex(), currentPlayer);
    System.out.println(cmd.actions());
    if(cmd.actions().contains(StationaryAction.BUY_PROPERTY)) {
      BuyPropertyPopUp pop = new BuyPropertyPopUp(myStyle, cmd.placeIndex(), gameEventHandler);
      pop.showMessage(myLanguage);
    }
    // use this to generate correct pop up
  }

  public void buyProperty(GameEvent event) {
    UpdateViewRecord command = (UpdateViewRecord) event.getGameEventCommand().getCommand().getCommandArgs();
    BuyPropertyPopUp pop = new BuyPropertyPopUp(myStyle, (int) command.placeIndex(), gameEventHandler);
    pop.showMessage(myLanguage);
  }
  public void viewPlaceInfo(GameEvent event) {

  }
  @Override
  public void onGameEvent(GameEvent event) {
    String patternToken = ".+_TO_VIEW_.+";
    boolean isViewEvent = Pattern.matches(patternToken, event.getGameEventType());
    String pattern = GameEventType.VIEW_POST_ACTION_DRAW_BOARD.name();
    if (isViewEvent | Pattern.matches(pattern, event.getGameEventType())) {
      Reflection reflect = new Reflection();
      ResourceBundle reflectResources = ResourceBundle.getBundle(Main.DEFAULT_RESOURCE_PACKAGE + "GameViewReflection");
      String method = reflectResources.getString(event.getGameEventType());
      try {
        reflect.makeMethod(method, this.getClass(), new Class[]{GameEvent.class}).invoke(this, event);
      } catch (IllegalAccessException | InvocationTargetException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
