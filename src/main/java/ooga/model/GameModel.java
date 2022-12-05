package ooga.model;

import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventListener;
import ooga.event.command.Command;
import ooga.event.command.GameDataCommand;
import ooga.model.components.ConcretePlayerTurn;
import ooga.model.gamesaver.Metadata;
import ooga.model.gamesaver.PlaceSaver;
import ooga.model.gamesaver.PlayerSaver;
import ooga.model.place.ControllerPlace;
import ooga.model.place.Place;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static ooga.model.place.AbstractPlace.DEFAULT_RESOURCE_FOLDER;
import static ooga.model.place.AbstractPlace.PLACE_PACKAGE_NAME;

public class GameModel implements GameEventListener, ModelOutput {
  private ConcretePlayerTurn turn;
  private List<Player> players;
  private List<Place> places;
  private GameEventHandler gameEventHandler;
  public static final String DEFAULT_RESOURCE_PACKAGE = "properties.";
  private ResourceBundle modelResources;
  private static final Logger LOG = LogManager.getLogger(GameModel.class);

  public GameModel(GameEventHandler gameEventHandler) {
    this.gameEventHandler = gameEventHandler;
    modelResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "Model");
  }

  protected ResourceBundle getResources() {
    return modelResources;
  }

  public void rollDice() {
    turn.roll();
  }

  private void endTurn() {
    turn.nextTurn();
  }

  private void buyProperty(int propertyIndex) {
    Player currentPlayer = getCurrentPlayerHelper();
    currentPlayer.purchase(places.get(propertyIndex), propertyIndex);
  }

  private void publishGameData() {
    Player currentPlayer = getCurrentPlayerHelper();
    for (Place place : places) {
      place.updatePlaceActions(currentPlayer);
    }
    ModelOutput gameData = this;
    Command cmd = new GameDataCommand(gameData);
    GameEvent event = gameEventHandler.makeGameEventwithCommand("MODEL_TO_CONTROLLER_GAME_DATA", cmd);
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
    places = new ArrayList<>();
    int j = 1;
    while (map.containsKey(String.valueOf(j))) {
      places.add(createPlace((String) map.get(String.valueOf(j)).get("type"), (String) map.get(String.valueOf(j)).get("id")));
      j++;
    }
    players = new ArrayList<>();
    for (int i = 0; i < (int) (double) map.get("meta").get("players"); i++)
      players.add(new ConcretePlayer(i));
    turn = new ConcretePlayerTurn(players, places);
  }

  /**
   * "protected" is for test purpose
   *
   * @param map
   */
  protected void loadGame(Map<String, Object> map) {
    players = new ArrayList<>();
    places = new ArrayList<>();
    loadPlaceData(map);
    loadPlayerData(map);
    Metadata metaData = (Metadata) map.get("meta");
    turn = new ConcretePlayerTurn(players, places);//TODO: set current player
  }

  private void loadPlayerData(Map<String, Object> map) {
    List<PlayerSaver> playersData = (List<PlayerSaver>) map.get("players");
    for (PlayerSaver singlePlayersData : playersData) {
      int playerId = singlePlayersData.id();
      Player newPlayer = new ConcretePlayer(playerId);
      newPlayer.setMoney(1500);//TODO: use properties file
      newPlayer.setJail(singlePlayersData.jail());
      newPlayer.setIndex(singlePlayersData.currentPlaceIndex());
      newPlayer.setProperties(singlePlayersData.properties());
      players.add(newPlayer);
    }
  }

  private void loadPlaceData(Map<String, Object> map) {
    List<PlaceSaver> placesData = (List<PlaceSaver>) map.get("places");
    for (PlaceSaver singlePlaceData : placesData) {
      String placeId = singlePlaceData.id();
      Gson gson = new Gson();
      Reader reader;
      Map<String, ?> config;
      try {
        File file = new File("." + "/src/main/resources" + DEFAULT_RESOURCE_FOLDER + placeId + ".json");
        reader = new FileReader(file);
        TypeToken<Map<String, ?>> mapType = new TypeToken<>() {
        };
        config = gson.fromJson(reader, mapType);
        String type = (String) config.get("type");
        Place newPlace = createPlace(type, placeId);
        if (singlePlaceData.owner() != null && singlePlaceData.owner() != -1) //if the place can be purchased and there is someone who purchased it
          newPlace.setOwner(singlePlaceData.owner());
        if (singlePlaceData.houseCount() != null)
          newPlace.setHouseCount(singlePlaceData.houseCount());
        places.add(newPlace);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  /**
   * "protected" is for test purpose
   *
   * @param type
   */
  protected Place createPlace(String type, String id) {
    Place newPlace;
    Class<?> placeClass;
    try {
      placeClass = Class.forName(PLACE_PACKAGE_NAME + modelResources.getString(type));
    } catch (ClassNotFoundException e) {
      LOG.warn(e);
      throw new IllegalStateException("classNotFound", e);
    }
    Constructor<?>[] makeNewPlace = placeClass.getConstructors();
    try {
      newPlace = (Place) makeNewPlace[0].newInstance(id);
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
      LOG.warn(e);
      throw new RuntimeException(e);
    }
    return newPlace;
  }
  // beginning of ModelOutput methods

  @Override
  public Point getDiceNum() {
    return turn.getDiceNum();
  }

  @Override
  public int getCurrentPlayer() {
    return turn.getCurrentPlayerTurnId();
  }

  @Override
  public List<ControllerPlayer> getPlayers() {
    List<ControllerPlayer> playersData = new ArrayList<>(players);
    return playersData;
  }

  @Override
  public List<ControllerPlace> getBoard() {
    List<ControllerPlace> board = new ArrayList<>(places);
    return board;
  }

  @Override
  public Collection<StationaryAction> getStationaryAction() {
    Player currentPlayer = getCurrentPlayerHelper();
    Place currentPlace = places.get(currentPlayer.getCurrentPlaceIndex());
    Collection<StationaryAction> stationaryActions = currentPlace.getStationaryActions(currentPlayer);
    return stationaryActions;
  }

  //end of ModelOutput methods

  @Override
  public void onGameEvent(GameEvent event) {
    //TODO: Refactor the switch expression
    switch (event.getGameEventType()) {
      case "CONTROLLER_TO_MODEL_GAME_START" -> {
        Command cmd = event.getGameEventCommand().getCommand();
        initializeGame((Map) cmd.getCommandArgs());
        publishGameData();
      }
      case "CONTROLLER_TO_MODEL_ROLL_DICE" -> {
        Command cmd = event.getGameEventCommand().getCommand();
        rollDice();
        publishGameData();
      }
      case "CONTROLLER_TO_MODEL_PURCHASE_PROPERTY" -> {
        Command cmd = event.getGameEventCommand().getCommand();
        int propertyIndex = (int) cmd.getCommandArgs();
        buyProperty(propertyIndex);
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
