package ooga.model.place.property;

public interface RailRoad extends Property{
  /**
   * Get rent of the railroad. The rent of railroad increases as the owner possesses more railroads.
   * @return  rent of the railroad
   */
  @Override
  double getMoney();
}
