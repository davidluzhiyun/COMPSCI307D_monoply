package ooga.model.place.property;

import ooga.event.GameEventHandler;
import ooga.model.player.Player;

import java.util.List;

public class ConcreteRailroad extends AbstractProperty implements RailRoad{
  private final List<Double> rentWithRailroads;
  public ConcreteRailroad(String id, GameEventHandler gameEventHandler) {
    super(id, gameEventHandler);
    rentWithRailroads = (List<Double>) getConfig().get("rent with railroads");
  }

  public ConcreteRailroad(String id, int ownerId, GameEventHandler gameEventHandler) {
    super(id, ownerId, gameEventHandler);
    rentWithRailroads = (List<Double>) getConfig().get("rent with railroads");
  }

  @Override
  public double getMoney(Player player) {
    return rentWithRailroads.get(getOwner().getOwnedRailroadCount());
  }

  @Override
  public void setOwner(int playerId, Player owner) {
    super.setOwner(playerId, owner);
    owner.setOwnedRailroadsCount(owner.getOwnedRailroadCount() + 1);
  }

}
