package model;

import model.place.Place;

public interface Tax extends Place {
  /**
   * Get the amount of tax the player should pay.
   * @return amount of tax
   */
  int getTaxDue();
}
