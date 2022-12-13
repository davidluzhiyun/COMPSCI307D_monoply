package ooga.model.place.property;

import ooga.event.GameEventHandler;
import ooga.model.Player;

import java.util.List;

public class ConcreteUtility extends AbstractProperty implements Utility {
  public final double rent;

  public ConcreteUtility(String id, GameEventHandler gameEventHandler) {
    super(id, gameEventHandler);
    rent = (double) getConfig().get("rent");
  }

  public ConcreteUtility(String id, int owner, GameEventHandler gameEventHandler) {
    super(id, owner, gameEventHandler);
    rent = (double) getConfig().get("rent");
  }

  @Override
  public double getMoney(Player player) {
    return rent * player.getDice();
  }
}
