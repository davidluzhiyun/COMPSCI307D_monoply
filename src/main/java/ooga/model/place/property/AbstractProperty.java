package ooga.model.place.property;

import ooga.event.GameEventHandler;
import ooga.model.GameState;
import ooga.model.Player;
import ooga.model.StationaryAction;
import ooga.model.place.AbstractPlace;

import java.util.ArrayList;
import java.util.Collection;

import static ooga.model.component.ConcretePlayerTurn.modelToken;

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
  private int ownerId;
  private Player owner;

  public AbstractProperty(String id) {
    super(id);
    name = (String) getConfig().get("name");
    purchasePrice = (double) getConfig().get("purchasePrice");
    mortgagePrice = (double) getConfig().get("mortgagePrice");
    ownerId = -1;
  }

  public AbstractProperty(String id, int ownerId) {
    super(id);
    this.ownerId = ownerId;
    name = (String) getConfig().get("name");
    purchasePrice = (double) getConfig().get("purchasePrice");
    mortgagePrice = (double) getConfig().get("mortgagePrice");
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
  public void setOwner(int playerId, Player owner) {
    ownerId = playerId;
    this.owner = owner;
  }

  protected Player getOwner() {
    return owner;
  }

  @Override
  public void updateCurrentPlayerPlaceActions(Player player) {
    //Nothing for now
    return;
  }

  /**
   * @param player the current player
   * @return see the place interface
   * @author David Lu added this method to override parent by adding new condition.
   * The property need to be unimproved to be purchased
   */
  @Override
  public Collection<StationaryAction> getStationaryActions(Player player) {
    if (ownerId != -1) {
      //if owned, no stationary actions related to places are available
      return getCommonTurnBasedStationaryAction(player);
    } else {
      return super.getStationaryActions(player);
    }
  }

  @Override
  public Collection<StationaryAction> getPlaceBasedStationaryActions(Player player) {
    Collection<StationaryAction> placeBasedStationaryActions = new ArrayList<>();
    placeBasedStationaryActions.add(StationaryAction.AUCTION);
    if (player.getTotalMoney() >= getPurchasePrice())
      placeBasedStationaryActions.add(StationaryAction.BUY_PROPERTY);
    return placeBasedStationaryActions;
  }

  @Override
  public void landingEffect(Player player) {
    if (ownerId != -1) {//if owned
      player.setMoney(player.getTotalMoney() - getMoney(player));
      owner.setMoney(player.getTotalMoney() + getMoney(player));
      GameEventHandler gameEventHandler = new GameEventHandler();
      gameEventHandler.publish(modelToken + GameState.PAY_RENT);
    }
  }
}
