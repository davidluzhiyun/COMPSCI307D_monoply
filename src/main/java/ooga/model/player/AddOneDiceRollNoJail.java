package ooga.model.player;

import static ooga.model.player.ConcretePlayer.DEFAULT_JAIL_TURNS;
import static ooga.model.player.ConcretePlayer.MAX_ROWS_IN_A_ROW;
public class AddOneDiceRollNoJail implements AddOneDiceRoll{
  private Player player;
  public AddOneDiceRollNoJail(Player player) {
    this.player = player;
  }
  @Override
  public void addOneDiceRRoll() {
    player.setHasNextDice(true);
    player.setDicesTotal(player.getDicesTotal()+1);
  }
}
