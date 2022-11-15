package ooga.model;

import ooga.model.place.Place;
import ooga.model.place.property.StationaryActionManager;

import java.awt.*;
import java.util.List;

public class ConcretePlayerTurn implements PlayerTurn {
  private Player currentPlayer;
  private Place currentPlace;
  private List<? extends Player> players;
  private List<? extends Place> places;
  private Dice dice;
  private StationaryActionManager stationaryActionManager;

  public ConcretePlayerTurn(List<? extends Player> players, List<? extends Place> places) {
    this.stationaryActionManager = new StationaryActionManager();
    this.players = players;
    this.places = places;
    currentPlayer = players.get(0);
    currentPlayer.newTurn();
    currentPlace = this.places.get(currentPlayer.getCurrentPlaceId());
    dice = new ConcreteDice();
  }

  @Override
  public void roll() {
    Point point = dice.roll();
    int r1 = point.x;
    int r2 = point.y;
    currentPlayer.decrementOneTurnLeft();
    currentPlayer.addOneTurnUsed();
//    if (r1 == r2)
//      currentPlayer.addOneTurnLeft();
//    if (currentPlayer.goJail())
//      currentPlayer.move(jail);
    //TODO: roll triple doubles and go jail
    if (currentPlayer.getCurrentPlaceId() + r1 + r2 < places.size())
      currentPlace = places.get(currentPlayer.getCurrentPlaceId() + r1 + r2);
    else
      currentPlace = places.get(currentPlayer.getCurrentPlaceId() + r1 + r2 - places.size());
    currentPlayer.move(currentPlace);
  }

  @Override
  public List<StationaryAction> getStationaryActions() {
    List<StationaryAction> stationaryActions = stationaryActionManager.getStationaryAction(currentPlace, currentPlayer);
    return stationaryActions;
  }

  @Override
  public int getCurrentPlayerTurnId() {
    return currentPlayer.getPlayId();
  }

  @Override
  public void nextTurn() {
    int currentPlayerId = currentPlayer.getPlayId();
    if (currentPlayerId < players.size()) currentPlayer = players.get(currentPlayerId + 1);
    else currentPlayer = players.get(0);;
  }
}
