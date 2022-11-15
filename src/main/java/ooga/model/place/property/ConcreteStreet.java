package ooga.model.place.property;

public class ConcreteStreet extends ConcreteProperty implements Street {
  private final double colorId;
  private final double housePrice;
  private int housesBuilt = 0;

  public ConcreteStreet(int id) {
    super(id);
    colorId = (double) getConfig().get("colorId");
    housePrice = (double) getConfig().get("houseCost");
  }

  @Override
  public int getColorId() {
    return (int) colorId;
  }

  @Override
  public int getHousePrice() {
    return (int) housePrice;
  }

  @Override
  public int getHousesBuilt() {
    return housesBuilt;
  }

  @Override
  public void addOneHouse() {
    housesBuilt++;
  }

  @Override
  public double getMoney() {
    return getRent();
  }
}
