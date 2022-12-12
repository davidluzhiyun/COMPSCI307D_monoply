package ooga.model.place.property;

import ooga.model.Player;

import java.util.List;

public class ConcreteUtility extends AbstractProperty implements Utility {
  public final double rent;

  public ConcreteUtility(String id) {
    super(id);
    rent = (double) getConfig().get("rent");
  }

  public ConcreteUtility(String id, int owner) {
    super(id, owner);
    rent = (double) getConfig().get("rent");
  }

  @Override
  public double getMoney(Player player) {
    return rent * player.getDice();
  }
}
