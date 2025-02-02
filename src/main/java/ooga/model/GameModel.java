package ooga.model;

import java.awt.Point;

import com.google.gson.internal.LinkedTreeMap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.stream.Collectors;

import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventListener;
import ooga.event.GameEventType;
import ooga.event.command.ConcreteCommand;
import ooga.event.command.Command;
import ooga.model.component.ConcretePlayerTurn;
import ooga.model.exception.BadDataException;
import ooga.model.gamearchive.*;
import ooga.model.place.ControllerPlace;
import ooga.model.place.Place;
import ooga.model.player.ControllerPlayer;
import ooga.model.player.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Main class for the model. This class manages all the processes of the gameplay.
 * This class implements ModelOutput, and overrides onGameEvent(GameEvent event) method to handle the interaction with other classes.
 * This class implements ModelOutput, which is a limited access interface of the GameModel for the controller to select data from the model and update the view.
 */

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
  private GameConfig gameConfig;
  private GameSaver gameSaver;

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
    GameEvent event = GameEventHandler.makeGameEventwithCommand(GameEventType.MODEL_TO_CONTROLLER_UPDATE_DATA.name(), cmd1);
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
  protected void initializeGame(Map<String, LinkedTreeMap> initConfigJsonMap) {
    InitialConfigLoader initialConfigLoader = new InitialConfigLoader(initConfigJsonMap, modelResources, gameEventHandler);
    initialConfigLoader.check();

    places = initialConfigLoader.getPlaces();
    players = initialConfigLoader.getPlayers();

    gameConfig = initialConfigLoader.getGameConfig();
    gameSaver = new GameSaver(this, initConfigJsonMap);
    turn = new ConcretePlayerTurn(players, places, 0, gameEventHandler);
  }


  /**
   * "protected" is for test purpose
   *
   * @param map
   */
  protected void loadGame(Map<String, Object> map) {
    GameLoader gameLoader = new GameLoader(map, modelResources, gameEventHandler);
    places = gameLoader.getPlaces();
    players = gameLoader.getPlayers();
    gameLoader.setUpPlayersPropertiesAndPropertyOwner(players, places);
    Metadata metaData = gameLoader.getMetadata();
    turn = new ConcretePlayerTurn(players, places, metaData.currentPlayerId(), gameEventHandler);
    publishGameData(GameState.GAME_SET_UP);
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
    List<ControllerPlayer> result = players.stream().filter(Player::isAlive)
        .sorted(Comparator.comparing(Player::getPlayerId)).collect(Collectors.toList());
    return result;
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
    String pattern = "MODEL_TO_MODEL_(.+)";
    Pattern p = Pattern.compile(pattern);
    Matcher m = p.matcher(event.getGameEventType());
    if (m.find()) {
      GameState currrentGameState = GameState.valueOf(m.group(1));
      if (currrentGameState == GameState.PAY_RENT) {
        queryIndex = (int) event.getGameEventCommand().getCommand().getCommandArgs();
      }
      publishGameData(currrentGameState);
    } else {
      String patternToken = ".+_TO_MODEL_.+";
      boolean isModelEvent = Pattern.matches(patternToken, event.getGameEventType());
      if (isModelEvent) {
        eventTypeMap.get(event.getGameEventType()).accept(event);
      }
    }
  }

  private void setUpOnEventMap() {
    eventTypeMap.put(GameEventType.CONTROLLER_TO_VIEW_ROLL_DICE.name(), e -> {
      turn.roll();
      publishGameData(GameState.DICE_RESULT);
    });
    eventTypeMap.put(GameEventType.CONTROLLER_TO_MODEL_GAME_START.name(), this::startGame);
    eventTypeMap.put(GameEventType.VIEW_TO_MODEL_SAVE_GAME.name(), e -> {
      try {
        gameSaver.saveToJson();
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    eventTypeMap.put(GameEventType.CONTROLLER_TO_MODEL_LOAD_GAME.name(), e -> loadGame((Map<String, Object>) e.getGameEventCommand().getCommand().getCommandArgs()));
    eventTypeMap.put(GameEventType.CONTROLLER_TO_MODEL_CHECK_PLACE_ACTION.name(), this::sendPlaceActions);
    eventTypeMap.put(GameEventType.VIEW_TO_MODEL_PURCHASE_PROPERTY.name(), this::purchaseProperty);
    eventTypeMap.put(GameEventType.VIEW_TO_MODEL_GET_PLACE_ACTIONS.name(), this::sendPlaceActions);
    eventTypeMap.put(GameEventType.VIEW_TO_MODEL_ROLL_DICE.name(), e -> turn.roll());
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

  private void startGame(GameEvent event) throws BadDataException {
    Command cmd = event.getGameEventCommand().getCommand();
    initializeGame((Map) cmd.getCommandArgs());
    publishGameData(GameState.GAME_SET_UP);
  }
}
