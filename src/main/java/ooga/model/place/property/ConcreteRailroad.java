package ooga.model.place.property;

import ooga.model.Player;

import java.util.List;

public class ConcreteRailroad extends AbstractProperty implements RailRoad{
  private final List<Double> rentWithRailroads;
  public ConcreteRailroad(String id) {
    super(id);
    rentWithRailroads = (List<Double>) getConfig().get("rent with railroads");
  }

  public ConcreteRailroad(String id, int ownerId) {
    super(id, ownerId);
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
