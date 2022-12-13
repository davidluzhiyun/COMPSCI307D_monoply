package ooga.model.player;

import static ooga.model.player.ConcretePlayer.DEFAULT_JAIL_TURNS;
import static ooga.model.player.ConcretePlayer.MAX_ROWS_IN_A_ROW;
public class AddOneDiceRollJail implements AddOneDiceRoll{
  private Player player;
  public AddOneDiceRollJail(Player player) {
    this.player = player;
  }
  @Override
  public void addOneDiceRRoll() {
    if (player.remainingJailTurns() > 0) {
      player.getOutOfJail();
    }
    player.setHasNextDice(true);
    player.setDicesTotal(player.getDicesTotal()+1);
    if (player.getDicesTotal() > MAX_ROWS_IN_A_ROW){
      player.setDicesTotal(1);
      player.setJail(DEFAULT_JAIL_TURNS);
    }
  }
}
