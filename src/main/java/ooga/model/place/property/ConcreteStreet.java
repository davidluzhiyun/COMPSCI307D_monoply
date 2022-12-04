package ooga.model.place.property;

import ooga.model.exception.CannotBuildHouseException;

public class ConcreteStreet extends AbstractProperty implements Street {
  private final int colorId;
  private final double housePrice;
  private int housesBuilt = 0;

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
}
