package ooga.model.place.property;

import java.util.List;

public interface Street extends Property {
  /**
   * Get the price it takes to build a house on the street.
   *
   * @return price it takes to build a house
   */
  int getHousePrice();


  void addOneHouse();
  List<Double> getRentWithProperties();

}
