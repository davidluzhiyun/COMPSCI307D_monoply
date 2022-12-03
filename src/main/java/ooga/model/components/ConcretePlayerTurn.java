package ooga.model.components;

import ooga.model.Player;
import ooga.model.place.Place;

import java.awt.*;
import java.util.List;

public class ConcretePlayerTurn implements PlayerTurn {
  private Player currentPlayer;
  private Place currentPlace;
  private final List<Player> players;
  private final List<Place> places;
  private Dice dice;
  private Point diceNum;


  public ConcretePlayerTurn(List<Player> players, List<Place> places) {
    this.players = players;
    this.places = places;
    currentPlayer = players.get(0);
    currentPlayer.newTurn();
    currentPlace = this.places.get(currentPlayer.getCurrentPlaceId());
    dice = new ConcreteDice();
  }

  @Override
  public Point roll() {
    Point point = dice.roll();
    int r1 = point.x;
    int r2 = point.y;
    currentPlayer.decrementOneDiceLeft();
    diceNum = point;
    if (r1 == r2)
      currentPlayer.addOneDiceRoll();
//    if (currentPlayer.goJail())
//      currentPlayer.move(jail);
    //TODO: roll triple doubles and go jail
    go(r1 + r2);
    return  point;
  }

  /**
   * Step can be negative, in the case of "go to jail do not pass GO" in chance.
   *
   * @param step
   */
  private void go(int step) {
    if (currentPlayer.getCurrentPlaceId() + step < places.size())
      currentPlace = places.get(currentPlayer.getCurrentPlaceId() + step);
    else {
      currentPlace = places.get(currentPlayer.getCurrentPlaceId() + step - places.size());
      if (currentPlayer.getCurrentPlaceId() + step - places.size() > 0)
        //the player goes past GO and still gets money
        currentPlayer.earnMoney(places.get(0).getMoney());
    }
    currentPlayer.move(currentPlace);
  }


  @Override
  public int getCurrentPlayerTurnId() {
    return currentPlayer.getPlayerId();
  }

  @Override
  public void nextTurn() {
    int currentPlayerId = currentPlayer.getPlayerId();
    if (currentPlayerId < players.size()) currentPlayer = players.get(currentPlayerId + 1);
    else currentPlayer = players.get(0);
    currentPlayer.newTurn();
  }

  @Override
  public Place getCurrentPlace() {
    return currentPlace;
  }

  @Override
  public Point getDiceNum() {
    if (diceNum == null){
      diceNum = dice.roll();
    }
    return new Point(diceNum);
  }
}
