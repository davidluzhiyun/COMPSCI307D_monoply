package ooga.model.place.property;

import java.util.ArrayList;
import java.util.Collection;
import ooga.model.PlaceAction;
import ooga.model.Player;
import ooga.model.exception.CannotBuildHouseException;

public class ConcreteStreet extends AbstractProperty implements Street {
  private final int colorId;
  private final double housePrice;
  private int housesBuilt = 0;
  // refactor to resource later
  public static final int MAX_HOUSE = 5;

  public ConcreteStreet(String id) {
    super(id);
    colorId = (int) (double) getConfig().get("colorId");;
    housePrice = (double) getConfig().get("houseCost");
  }

  @Override
  public int getHousePrice() {
    return (int) housePrice;
  }

  @Override
  public void addOneHouse() {
    housesBuilt++;
  }

  @Override
  public double getMoney() {
    return getRent();
  }

  @Override
  public int getColorSetId() {
    return colorId;
  }

  @Override
  public int getHouseCount() {
    return housesBuilt;
  }

  public void print() {
    System.out.println(11111);
  }

  @Override
  public void setHouseCount(int count) throws IllegalStateException {
    housesBuilt = count;
  }
  @Override
  public void updatePlaceActions(Player player) {
    Collection<PlaceAction> updatedPlaceActions = getPlaceActions();
    Collection<PlaceAction> inherentPlaceActions = getInherentPlaceActions();
    updatedPlaceActions.clear();
    updatedPlaceActions.addAll(inherentPlaceActions);
    if (player.canBuildOn(this) && housesBuilt <= MAX_HOUSE && (!isMortgaged())){
      updatedPlaceActions.add(PlaceAction.BUILD_HOUSE);
      updatedPlaceActions.add(PlaceAction.MORTGAGE);
    }
    else {
      //In case build house or mortgage was put into inherentPlaceAction by teammates
      updatedPlaceActions.remove(PlaceAction.BUILD_HOUSE);
      updatedPlaceActions.remove(PlaceAction.MORTGAGE);
      //TODO: other effects of mortgage
    }
  }
}
