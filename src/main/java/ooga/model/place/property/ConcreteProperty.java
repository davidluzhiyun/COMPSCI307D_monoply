package ooga.model.place.property;

import ooga.model.Player;
import ooga.model.place.ConcretePlace;

public abstract class ConcreteProperty extends ConcretePlace implements Property {
  private Player owner;
  
  public ConcreteProperty(int id) {
    super(id);
  }

  @Override
  public int getOwnerId() {
    return owner.getPlayId();
  }

  @Override
  public double getPurchasePrice() {
    return (double) getConfig().get("purchasePrice");
  }

  @Override
  public int getMortgagePrice() {
    return 0;
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
}
