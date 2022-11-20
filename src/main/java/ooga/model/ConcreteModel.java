package ooga.model;

import java.util.Collection;

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
import ooga.model.place.property.ConcreteStreet;
import ooga.model.place.property.Property;
import ooga.view.SampleViewData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConcreteModel implements Model, GameEventListener {
  private ConcretePlayerTurn turn;
  private List<Player> players;
  private List<Place> places;
  private GameEventHandler gameEventHandler;

  public ConcreteModel(GameEventHandler gameEventHandler) {
    this.gameEventHandler = gameEventHandler;
  }

  @Override
  public void publishDice() {
    Player currentPlayer = players.get(turn.getCurrentPlayerTurnId());
    turn.roll();
    SampleViewData d = null;//TODO
    Command cmd = new SampleCommand(d);
    GameEvent event = gameEventHandler.makeGameEventwithCommand("MODEL_TO_CONTROLLER_DICE_ROLLED", cmd);
    gameEventHandler.publish(event);
  }

  @Override
  public void endTurn() {
    turn.nextTurn();
  }

  public void buyProperty(Property property) {
    Player currentPlayer = getCurrentPlayer();
    currentPlayer.purchase(property);
  }

  @Override
  public void publishGameData() {
    ModelOutput gameData = null;
    Command cmd = new GameDataCommand(gameData);
    GameEvent event = gameEventHandler.makeGameEventwithCommand("MODEL_TO_CONTROLLER_GAME_DATA", cmd);
    gameEventHandler.publish(event);
  }


  @Override
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

  @Override
  public void playersData() {
    Collection<ViewPlayer> playersData = new ArrayList<>(players);
    //TODO: publish this data
  }

  @Override
  public void boardData() {
    List<Place> boardData = new ArrayList<>(places);
    //TODO: publish this data? I (David Lu) don't really know what this one should be
  }

  @Override
  public void stationaryActions() {
    Player currentPlayer = getCurrentPlayer();
    Place currentPlace = places.get(currentPlayer.getCurrentPlaceId());
    Collection<StationaryAction> stationaryActions = currentPlace.getStationaryActions(currentPlayer);
    //TODO: publish this data (stationaryActions)
  }

  @Override
  public void boardUpdateData() {
    ViewBoard boardData = new ViewBoardBuilder(new ArrayList<Place>(places), getCurrentPlayer());
    //TODO: publish this data
  }

  private void initializeGame(InitBoardRecord record) {
    List<ParsedProperty> parsedProperties = record.places();
    Collection<PlayerRecord> playerRecords = record.players();
    places = new ArrayList<>();
    for (ParsedProperty parsedProperty : parsedProperties) {
      places.add(new ConcreteStreet(parsedProperty.id()));
      //TODO: use reflection
    }
    players = new ArrayList<>();
    for (int i = 0; i < playerRecords.size(); i++)
      players.add(new ConcretePlayer(i));
    turn = new ConcretePlayerTurn(players, places);
  }

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
