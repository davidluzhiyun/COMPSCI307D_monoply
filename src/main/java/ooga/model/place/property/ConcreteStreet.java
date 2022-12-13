package ooga.model.place.property;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ooga.event.GameEventHandler;
import ooga.model.PlaceAction;
import ooga.model.Player;
import ooga.model.exception.CannotBuildHouseException;

public class ConcreteStreet extends AbstractProperty implements Street {
  private final int colorId;
  private final double housePrice;
  private final double rent;
  private final double rentWithColorSet;
  private final List<Double> rentWithHouses;
  private int housesBuilt = 0;
  // refactor to resource later
  public static final int MAX_HOUSE = 5;


  public ConcreteStreet(String id, GameEventHandler gameEventHandler) {
    super(id, gameEventHandler);
    colorId = (int) (double) getConfig().get("colorId");
    housePrice = (double) getConfig().get("houseCost");
    rent = (double) getConfig().get("rent");
    rentWithColorSet = (double) getConfig().get("rentWithColorSet");
    rentWithHouses = (List<Double>) getConfig().get("rentWithHouses");
  }

  public ConcreteStreet(String id, int owner, int houseCount, GameEventHandler gameEventHandler) {
    super(id, owner, gameEventHandler);
    this.housesBuilt = houseCount;
    colorId = (int) (double) getConfig().get("colorId");
    housePrice = (double) getConfig().get("houseCost");
    rent = (double) getConfig().get("rent");
    rentWithColorSet = (double) getConfig().get("rentWithColorSet");
    rentWithHouses = (List<Double>) getConfig().get("rentWithHouses");
  }

  @Override
  public double getHousePrice() {
    return housePrice;
  }

  @Override
  public void addOneHouse() {
    housesBuilt++;
  }

  public List<Double> getRentWithProperties() {
    return rentWithHouses;
  }

  protected double getRentWithColorSet() {
    return rentWithColorSet;
  }

  /**
   * @param player
   * @return
   * @author David Lu
   * Calculate the amount of money the player passing by need to pay
   */
  @Override
  public double getMoney(Player player) {
    try {
      if (isMortgaged() || getOwner() == null) {
        return 0;
      }
      if (getOwner().getPlayerId() == player.getPlayerId()) {
        return 0;
      }
      if (!getOwner().checkMonopolyOver(colorId)) {
        return rent;
      }
      if (housesBuilt == 0) {
        return rentWithColorSet;
      }
      return rentWithHouses.get(housesBuilt - 1);
    } catch (NullPointerException e) {
      throw new IllegalStateException("Input can't be null", e);
    }
  }

  @Override
  public int getColorSetId() {
    return colorId;
  }

  @Override
  public int getHouseCount() {
    return housesBuilt;
  }

  @Override
  public void setHouseCount(int count) throws IllegalStateException {
    housesBuilt = count;
  }

  @Override
  public void updateCurrentPlayerPlaceActions(Player player) {
    super.updateCurrentPlayerPlaceActions(player);
    if (player.canBuildOn(this))
      getPlaceActions().add(PlaceAction.BUILD_HOUSE);
  }
}
