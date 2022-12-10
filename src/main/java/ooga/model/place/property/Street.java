package ooga.model.place.property;

import ooga.model.Player;

import java.util.List;

public interface Street extends Property {
  /**
   * Get the price it takes to build a house on the street.
   *
   * @return price it takes to build a house
   */
  int getHousePrice();

  /**
   * Get rent of the street. The rent of street increases as the owner builds more houses (hotel).
   *
   * @return rent of the street
   */
  @Override
  double getMoney(Player player);

  void addOneHouse();
  List<Double> getRentWithProperties();

}
