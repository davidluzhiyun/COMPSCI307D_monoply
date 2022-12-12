package ooga.model;

import java.awt.Point;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventListener;
import ooga.event.GameEventType;
import ooga.event.command.ConcreteCommand;
import ooga.event.command.Command;
import ooga.event.command.GameDataCommand;
import ooga.model.colorSet.ConcreteColorSet;
import ooga.model.component.ConcretePlayerTurn;
import ooga.model.exception.BadDataException;
import ooga.model.exception.MonopolyException;
import ooga.model.gamearchive.GameConfig;
import ooga.model.gamearchive.GameLoader;
import ooga.model.gamearchive.InitialConfigLoader;
import ooga.model.gamearchive.Metadata;
import ooga.model.place.ControllerPlace;
import ooga.model.place.Place;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static ooga.model.place.AbstractPlace.PLACE_PACKAGE_NAME;

public class GameModel implements GameEventListener, ModelOutput {
  private ConcretePlayerTurn turn;
  private List<Player> players;
  private List<Place> places;
  private final GameEventHandler gameEventHandler;
  public static final String DEFAULT_RESOURCE_PACKAGE = "properties.";
  private final ResourceBundle modelResources;
  private static final Logger LOG = LogManager.getLogger(GameModel.class);
  private GameState gameState;
  private int queryIndex;
  private final Map<String, Consumer<GameEvent>> eventTypeMap = new HashMap<>();

  public GameModel(GameEventHandler gameEventHandler) {
    this.gameEventHandler = gameEventHandler;
    modelResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "Model");
    setUpOnEventMap();
  }

  protected ResourceBundle getResources() {
    return modelResources;
  }


  private void endTurn() {
    turn.nextTurn();
  }

  private void buyProperty(int propertyIndex) {
    Player currentPlayer = getCurrentPlayerHelper();
    currentPlayer.purchase(places.get(propertyIndex), propertyIndex);
  }

  private void publishGameData(GameState gameState) {
    this.gameState = gameState;
    Player currentPlayer = getCurrentPlayerHelper();
    for (Place place : places) {
      place.updateCurrentPlayerPlaceActions(currentPlayer);
    }
    ModelOutput gameData = this;
    Command<ModelOutput> cmd1 = new ConcreteCommand<>(gameData);
    GameEvent event = GameEventHandler.makeGameEventwithCommand(GameEventType.MODEL_TO_CONTROLLER_GAME_DATA.name(), cmd1);
    gameEventHandler.publish(event);
  }


  /**
   * Helper method to get the current player
   */
  private Player getCurrentPlayerHelper() {
    return players.get(turn.getCurrentPlayerTurnId());
  }


  /**
   * Initialize the game using data from controller
   * "protected" is for test purpose
   */
  protected void initializeGame(Map<String, LinkedTreeMap> map) {
    InitialConfigLoader initialConfigLoader = new InitialConfigLoader(map, modelResources);
    try {
      initialConfigLoader.check();
    } catch (MonopolyException e) {
      Command<MonopolyException> command = new ConcreteCommand<>(e);
      GameEvent event = GameEventHandler.makeGameEventwithCommand(GameEventType.MODEL_TO_VIEW_EXCEPTION.name(), command);
      gameEventHandler.publish(event);
    }
    places = initialConfigLoader.getPlaces();
    players = initialConfigLoader.getPlayers();
    GameConfig gameConfig;
    try {
      gameConfig = initialConfigLoader.getGameConfig();
    }
    catch (BadDataException e){
      Command<MonopolyException> command = new ConcreteCommand<>(e);
      GameEvent event = GameEventHandler.makeGameEventwithCommand(GameEventType.MODEL_TO_VIEW_EXCEPTION.name(), command);
      LOG.warn(e);
      gameEventHandler.publish(event);
    }
    turn = new ConcretePlayerTurn(players, places, 0);
  }


  /**
   * "protected" is for test purpose
   *
   * @param map
   */
  protected void loadGame(Map<String, Object> map) {
    GameLoader gameLoader = new GameLoader(map, modelResources);
    places = gameLoader.loadPlaceData();
    players = gameLoader.loadPlayerData();
    gameLoader.setUpPlayersPropertiesAndPropertyOwner(players, places);
    Metadata metaData = gameLoader.getMetadata();
    turn = new ConcretePlayerTurn(players, places, metaData.currentPlayerId());//TODO: set current player
    publishGameData(GameState.LOAD_BOARD);
  }

  // beginning of ModelOutput methods
  @Override
  public GameState getGameState() {
    return gameState;
  }

  @Override
  public Point getDiceNum() {
    return turn.getDiceNum();
  }

  @Override
  public int getCurrentPlayerId() {
    return turn.getCurrentPlayerTurnId();
  }

  @Override
  public List<ControllerPlayer> getPlayers() {
    return new ArrayList<>(players);
  }

  @Override
  public List<ControllerPlace> getBoard() {
    return new ArrayList<>(places);
  }

  @Override
  public Collection<StationaryAction> getStationaryAction() {
    Player currentPlayer = getCurrentPlayerHelper();
    Place currentPlace = places.get(currentPlayer.getCurrentPlaceIndex());
    return currentPlace.getStationaryActions(currentPlayer);
  }

  @Override
  public int getQueryIndex() {
    return queryIndex;
  }

  //end of ModelOutput methods

  @Override
  public void onGameEvent(GameEvent event) {
    System.out.println("in game model");
    System.out.println(event);
    Pattern pattern = Pattern.compile("MODEL_TO_MODEL_(.+)");
    Matcher matcher = pattern.matcher(event.getGameEventType());
    if (matcher.find())
      publishGameData(GameState.valueOf(matcher.group(1)));
    //Inside model

    //TODO: Refactor the switch expression
    String patternToken = ".+_TO_MODEL_.+";
    boolean isModelEvent = Pattern.matches(patternToken, event.getGameEventType());
    if (isModelEvent) {
      eventTypeMap.get(event.getGameEventType()).accept(event);
    }
  }

  private void setUpOnEventMap() {
    eventTypeMap.put(GameEventType.CONTROLLER_TO_VIEW_ROLL_DICE.name(), e -> {
      turn.roll();
      publishGameData(GameState.DICE_RESULT);
    });
    eventTypeMap.put(GameEventType.CONTROLLER_TO_MODEL_GAME_START.name(), this::startGame);
    eventTypeMap.put(GameEventType.VIEW_TO_MODEL_PURCHASE_PROPERTY.name(), this::purchaseProperty);
    eventTypeMap.put(GameEventType.CONTROLLER_TO_MODEL_CHECK_PLACE_ACTION.name(), this::sendPlaceActions);
    eventTypeMap.put(GameEventType.VIEW_TO_MODEL_END_TURN.name(), e -> {
      endTurn();
      publishGameData(GameState.NEXT_PLAYER);
    });
  }

  private void sendPlaceActions(GameEvent event) {
    Command cmd = event.getGameEventCommand().getCommand();
    queryIndex = (int) cmd.getCommandArgs();
    publishGameData(GameState.GET_PLACE_ACTIONS);
  }

  private void purchaseProperty(GameEvent event) {
    Command cmd = event.getGameEventCommand().getCommand();
    int propertyIndex = (int) cmd.getCommandArgs();
    buyProperty(propertyIndex);
    publishGameData(GameState.BUY_PROPERTY);
  }

  private void startGame(GameEvent event) {
    Command cmd = event.getGameEventCommand().getCommand();
    initializeGame((Map) cmd.getCommandArgs());
    publishGameData(GameState.GAME_SET_UP);
  }
}
