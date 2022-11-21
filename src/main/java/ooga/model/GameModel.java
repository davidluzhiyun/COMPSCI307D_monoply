package ooga.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import com.google.gson.internal.LinkedTreeMap;
import ooga.controller.InitBoardRecord;
import ooga.controller.ParsedProperty;
import ooga.controller.PlayerRecord;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventListener;
import ooga.event.command.Command;
import ooga.event.command.GameDataCommand;
import ooga.event.command.SampleCommand;
import ooga.model.components.ConcretePlayerTurn;
import ooga.model.place.Place;
import ooga.model.place.property.Property;
import ooga.view.SampleViewData;

import static ooga.model.place.AbstractPlace.PLACE_PACKAGE_NAME;

public class GameModel implements GameEventListener {
  private ConcretePlayerTurn turn;
  private List<Player> players;
  private List<Place> places;
  private GameEventHandler gameEventHandler;
  public static final String DEFAULT_RESOURCE_PACKAGE = "properties.";
  private ResourceBundle modelResources;

  public GameModel(GameEventHandler gameEventHandler) {
    this.gameEventHandler = gameEventHandler;
    modelResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "Model");
  }

  protected ResourceBundle getResources() {
    return modelResources;
  }

  public void publishDice() {
    Player currentPlayer = players.get(turn.getCurrentPlayerTurnId());
    turn.roll();
    SampleViewData d = null;//TODO
    Command cmd = new SampleCommand(d);
    GameEvent event = gameEventHandler.makeGameEventwithCommand("MODEL_TO_CONTROLLER_DICE_ROLLED", cmd);
    gameEventHandler.publish(event);
  }

  public void endTurn() {
    turn.nextTurn();
  }

  public void buyProperty(Property property) {
    Player currentPlayer = getCurrentPlayer();
    currentPlayer.purchase(property);
  }

  public void publishGameData() {
    ModelOutput gameData = null;
    Command cmd = new GameDataCommand(gameData);
    GameEvent event = gameEventHandler.makeGameEventwithCommand("MODEL_TO_CONTROLLER_GAME_DATA", cmd);
    gameEventHandler.publish(event);
  }


  public void publishCurrentPlayer() {
    Player currentPlayer = getCurrentPlayer();
    //TODO: publish this data
  }

  /**
   * Helper method to get the current player
   */
  private Player getCurrentPlayer() {
    return players.get(turn.getCurrentPlayerTurnId());
  }

  public void playersData() {
    Collection<ViewPlayer> playersData = new ArrayList<>(players);
    //TODO: publish this data
  }

  public void boardData() {
    List<Place> boardData = new ArrayList<>(places);
    //TODO: publish this data? I (David Lu) don't really know what this one should be
  }

  public void stationaryActions() {
    Player currentPlayer = getCurrentPlayer();
    Place currentPlace = places.get(currentPlayer.getCurrentPlaceId());
    Collection<StationaryAction> stationaryActions = currentPlace.getStationaryActions(currentPlayer);
    //TODO: publish this data (stationaryActions)
  }

  public void boardUpdateData() {
    ViewBoard boardData = new ViewBoardBuilder(new ArrayList<Place>(places), getCurrentPlayer());
    //TODO: publish this data
  }

  /**
   * For test purpose
   */
  protected void initializeGame(Map<String, LinkedTreeMap> map) {
    places = new ArrayList<>();
    int j = 1;
    while (map.containsKey(String.valueOf(j))) {
      places.add(createPlace((String) map.get(String.valueOf(j)).get("type"), (int) (double) map.get(String.valueOf(j)).get("id")));
      j++;
    }
    players = new ArrayList<>();
    for (int i = 0; i < (int) (double) map.get("meta").get("players"); i++)
      players.add(new ConcretePlayer(i));
//    turn = new ConcretePlayerTurn(players, places);
  }

  /**
   * For test purposes
   *
   * @param type
   */
  protected Place createPlace(String type, int id) {
    Place newPlace;
    Class<?> placeClass;
    try {
      placeClass = Class.forName(PLACE_PACKAGE_NAME + modelResources.getString(type));
    } catch (ClassNotFoundException e) {
      throw new IllegalStateException("classNotFound", e);
    }
    Constructor<?>[] makeNewPlace = placeClass.getConstructors();
    try {
      newPlace = (Place) makeNewPlace[0].newInstance(id);
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
      throw new RuntimeException(e);
    }
    return newPlace;
  }

  /**
   * For test purpose.
   *
   * @return
   */
  protected List<Player> getPlayers() {
    return players;
  }

  /**
   * For test purpose.
   *
   * @return
   */
  protected List<Place> getPlaces() {
    return places;
  }


  @Override
  public void onGameEvent(GameEvent event) {
    switch (event.getGameEventType()) {
      case "CONTROLLER_TO_MODEL_GAME_START" -> {
        Command cmd = event.getGameEventCommand().getCommand();
        initializeGame((Map) cmd.getCommandArgs());
        publishGameData();
      }
      case "CONTROLLER_TO_MODEL_ROLL_DICE" -> {
        Command cmd = event.getGameEventCommand().getCommand();
        publishGameData();
      }
      case "CONTROLLER_TO_MODEL_PURCHASE_PROPERTY" -> {
        Command cmd = event.getGameEventCommand().getCommand();
        buyProperty((Property) places.get((int) cmd.getCommandArgs()));
        publishGameData();
      }
      case "CONTROLLER_TO_MODEL_END_TURN" -> {
        Command cmd = event.getGameEventCommand().getCommand();
        endTurn();
        publishGameData();
      }
    }
  }
}
