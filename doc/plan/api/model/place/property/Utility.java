package model.place.property;

public interface Utility extends Property{
  /**
   * Get rent of the utility. The rent of railroad increases as the owner possesses more utility and steps passengers take
   * to reach utility increase.
   * @return  rent of the utility
   */
  @Override
  int getRent();
}
