package ooga.model.place.property;

import ooga.event.GameEventHandler;
import ooga.model.Player;
import ooga.model.StationaryAction;
import ooga.model.place.AbstractPlace;

import java.beans.EventHandler;
import java.util.Collection;
import java.util.List;

/**
 * @author David Lu refactoring and integration
 * @author Luyao Wang original
 * The concretion of the property interface
 * ABC of concrete properties like streets
 */
public abstract class AbstractProperty extends AbstractPlace implements Property {
  private final String name;
  private final double purchasePrice;
  private final double mortgagePrice;
  private final double rent;
  private final double rentWithColorSet;
  private final List<Double> rentWithHouses;
  private int ownerId;

  public AbstractProperty(String id) {
    super(id);
    name = (String) getConfig().get("name");
    purchasePrice = (double) getConfig().get("purchasePrice");
    mortgagePrice = (double) getConfig().get("mortgagePrice");
    rent = (double) getConfig().get("rent");
    rentWithColorSet = (double) getConfig().get("rentWithColorSet");
    rentWithHouses = (List<Double>) getConfig().get("rentWithHouses");
    ownerId = -1;
    addStationaryAction(StationaryAction.BUY_PROPERTY);
    addStationaryAction(StationaryAction.AUCTION);
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getOwnerId() {
    return ownerId;
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
  public void setOwner(int playerId) {
    ownerId = playerId;
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
  public void updatePlaceActions(Player player) {
    //Nothing for now
    return;
  }

  /**
   * @author David Lu added this method to override parent by adding new condition.
   * The property need to be unimproved to be purchased
   * @param player the current player
   * @return see the place interface
   */
  @Override
  public Collection<StationaryAction> getStationaryActions(Player player){
    if (ownerId != -1){
      return null;
    }
    else {
      return super.getStationaryActions(player);
    }
  }

  @Override
  public void landingEffect(Player player) {
    player.setMoney(player.getTotalMoney() + getMoney());
    GameEventHandler gameEventHandler = new GameEventHandler();
    gameEventHandler.publish("MODEL_TO_MODEL_PAY_RENT");
  }
}
