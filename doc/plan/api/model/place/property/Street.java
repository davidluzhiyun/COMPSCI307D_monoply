package model.place.property;

public interface Street extends Property{
  /**
   * Get the id of the color of street.
   * @return id of the color of street
   */
  int getColorId();
  /**
   * Get the price it takes to build a house on the street.
   * @return price it takes to build a house
   */
  int getHousePrice();
  /**
   * Get the number of houses built on the street.
   * @return number of houses built
   */
  int getHousesBuilt();
  /**
   * Get rent of the street. The rent of street increases as the owner builds more houses (hotel).
   * @return  rent of the street
   */
  @Override
  double getMoney();
}
