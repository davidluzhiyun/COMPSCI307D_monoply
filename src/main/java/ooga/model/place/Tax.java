package ooga.model.place;

import ooga.model.place.Place;

public interface Tax extends Place {
  /**
   * Get the amount of tax the player should pay.
   *
   * @return amount of tax
   */
  int getTaxDue();
}
