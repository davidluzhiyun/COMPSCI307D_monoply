package ooga.model.place.property;

import ooga.model.Player;
import ooga.model.place.Place;

import java.util.List;

/**
 * This interface is a shared interface of properties, including railroad, street, and utility.
 */
public interface Property extends Place{

  /**
   * Get the rent paid to owner when pass by the property.
   * @return rent paid to owner
   */
  double getMoney();

  /**
   * Get if the property is mortgaged.
   * @return if the property is mortgaged
   */
  boolean isMortgaged();
  /**
   * Get the name of the property.
   * @return name of the property
   */
  String getPropertyName();
  /**
   * Get description of the property.
   * @return description of the property
   */
  String getDescription();

  String getName();
  /**
   * Get the mopey earned by mortgaging the property.
   * @return mopey earned by mortgaging the property
   */
  double getMortgagePrice();
  List<Double> getRentWithProperties();
}
