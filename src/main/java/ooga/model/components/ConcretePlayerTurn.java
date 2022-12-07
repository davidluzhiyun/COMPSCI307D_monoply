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
    currentPlace = this.places.get(currentPlayer.getCurrentPlaceIndex());
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
   * @author Luyao Wang
   * @author David Lu modified the method to accomodate new definitions
   * Step can be negative, in the case of "go to jail do not pass GO" in chance.
   * This method also takes care of thing like wrap around
   *
   * @param step
   */
  private void go(int step) {
    //the player goes past GO (including landing on go) and get money
    if (currentPlayer.getCurrentPlaceIndex() + step >= places.size()){
      int passes = (currentPlayer.getCurrentPlaceIndex() + step) / places.size();
      // Passing the place multiple times gives the player salaries multiple times
      currentPlayer.setMoney(currentPlayer.getTotalMoney() + passes * (places.get(0).getMoney()));
    }
    int index = (currentPlayer.getCurrentPlaceIndex() + step) % places.size();
    currentPlace = places.get(index);
    // to prevent giving salary repetitively
    if (index != 0){
      // since the move method no longer accepts place, this step can't be done automatically
      // TODO: publish event when rent levied
      double money = currentPlace.getMoney();
      currentPlayer.setMoney(currentPlayer.getTotalMoney() + money);
    }
    currentPlayer.setIndex(currentPlayer.getCurrentPlaceIndex() + index);
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
