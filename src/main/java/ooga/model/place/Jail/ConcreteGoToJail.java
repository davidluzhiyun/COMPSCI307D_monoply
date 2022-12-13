package ooga.model.place.Jail;

import ooga.event.GameEventHandler;
import ooga.model.ConcretePlayer;
import ooga.model.Player;
import ooga.model.place.AbstractPlace;

public class ConcreteGoToJail extends AbstractPlace implements GoToJail {

  public ConcreteGoToJail(String id, GameEventHandler gameEventHandler) {
    super(id, gameEventHandler);
  }

  /**
   * @author David Lu
   * Send the player landing on it directly to Jail
   * @param player The player that landed on the GoToJail place
   */
  @Override
  public void landingEffect(Player player){
    player.setJail(ConcretePlayer.DEFAULT_JAIL_TURNS);
  }
}
