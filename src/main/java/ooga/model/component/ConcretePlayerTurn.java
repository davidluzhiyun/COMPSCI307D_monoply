package ooga.model.component;

import ooga.event.GameEventHandler;
import ooga.model.GameState;
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
  private GameEventHandler gameEventHandler;
  public static final String modelToken = "MODEL_TO_MODEL_";


  public ConcretePlayerTurn(List<Player> players, List<Place> places, int currentPlayerIndex) {
    this.players = players;
    this.places = places;
    currentPlayer = players.get(currentPlayerIndex);
    currentPlayer.newTurn();
    currentPlace = this.places.get(currentPlayer.getCurrentPlaceIndex());
    dice = new ConcreteDice();
    gameEventHandler = new GameEventHandler();
  }

  @Override
  public void roll() {
    diceNum = dice.roll();
    int r1 = diceNum.x;
    int r2 = diceNum.y;
    currentPlayer.nextDice();
    if (r1 == r2)
      currentPlayer.addOneDiceRoll();
    gameEventHandler.publish(modelToken + GameState.DICE_RESULT);
    if (currentPlayer.remainingJailTurns() == 1) {
      currentPlayer.payOutOfJail();
    }
    if (currentPlayer.remainingJailTurns() == 0) {
      go(r1 + r2);
    }
    else {
      go(0);
    }
  }

  /**
   * @param step
   * @author Luyao Wang
   * @author David Lu modified the method to accomodate new definitions
   * Step can be negative, in the case of "go to jail do not pass GO" in chance.
   * This method also takes care of thing like wrap around
   */
  public void go(int step) {
    int passes = 0;
    //the player goes past GO (not including landing on go) and get money
    if (currentPlayer.getCurrentPlaceIndex() + step + 1 >= places.size()) {
      passes = (currentPlayer.getCurrentPlaceIndex() + step) / places.size();
      // Passing the place multiple times gives the player salaries multiple times
    }
    int index = (currentPlayer.getCurrentPlaceIndex() + step) % places.size();
    currentPlayer.setIndex(currentPlayer.getCurrentPlaceIndex() + index);
    gameEventHandler.publish(modelToken + GameState.MOVE);
    if (passes > 0) {
      currentPlayer.setMoney(currentPlayer.getTotalMoney() + passes * (places.get(0).getMoney(currentPlayer)));
      gameEventHandler.publish(modelToken + GameState.COLLECT_SALARY);
    }
    currentPlace = places.get(index);
    currentPlace.landingEffect(currentPlayer);
  }


  @Override
  public int getCurrentPlayerTurnId() {
    return currentPlayer.getPlayerId();
  }

  @Override
  public void nextTurn() {
    int currentPlayerId = currentPlayer.getPlayerId();
    if (currentPlayerId < players.size()) {
      currentPlayer = players.get(currentPlayerId + 1);
    } else {
      currentPlayer = players.get(0);
    }
    currentPlayer.newTurn();
    currentPlace = places.get(currentPlayer.getCurrentPlaceIndex());
  }

  /**
   * For test purpose
   * @return
   */
  protected Place getCurrentPlace() {
    return currentPlace;
  }

  @Override
  public Point getDiceNum() {
//    if (diceNum == null){
//      diceNum = dice.roll();
//    }
//    return new Point(diceNum);
    return diceNum;
  }
}
