package ooga.model;

import java.awt.Point;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

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

public class ConcreteModel implements GameEventListener, ModelOutput {
  private ConcretePlayerTurn turn;
  private List<Player> players;
  private List<Place> places;
  private GameEventHandler gameEventHandler;
  public static final String DEFAULT_RESOURCE_PACKAGE = "properties.";
  private ResourceBundle modelResources;

  public ConcreteModel(GameEventHandler gameEventHandler) {
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
    Player currentPlayer = getCurrentPlayerHelper();
    currentPlayer.purchase(property);
  }

  public void publishGameData() {
    ModelOutput gameData = null;
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



  public void boardData() {
    List<Place> boardData = new ArrayList<>(places);
    //TODO: publish this data? I (David Lu) don't really know what this one should be
  }


  public void boardUpdateData() {
    ViewBoard boardData = new ViewBoardBuilder(new ArrayList<Place>(places), getCurrentPlayerHelper());
    //TODO: publish this data
  }

  /**
   * For test purpose
   * @param record
   */
  protected void initializeGame(InitBoardRecord record) {
    List<ParsedProperty> parsedProperties = record.places();
    Collection<PlayerRecord> playerRecords = record.players();
    places = new ArrayList<>();
    for (ParsedProperty parsedProperty : parsedProperties) {

      places.add(createPlace(parsedProperty.type(), parsedProperty.id()));
      //TODO: use reflection
    }
    players = new ArrayList<>();
    for (int i = 0; i < playerRecords.size(); i++)
      players.add(new ConcretePlayer(i));
    turn = new ConcretePlayerTurn(players, places);
  }

  /**
   * For test purposes
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
  public List<ViewPlayer> getPlayers() {
    List<ViewPlayer> playersData = new ArrayList<>(players);
    return playersData;
  }

  @Override
  public List<Place> getBoard() {
    return null;
  }

  @Override
  public Collection<StationaryAction> getStationaryAction() {
    Player currentPlayer = getCurrentPlayerHelper();
    Place currentPlace = places.get(currentPlayer.getCurrentPlaceId());
    Collection<StationaryAction> stationaryActions = currentPlace.getStationaryActions(currentPlayer);
    return stationaryActions;
  }

  //end of ModelOutput methods
  @Override
  public void onGameEvent(GameEvent event) {
    switch (event.getGameEventType()) {
      case "CONTROLLER_TO_MODEL_GAME_START" -> {
        Command cmd = event.getGameEventCommand().getCommand();
        initializeGame((InitBoardRecord) cmd.getCommandArgs());
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
    }
  }
}
