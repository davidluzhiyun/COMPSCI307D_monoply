package ooga.model.place.property;

public class ConcreteStreet extends ConcreteProperty implements Street{
  public ConcreteStreet(int id) {
    super(id);
  }
  @Override
  public int getColorId() {
    return 0;
  }

  @Override
  public int getHousePrice() {
    return 0;
  }

  @Override
  public int getHousesBuilt() {
    return 0;
  }
}
