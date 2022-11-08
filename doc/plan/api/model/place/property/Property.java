package model.place.property;

import model.place.Place;

/**
 * This interface is a shared interface of properties, including railroad, street, and utility.
 */
public interface Property extends Place {
  /**
   * Get the id of owner.
   * @return the id of owner
   */
  int getOwnerId();
  /**
   * Get the price to purchase the property.
   * @return the price to purchase the property
   */
  int getPurchasePrice();
  /**
   * Get the mopey earned by mortgaging the property.
   * @return mopey earned by mortgaging the property
   */
  int getMortgagePrice();

  /**
   * Get the rent paid to owner when pass by the property.
   * @return rent paid to owner
   */
  int getRent();

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
}
