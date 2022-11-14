package ooga.model.colorSet;

import ooga.model.place.property.Street;

public class DummyStreet extends DummyPlace implements Street {
  private int color;
  DummyStreet(int color,int id) {
    super(id);
    this.color = color;
  }

  @Override
  public int getColorId() {
    return color;
  }

  @Override
  public int getHousePrice() {
    return 0;
  }

  @Override
  public int getHousesBuilt() {
    return 0;
  }

  @Override
  public int getOwnerId() {
    return 0;
  }

  @Override
  public int getPurchasePrice() {
    return 0;
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
