package ooga.model;

;

import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventListener;
import ooga.event.command.Command;
import ooga.event.command.SampleCommand;
import ooga.model.place.AbstractPlace;
import ooga.model.place.property.Property;
import ooga.view.SampleViewData;

import java.util.ArrayList;
import java.util.List;

public class ConcreteModel implements Model, GameEventListener {
  private ConcretePlayerTurn turn;
  private List<ConcretePlayer> players;
  private int currentPlayerId;
  private List<AbstractPlace> places;
  private GameEventHandler gameEventHandler;

  public ConcreteModel(GameEventHandler gameEventHandler) {
    places = new ArrayList<>();
    players = new ArrayList<>();
    turn = new ConcretePlayerTurn(players, places);
    this.gameEventHandler = gameEventHandler;
  }

  @Override
  public void publishDice() {
    Player currentPlayer = players.get(turn.getCurrentPlayerTurnId());
    turn.roll();
    List<StationaryAction> stationaryActions = turn.getStationaryActions();
    SampleViewData d = null;//TODO
    Command cmd = new SampleCommand(d);
    GameEvent event = gameEventHandler.makeGameEventwithCommand("MODEL_TO_CONTROLLER_DICE_ROLLED", cmd);
    gameEventHandler.publish(event);
  }

  public void endTurn() {
    turn.nextTurn();
  }

  public void buyProperty(Property property) {
    Player currentPlayer = players.get(currentPlayerId);
    currentPlayer.purchase(property);
  }

  @Override
  public void publishCurrentPlayer() {

  }

  @Override
  public void playersData() {

  }

  @Override
  public void boardData() {

  }

  @Override
  public void stationaryAction() {

  }

  @Override
  public void boardUpdateData() {

  }

  @Override
  public void onGameEvent(GameEvent event) {
    switch (event.getGameEventType()) {
      case "CONTROLLER_TO_MODEL_ROLL_DICE":
        Command cmd = event.getGameEventCommand().getCommand();
        publishDice();
        break;
    }
  }
}
