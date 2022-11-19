package ooga.model.place.property;

public class ConcreteStreet extends AbstractProperty implements Street {
  private final int colorId;
  private final double housePrice;
  private int housesBuilt = 0;

  public ConcreteStreet(int id) {
    super(id);
    colorId = (int) getConfig().get("colorId");
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
}
