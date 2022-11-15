package ooga.model.place.property;

import ooga.model.PlaceAction;
import ooga.model.Player;
import ooga.model.StationaryAction;
import ooga.model.place.AbstractPlace;

import java.util.Collection;
import java.util.List;

public abstract class ConcreteProperty extends AbstractPlace implements Property {
  private final String name;
  private final double purchasePrice;
  private final double mortgagePrice;
  private final double rent;
  private final double rentWithColorSet;
  private final List<Double> rentWithHouses;
  private Player owner;
  
  public ConcreteProperty(int id) {
    super(id);
    name = (String) getConfig().get("name");
    purchasePrice = (double) getConfig().get("purchasePrice");
    mortgagePrice = (double) getConfig().get("mortgagePrice");
    rent = (double) getConfig().get("rent");
    rentWithColorSet = (double) getConfig().get("rentWithColorSet");
    rentWithHouses = (List<Double>) getConfig().get("rentWithHouses");
    addStationaryAction(StationaryAction.BUY_PROPERTY);
    addStationaryAction(StationaryAction.AUCTION);
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getOwnerId() {
    return owner.getPlayId();
  }

  @Override
  public double getPurchasePrice() {
    return purchasePrice;
  }

  @Override
  public double getMortgagePrice() {
    return mortgagePrice;
  }

  @Override
  public boolean isMortgaged() {
    return false;
  }

  @Override
  public String getPropertyName() {
    return null;
  }

  @Override
  public String getDescription() {
    return null;
  }

  @Override
  public void purchaseBy(Player player) {
    owner = player;
  }

  @Override
  public List<Double> getRentWithProperties() {
    return rentWithHouses;
  }

  protected double getRent() {
    return rent;
  }

  protected double getRentWithColorSet() {
    return rentWithColorSet;
  }

  @Override
  public int getHousesNum() {
    return 0;
  }

  @Override
  public Collection<PlaceAction> getPlaceActions() {
    return null;
  }
}
