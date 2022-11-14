package ooga.model;

;

import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventListener;
import ooga.event.command.Command;
import ooga.event.command.SampleCommand;
import ooga.model.place.Place;
import ooga.model.place.property.Property;
import ooga.view.SampleViewData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ConcreteModel implements Model, GameEventListener {
  private ConcretePlayerTurn turn;
  private List<Player> players;
  private int currentPlayerId;
  private List<Place> places;
  private GameEventHandler gameEventHandler;

  public ConcreteModel(GameEventHandler gameEventHandler) {
    turn = new ConcretePlayerTurn(4);
    places = new ArrayList<>();
    players = new ArrayList<>();
    this.gameEventHandler = gameEventHandler;
  }

  @Override
  public Object getCommandArgs() {
    return null;
  }

  @Override
  public void publishDice() {
    Player currentPlayer = players.get(turn.getCurrentPlayerTurnId());
    int placesToGo = turn.roll();
    Place newPlace;
    if (placesToGo == -1)
      newPlace = null;
    newPlace = places.get(currentPlayer.getCurrentSpaceId() + placesToGo);
    currentPlayer.move(newPlace);
    Collection<StationaryAction> stationaryActions = newPlace.getStationaryActions(currentPlayer);

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
  public void currentPlayer() {

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
